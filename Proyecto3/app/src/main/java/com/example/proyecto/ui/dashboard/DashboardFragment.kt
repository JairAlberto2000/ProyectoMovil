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
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
//import com.example.proyecto.Detalle
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    fun splash(prod: String){
        val progressDialog = Dialog(requireContext())
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.setCancelable(false)
        progressDialog.show()
        val progressBar = progressDialog.findViewById<ProgressBar>(R.id.progressBarDialog)
        val handler = Handler(Looper.getMainLooper())
        for(i in 1..100){
            handler.postDelayed({
                val progress = (i+1)
                progressBar.progress = progress
                if(progress == 100){
                    //Start Activity
                    //val intent = Intent(requireContext(), Detalle::class.java)
                    //intent.putExtra("ID", prod)
                    progressDialog.dismiss()
                    //startActivity(intent)
                }
            }, i * 20L)
        }
    }
    override fun onViewCreated(view:View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        binding.imgProducto1.setOnClickListener{
            splash("1")
        }
        binding.imgProducto2.setOnClickListener{
            splash("2")
        }
        binding.imgProducto3.setOnClickListener{
            splash("3")
        }
        binding.imgProducto4.setOnClickListener{
            splash("4")
        }
        binding.imgProducto5.setOnClickListener{
            splash("5")
        }
        binding.imgProducto6.setOnClickListener{
            splash("6")
        }
        binding.imgProducto7.setOnClickListener{
            splash("7")
        }
        binding.imgProducto8.setOnClickListener{
            splash("8")
        }
        binding.imgProducto9.setOnClickListener{
            splash("9")
        }
        binding.imgProducto10.setOnClickListener{
            splash("10")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}