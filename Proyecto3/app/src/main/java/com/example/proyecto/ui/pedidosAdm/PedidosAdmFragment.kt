package com.example.proyecto.ui.pedidosAdm

import android.widget.Toast

import androidx.lifecycle.ViewModelProvider

import com.example.proyecto.DetallePedido
import com.example.proyecto.DetallePlatilloActivity
import com.example.proyecto.MyApp
import com.example.proyecto.PedidosAdapter
import com.example.proyecto.Platillo
import com.example.proyecto.PlatillosAdapter
import com.example.proyecto.PlatillosViewModel
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentDashboardBinding
import com.example.proyecto.databinding.FragmentPedidosAdmBinding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.Pedido

class PedidosAdmFragment : Fragment() {

    private var _binding: FragmentPedidosAdmBinding? = null
    private val binding get() = _binding!!

    private val platillosViewModel: PlatillosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPedidosAdmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val pedidosAdapter = PedidosAdapter(emptyList(), object : PedidosAdapter.PlatilloClickListener {
            override fun onPlatilloClick(pedido: Pedido) {
                // Manejar clic en un platillo: abrir la actividad de detalles

                    val intent = Intent(requireContext(), DetallePedido::class.java)
                    intent.putExtra("nombrePlatillo", pedido.nombre)
                    intent.putExtra("precioPlatillo", pedido.total)
                    intent.putExtra("pedidoId", pedido.id) // Pasar el ID del pedido
                   // intent.putExtra("position", position) // Pasar la posición del pedido

                    startActivity(intent)

            }
        })

        recyclerView.adapter = pedidosAdapter

        // Observa los cambios en LiveData y actualiza el adaptador
        platillosViewModel.pedidos.observe(viewLifecycleOwner, Observer { pedidos ->
            pedidosAdapter.actualizarLista(pedidos)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
