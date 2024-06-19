package com.example.proyecto.ui.agregarMenuAdm

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.proyecto.Ingreso
import com.example.proyecto.MainActivity
import com.example.proyecto.MyApp
import com.example.proyecto.Platillo
import com.example.proyecto.PlatillosViewModel
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentAgregarMenuAdmBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AgregarMenuAdmFragment : Fragment() {
    private var _binding: FragmentAgregarMenuAdmBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    private lateinit var db: FirebaseFirestore

    var id=""

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

        firebaseAuth = Firebase.auth
        // Inicializar Firestore
        db = FirebaseFirestore.getInstance()
        //platillosViewModel = ViewModelProvider(requireActivity()).get(PlatillosViewModel::class.java)
       // platillosViewModel = ViewModelProvider(this).get(PlatillosViewModel::class.java)
        //sharedViewModel = ViewModelProvider(this).get(PlatillosViewModel::class.java)


        binding.btnConsultar.setOnClickListener {
            val nombre = binding.etNombrePlatillo.text.toString()
            buscarPlatilloPorNombre(nombre)
        }

        binding.btnActualizar.setOnClickListener {
            val nombre = binding.etNombrePlatillo.text.toString()
            val precio = binding.etPrecioPlatillo.text.toString().toDouble()
            val info = binding.etInfoNutricional.text.toString()
            editarPlatillo(id, nombre, precio, info)
        }
        binding.btnGuardar.setOnClickListener {
            val nombre = binding.etNombrePlatillo.text.toString()
            val precio = binding.etPrecioPlatillo.text.toString().toDouble()
            val info = binding.etInfoNutricional.text.toString()

            // Create a new user with a first and last name
            val platillo = hashMapOf(
                "Nombre" to nombre,
                "Costo" to precio,
                "Info" to info,
            )

            db.collection("Platillos")
                .add(platillo)
                .addOnSuccessListener { documentReference ->
                    val pedidoId = documentReference.id
                    Toast.makeText(requireContext(), "Platillo agregado correctamente", Toast.LENGTH_SHORT).show()

                    // Guarda el ID en el documento
                    db.collection("Platillos").document(pedidoId).update("id", pedidoId)
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error adding document", e)
                }


            // Limpiar campos después de agregar un platillo
            binding.etNombrePlatillo.text.clear()
            binding.etPrecioPlatillo.text.clear()
            binding.etInfoNutricional.text.clear()

            // Informar al usuario que se agregó el platillo al pedido
            Toast.makeText(requireContext(), "Platillo agregado al menu principal con exito", Toast.LENGTH_SHORT).show()
        }

        binding.btnCliente.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }


        binding.btnCerrarSesion2.setOnClickListener {
            val intent = Intent(requireContext(), Ingreso::class.java)
            startActivity(intent)
        }
    }

    private fun buscarPlatilloPorNombre(nombre: String) {
        db.collection("Platillos")
            .whereEqualTo("Nombre", nombre)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    val platillo1 = document.toObject(Platillo::class.java)
                    if (platillo1 != null) {
                        id = document.id  // Asignar el ID del documento encontrado
                        binding.etNombrePlatillo.setText(platillo1.Nombre)
                        binding.etPrecioPlatillo.setText(platillo1.Costo.toString())
                        binding.etInfoNutricional.setText(platillo1.Info)
                        Toast.makeText(requireContext(), "Platillo encontrado", Toast.LENGTH_SHORT).show()
                        return@addOnSuccessListener
                    }
                }
                // Si no se encuentra ningún platillo con ese nombre
                Toast.makeText(requireContext(), "No se encontró ningún platillo con ese nombre", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error al buscar platillo por nombre", e)
                Toast.makeText(requireContext(), "Error al buscar platillo por nombre", Toast.LENGTH_SHORT).show()
            }
    }


    fun editarPlatillo(documentId: String, nombre: String, precio: Double, info: String) {
        val platilloUpdates = mapOf(
            "Nombre" to nombre,
            "Costo" to precio,
            "Info" to info
        )

        db.collection("Platillos").document(documentId)
            .update(platilloUpdates)
            .addOnSuccessListener {
                Log.d(TAG, "Platillo actualizado correctamente")
                Toast.makeText(requireContext(), "Platillo actualizado correctamente", Toast.LENGTH_SHORT).show()

                // Limpiar campos después de agregar un platillo
                binding.etNombrePlatillo.text.clear()
                binding.etPrecioPlatillo.text.clear()
                binding.etInfoNutricional.text.clear()

            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error al actualizar platillo", e)
                Toast.makeText(requireContext(), "Error al actualizar platillo", Toast.LENGTH_SHORT).show()
            }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}