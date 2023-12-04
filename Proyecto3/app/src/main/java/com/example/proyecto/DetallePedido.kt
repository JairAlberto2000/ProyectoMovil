package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import com.example.proyecto.databinding.ActivityDetallePedidoBinding
import com.example.proyecto.databinding.ActivityDetallePlatilloBinding

class DetallePedido : AppCompatActivity() {
    private lateinit var binding: ActivityDetallePedidoBinding
    private lateinit var navController: NavController
    //private lateinit var platillosViewModel: PlatillosViewModel

    val platillosViewModel: PlatillosViewModel by lazy {
        (this.application as MyApp).platillosViewModel
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallePedidoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar el ViewModel
        // platillosViewModel = ViewModelProvider(this).get(PlatillosViewModel::class.java)

        // Obtener detalles del intent
        val nombre = intent.getStringExtra("nombrePlatillo")
        val precio = intent.getDoubleExtra("precioPlatillo", 0.0)

        // Mostrar detalles en las vistas de la actividad
        binding.tvNombreDetalle.text = nombre
        binding.tvPrecioDetalle.text = precio.toString()
        // Agregar más vistas según sea necesario para otros detalles

    }
}