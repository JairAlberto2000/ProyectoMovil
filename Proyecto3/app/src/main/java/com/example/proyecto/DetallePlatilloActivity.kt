package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.proyecto.databinding.ActivityDetallePlatilloBinding

class DetallePlatilloActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetallePlatilloBinding
    private lateinit var navController: NavController
    private lateinit var platillosViewModel: PlatillosViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallePlatilloBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar el ViewModel
        platillosViewModel = ViewModelProvider(this).get(PlatillosViewModel::class.java)

        // Obtener detalles del intent
        val nombre = intent.getStringExtra("nombrePlatillo")
        val precio = intent.getDoubleExtra("precioPlatillo", 0.0)

        // Mostrar detalles en las vistas de la actividad
        binding.tvNombreDetalle.text = nombre
        binding.tvPrecioDetalle.text = precio.toString()
        // Agregar más vistas según sea necesario para otros detalles


        binding.btnAgregar.setOnClickListener {
            val platillo = Platillo(nombre, precio)
            platillosViewModel.agregarPlatilloAlPedido(platillo)

            // Informar al usuario que se agregó el platillo al pedido
            Toast.makeText(this, "Platillo agregado al pedido", Toast.LENGTH_SHORT).show()
        }
    }
}