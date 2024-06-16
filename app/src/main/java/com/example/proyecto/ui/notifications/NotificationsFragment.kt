package com.example.proyecto.ui.notifications

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto.Clientes
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentNotificationsBinding
import java.util.ArrayList

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    private lateinit var nombre: EditText
    private lateinit var apellido: EditText
    private lateinit var foto: ImageView
    private lateinit var btnTomar: Button
    private lateinit var btnAgregar: Button
    private lateinit var btnMostrar: Button

    private var bitmapArray = ArrayList<Bitmap>()
    private var nombreApellidoArray = ArrayList<Pair<String, String>>()
    private var Index = 0 //Este es el indice actual de nuestro arreglo


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
        val view: View = binding.root

        foto = view.findViewById(R.id.imageVFoto)
        btnTomar = view.findViewById(R.id.btnTomarFotos)
        nombre = view.findViewById(R.id.edtNombre)
        apellido = view.findViewById(R.id.edtApellidos)

        //Metodos
        btnTomar.setOnClickListener {
//            Instancia para abrir la camara
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            Lo que sucede cuando la camara regresa un resultado
            responseLauncher.launch(intent)
        }

        btnAgregar = view.findViewById<Button>(R.id.btnAgregar) ?: Button(requireContext()) // Inicializa con un botón vacío si no se encuentra
        btnMostrar = view.findViewById<Button>(R.id.btnConsultar) ?: Button(requireContext()) // Inicializa con un botón vacío si no se encuentra

        btnAgregar.setOnClickListener {
            agregarNombreApellido()
        }

        btnMostrar.setOnClickListener {
            mostrarNombreApellido()
        }


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



        return view
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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

     */

    // Método para agregar el nombre y apellido a la lista
    private fun agregarNombreApellido() {
        val nombreString = nombre.text.toString()
        val apellidoString = apellido.text.toString()
        nombreApellidoArray.add(Pair(nombreString, apellidoString))
        Toast.makeText(requireContext(), "Nombre y apellido agregados", Toast.LENGTH_SHORT).show()
    }

    // Método para mostrar los nombres y apellidos almacenados
    private fun mostrarNombreApellido() {
        val stringBuilder = StringBuilder()
        for ((nombre, apellido) in nombreApellidoArray) {
            stringBuilder.append("Nombre: $nombre\n")
            stringBuilder.append("Apellido: $apellido\n\n")
        }
        Toast.makeText(requireContext(), stringBuilder.toString(), Toast.LENGTH_LONG).show()
    }



    //Variable que se ejecuta una vez que tome la foto
    private val responseLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->
        if(activityResult.resultCode == AppCompatActivity.RESULT_OK){
            Toast.makeText(requireContext(),"Fotografia tomada", Toast.LENGTH_SHORT).show()
            val extras = activityResult.data?.extras
            val bitmap = extras?.get("data") as Bitmap?
            if(bitmap != null){
                bitmapArray.add(bitmap) //Agregamos a la lista de bitmapArray
                foto.setImageBitmap(bitmap) // Mostrar la ultima foto tomada
                Index = bitmapArray.size - 1 //Actualizamos el indice al ultimo elemento
            }else {
                Toast.makeText(requireContext(), "Error al obtener la imagen", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(requireContext(),"Proceso cancelado", Toast.LENGTH_SHORT).show()
        }
    }//responseLauncher

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}