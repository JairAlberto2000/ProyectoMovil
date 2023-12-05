package com.example.proyecto.ui.reservasCliente

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyecto.R
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import com.example.proyecto.Reservacion

class ReservasClienteFragment : Fragment() {

    private lateinit var editTextFecha: EditText
    private lateinit var editTextHora: EditText
    private lateinit var editTextClientes: EditText
    private lateinit var editTextNombreCliente: EditText
    private lateinit var botonReservar: Button

    // Arreglo para almacenar las reservaciones
    private val reservaciones = mutableListOf<Reservacion>()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_reserva_cliente, container, false)

        editTextFecha = view.findViewById(R.id.editTextFecha)
        editTextHora = view.findViewById(R.id.editTextHora)
        editTextClientes = view.findViewById(R.id.editTextClientes)
        editTextNombreCliente = view.findViewById(R.id.editTextNombreCliente)
        botonReservar = view.findViewById(R.id.botonReservar)

        botonReservar.setOnClickListener { hacerReservacion() }

        return view
    }

    private fun hacerReservacion() {
        val fecha = editTextFecha.text.toString()
        val hora = editTextHora.text.toString()
        val invitados = editTextClientes.text.toString().toIntOrNull()
        val nombreCliente = editTextNombreCliente.text.toString()

        if (fecha.isNotEmpty() && hora.isNotEmpty() && invitados != null && nombreCliente.isNotEmpty()) {
            val nuevaReservacion = Reservacion(
                id = System.currentTimeMillis().toString(),
                fecha = fecha,
                hora = hora,
                numeroDeInvitados = invitados,
                nombreCliente = nombreCliente
            )

            reservaciones.add(nuevaReservacion)

            Toast.makeText(requireContext(), "Reservación realizada", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}