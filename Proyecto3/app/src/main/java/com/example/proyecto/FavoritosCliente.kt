package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.databinding.ActivityFavoritosClienteBinding
import com.example.proyecto.databinding.ActivityHistorialClienteBinding

class FavoritosCliente : AppCompatActivity() {
    private lateinit var binding: ActivityFavoritosClienteBinding
    private lateinit var pedidosAdapter: FavoritosAdapter
    private val platillosViewModel: PlatillosViewModel by lazy {
        (application as MyApp).platillosViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritosClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        val pedidosAdapter =
            FavoritosAdapter(emptyList(), object : FavoritosAdapter.PlatilloClickListener {
                override fun onPlatilloClick(platillo: Platillo?, position: Int) {
                    // Manejar clic en un platillo: abrir la actividad de detalles
                    platillo?.let {
                        val intent = Intent(this@FavoritosCliente, DetallesFav::class.java)
                        intent.putExtra("nombrePlatillo", it.nombre)
                        intent.putExtra("precioPlatillo", it.precio)
                        intent.putExtra("position", position) // Pasar la posición del pedido

                        startActivity(intent)
                    }
                }
            })

        recyclerView.adapter = pedidosAdapter

        // Observa los cambios en LiveData y actualiza el adaptador
        platillosViewModel.obtenerFavoritos().observe(this, Observer { pedidos ->
            pedidosAdapter.actualizarLista(pedidos)
        })
    }
}