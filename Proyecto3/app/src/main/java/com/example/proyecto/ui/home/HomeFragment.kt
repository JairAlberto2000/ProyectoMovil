package com.example.proyecto.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto.FavoritosCliente
import com.example.proyecto.Ingreso
import com.example.proyecto.MainActivity
import com.example.proyecto.MapsActivity
import com.example.proyecto.Platillo
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it

            binding.btnCerrarSesion.setOnClickListener {
                val intent = Intent(requireContext(), Ingreso::class.java)
                startActivity(intent)
            }
            // Referencia al nuevo botón "btnMostrarRuta"
            binding.btnMapa.setOnClickListener {
                val intent = Intent(requireContext(), MapsActivity::class.java)
                startActivity(intent)
            }
        }
        return root
    }






    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}