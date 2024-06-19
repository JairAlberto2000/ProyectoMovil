package com.example.proyecto

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class ReservaCliente : AppCompatActivity() {

    private lateinit var editTextFecha: EditText
    private lateinit var editTextHora: EditText
    private lateinit var editTextClientes: EditText
    private lateinit var editTextNombreCliente: EditText
    private lateinit var botonReservar: Button

    companion object {
        private const val TAG = "ReservaCliente"
        private const val CHANNEL_ID = "Canal_Reservaciones"
        private const val PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva_cliente)

        editTextFecha = findViewById(R.id.editTextFecha)
        editTextHora = findViewById(R.id.editTextHora)
        editTextClientes = findViewById(R.id.editTextClientes)
        editTextNombreCliente = findViewById(R.id.editTextNombreCliente)
        botonReservar = findViewById(R.id.botonReservar)

        botonReservar.setOnClickListener { hacerReservacion() }

        // Verificar y solicitar permisos si es necesario
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    PERMISSION_REQUEST_CODE)
            }
        } else {
            // Versiones anteriores no requieren permisos explícitos para notificaciones
            enviarNotificacion("Prueba de Notificación", "Esta es una prueba de notificación.")
        }
    }

    private fun hacerReservacion() {
        val fecha = editTextFecha.text.toString()
        val hora = editTextHora.text.toString()
        val invitados = editTextClientes.text.toString().toIntOrNull()
        val nombreCliente = editTextNombreCliente.text.toString()

        if (fecha.isNotEmpty() && hora.isNotEmpty() && invitados != null && nombreCliente.isNotEmpty()) {
            Toast.makeText(this, "Reservación realizada", Toast.LENGTH_SHORT).show()

            // Enviar notificación solo al cliente
            enviarNotificacion("Reservación Realizada", "Tu reservación ha sido realizada con éxito.")
        } else {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun crearCanalNotificacion() {
        val channelName = "Canal de Reservaciones"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val notificationChannel = NotificationChannel(CHANNEL_ID, channelName, importance).apply {
            description = "Canal para notificaciones de reservaciones"
        }
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }

    private fun enviarNotificacion(titulo: String, mensaje: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            crearCanalNotificacion()
        }

        Log.d(TAG, "Construyendo notificación...")
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val intentPendiente = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val constructorNotificacion = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(titulo)
            .setContentText(mensaje)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(intentPendiente)

        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(this@ReservaCliente, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                notify(0, constructorNotificacion.build())
            } else {
                Log.d(TAG, "Permiso de notificación no concedido")
            }
        }
        Log.d(TAG, "Notificación enviada.")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                enviarNotificacion("Permiso Concedido", "Ahora puedes recibir notificaciones.")
            } else {
                Toast.makeText(this, "Permiso de notificación denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
