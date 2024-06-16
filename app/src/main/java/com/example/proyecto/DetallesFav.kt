package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.example.proyecto.databinding.ActivityDetallePedidoBinding
import com.example.proyecto.databinding.ActivityDetallesFavBinding

class DetallesFav : AppCompatActivity() {
    private lateinit var binding: ActivityDetallesFavBinding
    private lateinit var navController: NavController
    //private lateinit var platillosViewModel: PlatillosViewModel

    val platillosViewModel: PlatillosViewModel by lazy {
        (this.application as MyApp).platillosViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesFavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar el ViewModel
        // platillosViewModel = ViewModelProvider(this).get(PlatillosViewModel::class.java)

        // Obtener detalles del intent
        val nombre = intent.getStringExtra("nombrePlatillo")
        val precio = intent.getDoubleExtra("precioPlatillo", 0.0)
        val position = intent.getIntExtra("position", -1)

        // Mostrar detalles en las vistas de la actividad
        binding.tvNombreDetalle.text = nombre
        binding.tvPrecioDetalle.text = precio.toString()
        // Agregar más vistas según sea necesario para otros detalles


    }

}