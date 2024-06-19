package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.databinding.ActivityHistorialClienteBinding


class HistorialCliente : AppCompatActivity() {

    class HistorialCliente : AppCompatActivity() {
        private lateinit var binding: ActivityHistorialClienteBinding
        private lateinit var pedidosAdapter: PedidosHistorialAdapter
        private val platillosViewModel: PlatillosViewModel by lazy {
            (application as MyApp).platillosViewModel
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityHistorialClienteBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val recyclerView: RecyclerView = binding.recyclerView
            recyclerView.layoutManager = LinearLayoutManager(this)

            pedidosAdapter = PedidosHistorialAdapter(emptyList(), object : PedidosHistorialAdapter.PlatilloClickListener {
                override fun onPlatilloClick(platillo: Platillo?, position: Int) {
                    // Manejar clic en un platillo: abrir la actividad de detalles o realizar alguna acción
                    platillo?.let {
                        val intent = Intent(this@HistorialCliente, DetallePedido::class.java)
                        intent.putExtra("nombrePlatillo", it.Nombre)
                        intent.putExtra("precioPlatillo", it.Costo)
                        intent.putExtra("position", position) // Pasar la posición del pedido

                        startActivity(intent)
                    }
                }
            })

            recyclerView.adapter = pedidosAdapter

            // Observar los cambios en LiveData y actualizar el adaptador
            platillosViewModel.obtenerPedidos1().observe(this, Observer { pedidos ->
                pedidosAdapter.actualizarLista(pedidos)
            })
        }
    }

}
