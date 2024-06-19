package com.example.proyecto

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.proyecto.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import org.json.JSONObject

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var minimumDistance = 30
    private val PERMISSION_LOCATION = 999
    private lateinit var binding: ActivityMapsBinding
    private lateinit var mFusedLocationProviderClient:
            FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    fun onMapCLick(latLng: LatLng?) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng!!, 13f))
        mMap.addMarker(
            MarkerOptions()
                .title("Marca personal")
                .snippet("Mi sitio marcado")
                .draggable(true)
                .icon(
                    BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_background)
                )
                .position(latLng)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        mFusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest.create()
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 1000
        locationRequest.smallestDisplacement = minimumDistance.toFloat()
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                Log.e(
                    "APP 06",
                    locationResult.lastLocation?.latitude.toString() + "," +
                            locationResult.lastLocation?.longitude
                )
            }
        }
    }//onCreate


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions,
            grantResults)
        if (requestCode == PERMISSION_LOCATION) {
            if
                    (permissions[0].equals(
                    Manifest.permission.ACCESS_FINE_LOCATION, ignoreCase =
                true)
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {

                startLocationUpdates()
            }
        }
    }//onRequestPermissionsResult

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                PERMISSION_LOCATION
            )
        }
        // Add a marker in Sydney and move the camera
        val CetiRioSantiago = LatLng(20.7029919, -103.391817)
        mMap.addMarker(MarkerOptions().position(CetiRioSantiago).title("Ceti Colomo"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        // Set click listener for markers
        mMap.setOnMarkerClickListener { marker ->
            drawRoute(marker.position)
            true // Return true to indicate that the event has been consumed
        }
    }//onMapReady

    private fun startLocationUpdates() {
        try {
            mFusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null
            )
        } catch (e: SecurityException) {
        }
    }//startLocationUpdates

    private fun stopLocationUpdates() {
        mFusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }//stopLocationUpdates
    override fun onStart() {
        super.onStart()
        startLocationUpdates()
    }//onStart
    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }//onPause

    private fun drawRoute(destination: LatLng) {
        // Get current location
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mFusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                val origin = "${location.latitude},${location.longitude}"
                val url =
                    "https://maps.googleapis.com/maps/api/directions/json" +
                            "?origin=$origin" +
                            "&destination=${destination.latitude},${destination.longitude}" +
                            "&key=AIzaSyA3v7cSdgGTq2q0xrgbHbDz6zyR8YADmQY" // Replace "YOUR_API_KEY" with your actual API key

                val queue = Volley.newRequestQueue(this)

                val jsonObjectRequest = JsonObjectRequest(
                    Request.Method.GET, url, null,
                    Response.Listener<JSONObject> { response ->
                        // Parse the response JSON and draw the route on the map
                        val routes = response.getJSONArray("routes")
                        if (routes.length() > 0) {
                            val points = routes.getJSONObject(0)
                                .getJSONObject("overview_polyline")
                                .getString("points")
                            val line = PolylineOptions()
                            val decoded = PolyUtil.decode(points)
                            for (point in decoded) {
                                line.add(point)
                            }
                            mMap.addPolyline(line)
                        }
                    },
                    Response.ErrorListener { error ->
                        // Handle Volley errors here
                        Log.e("VolleyError", "Error: ${error.message}")
                    }
                )
                queue.add(jsonObjectRequest)
            }
        }
    }
    //override fun onMapClick(latLng: LatLng) {}
}