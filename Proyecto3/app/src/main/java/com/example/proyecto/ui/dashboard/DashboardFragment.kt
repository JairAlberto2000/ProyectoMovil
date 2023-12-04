package com.example.proyecto.ui.dashboard

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.DetallePlatilloActivity
import com.example.proyecto.MyApp
import com.example.proyecto.Platillo
import com.example.proyecto.PlatillosAdapter
import com.example.proyecto.PlatillosViewModel
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentDashboardBinding
import com.example.proyecto.databinding.FragmentPedidosAdmBinding

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val platillosViewModel: PlatillosViewModel by lazy {
        (requireActivity().application as MyApp).platillosViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val platillosAdapter = PlatillosAdapter(emptyList(), object : PlatillosAdapter.PlatilloClickListener {
            override fun onPlatilloClick(platillo: Platillo) {
                // Manejar clic en un platillo: abrir la actividad de detalles
                val intent = Intent(requireContext(), DetallePlatilloActivity::class.java)
                intent.putExtra("nombrePlatillo", platillo.nombre)
                intent.putExtra("precioPlatillo", platillo.precio)
                startActivity(intent)
            }
        })

        recyclerView.adapter = platillosAdapter

        // Observa los cambios en LiveData y actualiza el adaptador
        platillosViewModel.obtenerPlatillos().observe(viewLifecycleOwner, Observer { platillos ->
            platillosAdapter.actualizarLista(platillos)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
