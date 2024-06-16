package com.example.proyecto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Button
import android.widget.Toast

class ReservaCliente : AppCompatActivity() {

    private lateinit var editTextFecha: EditText
    private lateinit var editTextHora: EditText
    private lateinit var editTextClientes: EditText
    private lateinit var editTextNombreCliente: EditText
    private lateinit var botonReservar: Button

    // Arreglo para almacenar las reservaciones
    private val reservaciones = mutableListOf<Reservacion>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva_cliente)

        editTextFecha = findViewById(R.id.editTextFecha)
        editTextHora = findViewById(R.id.editTextHora)
        editTextClientes = findViewById(R.id.editTextClientes)
        editTextNombreCliente = findViewById(R.id.editTextNombreCliente)
        botonReservar = findViewById(R.id.botonReservar)

        botonReservar.setOnClickListener { hacerReservacion() }
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

            Toast.makeText(this, "Reservación realizada", Toast.LENGTH_SHORT).show()

            // Iniciar ReservaAdmin y pasar las reservaciones
            iniciarReservaAdmin()
        } else {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun iniciarReservaAdmin() {
        val intent = Intent(this, ReservaAdmin::class.java)
        intent.putExtra("reservaciones", ArrayList(reservaciones))
        startActivity(intent)
    }
}