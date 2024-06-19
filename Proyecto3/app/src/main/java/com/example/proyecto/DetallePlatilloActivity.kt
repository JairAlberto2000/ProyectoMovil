package com.example.proyecto

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.proyecto.databinding.ActivityDetallePlatilloBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class DetallePlatilloActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var binding: ActivityDetallePlatilloBinding
    private val platillosViewModel: PlatillosViewModel by lazy {
        (application as MyApp).platillosViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallePlatilloBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = Firebase.auth
        db = FirebaseFirestore.getInstance()

        val nombre = intent.getStringExtra("nombrePlatillo")
        val precio = intent.getDoubleExtra("precioPlatillo", 0.0)
        val info = intent.getStringExtra("infoPlatillo")
        val idPlat = intent.getStringExtra("idPlatillo")

        binding.tvNombreDetalle.text = nombre ?: ""
        binding.tvPrecioDetalle.text = precio.toString()
        binding.tvInfoDetalle.text= info ?:""

        binding.btnAgregar.setOnClickListener {
            val pedido = hashMapOf(
                "Nombre" to nombre,
                "Total" to precio
            )

            db.collection("Pedidos")
                .add(pedido)
                .addOnSuccessListener { documentReference ->
                    val pedidoId = documentReference.id
                    Toast.makeText(baseContext, "Pedido solicitado correctamente", Toast.LENGTH_SHORT).show()

                    // Guarda el ID en el documento
                    db.collection("Pedidos").document(pedidoId).update("id", pedidoId)
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error adding document", e)
                }
        }

        binding.btnRegresarAdm.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        binding.btnVerHistorial.setOnClickListener {
            val intent = Intent(this, HistorialCliente::class.java)
            startActivity(intent)
        }

        binding.btnAgregarFavoritos.setOnClickListener {
            val platillo = Platillo(idPlat ?: "",nombre ?: "", precio ?: 0.0, info ?: "")
            platillosViewModel.agregarPlatilloAfavoritos(platillo)

            Toast.makeText(this, "Platillo agregado a favoritos", Toast.LENGTH_SHORT).show()
        }
    }
}
