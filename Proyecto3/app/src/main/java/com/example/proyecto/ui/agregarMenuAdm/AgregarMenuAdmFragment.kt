package com.example.proyecto.ui.agregarMenuAdm

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto.DetallePlatilloActivity
import com.example.proyecto.MainActivity
import com.example.proyecto.MyApp
import com.example.proyecto.Platillo
import com.example.proyecto.PlatillosViewModel
import com.example.proyecto.databinding.FragmentAgregarMenuAdmBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AgregarMenuAdmFragment : Fragment() {
    private var _binding: FragmentAgregarMenuAdmBinding? = null
    private val binding get() = _binding!!

    //private lateinit var platillosViewModel: PlatillosViewModel
    //private lateinit var platillosViewModel: PlatillosViewModel
    //private lateinit var sharedViewModel: PlatillosViewModel

    val platillosViewModel: PlatillosViewModel by lazy {
        (requireActivity().application as MyApp).platillosViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgregarMenuAdmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //platillosViewModel = ViewModelProvider(requireActivity()).get(PlatillosViewModel::class.java)
       // platillosViewModel = ViewModelProvider(this).get(PlatillosViewModel::class.java)
        //sharedViewModel = ViewModelProvider(this).get(PlatillosViewModel::class.java)


        binding.btnGuardar.setOnClickListener {
            val nombre = binding.etNombrePlatillo.text.toString()
            val precio = binding.etPrecioPlatillo.text.toString().toDouble()

            val platillo = Platillo(nombre, precio)
            platillosViewModel.agregarPlatillo(platillo)

            // Limpiar campos después de agregar un platillo
            binding.etNombrePlatillo.text.clear()
            binding.etPrecioPlatillo.text.clear()
        }

        binding.btnCliente.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}