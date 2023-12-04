package com.example.proyecto.ui.pedidosAdm

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.DetallePlatilloActivity
import com.example.proyecto.Platillo
import com.example.proyecto.PlatillosAdapter
import com.example.proyecto.PlatillosViewModel
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentDashboardBinding
import com.example.proyecto.databinding.FragmentPedidosAdmBinding

class PedidosAdmFragment : Fragment() {
    private var _binding: FragmentPedidosAdmBinding? = null
    private val binding get() = _binding!!

    private lateinit var platillosViewModel: PlatillosViewModel

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

        platillosViewModel = ViewModelProvider(requireActivity()).get(PlatillosViewModel::class.java)

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val platillosAdapter = PlatillosAdapter(platillosViewModel.getPedidos(), object : PlatillosAdapter.PlatilloClickListener {
            override fun onPlatilloClick(platillo: Platillo) {
                // Manejar clic en un platillo: abrir la actividad de detalles
                val intent = Intent(requireContext(), DetallePlatilloActivity::class.java)
                intent.putExtra("nombrePlatillo", platillo.nombre)
                intent.putExtra("precioPlatillo", platillo.precio)
                startActivity(intent)
            }
        })

        recyclerView.adapter = platillosAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}