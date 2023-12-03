package com.example.proyecto.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto.Clientes
import com.example.proyecto.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        /*val spinner: Spinner = binding.spClinica
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.lista_clinicas,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter*/


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val clientes = Clientes("default", "apellido Paterno", "Apellido Materno", "Numero Telefonico", "Correo Eletronico")
        binding.btnRegistrar.setOnClickListener{
            if(binding.edtNombre.text.isNotEmpty() || binding.edtNombre.text.isNotBlank()){
                if(binding.edtApellidoPaterno.text.isNotEmpty() || binding.edtApellidoPaterno.text.isNotBlank()){
                    if(binding.edtApellidoMaterno.text.isNotEmpty() || binding.edtApellidoMaterno.text.isNotBlank()){
                        if(binding.edtNumeroTelefonico.text.isNotEmpty() || binding.edtNumeroTelefonico.text.isNotBlank()) {
                            if(binding.edtCorreoEletronico.text.isNotEmpty() || binding.edtCorreoEletronico.text.isNotBlank()) {

                                val nombre = binding.edtNombre.text.toString()
                                val apellidoPaterno = binding.edtApellidoPaterno.text.toString()
                                val apellidoMaterno = binding.edtApellidoMaterno.text.toString()
                                val numeroTelefonico = binding.edtNumeroTelefonico.text.toString()
                                val correoEletronico = binding.edtCorreoEletronico.text.toString()
                                /*
                            var alergia = true
                            if(binding.chAlergias.isChecked){
                                alergia = true
                            }else{
                                alergia = false
                            }*/
                                /*
                                                            val clinica = binding.spClinica.selectedItem.toString()
                                                            clientes.setMNombre(nombre)
                                                            clientes.getMapellidoPaterno(apellidoPaterno)
                                                            clientes.getMapellidoMaterno(apellidoMaterno)
                                                            clientes.gatMNumeroTelefonico(numeroTelefonico)
                                                            clientes.getMCorreoEletronico(correoEletronico)
                                */
                                Toast.makeText(
                                    requireContext(),
                                    "Cliente " + nombre + " registrado\n" + nombre
                                            + "\n" + apellidoPaterno + "\n" + apellidoMaterno + "\n" + numeroTelefonico + "\n" + correoEletronico,
                                    Toast.LENGTH_SHORT
                                ).show()

                            }else{
                                Toast.makeText(requireContext(), "Error, escriba su correo eletronico para continuar",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            Toast.makeText(requireContext(), "Error, escriba su numero para continuar",
                                Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(requireContext(), "Error, escriba su Apellido materno para continuar",
                            Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(requireContext(), "Error, escriba su apellido  paterno para continuar",
                        Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireContext(), "Error, escriba su nombre para continuar", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnConsultar.setOnClickListener{
            val nombre = clientes.getMNombre()
            val apellidoPaterno = clientes.getMapellidoPaterno()
            val apellidoMaterno = clientes.getMapellidoMaterno()
            var numeroTelefonico = clientes.getMNumeroTelefonico()
            val correoEletronico = clientes.getMCorreoEletronico()

            if(nombre.equals("default")){
                Toast.makeText(requireContext(), "Error, primero registre al paciente", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "Paciente: " + "\nNombre: " +nombre+"\nApellido paterno: "+apellidoPaterno
                        +"\nApellido materno: "+apellidoMaterno+"\nNumeroTelefonico: "+numeroTelefonico
                        +"\nCorreo eletronico: "+correoEletronico,
                    Toast.LENGTH_LONG).show()
            }
        }
    }//onViewCreated


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}