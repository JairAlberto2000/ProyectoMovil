package com.example.proyecto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReservaAdmin : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adaptador: Adaptador
    private var reservaciones: List<Reservacion> = mutableListOf() // Lista de reservaciones

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva_admin)

        recyclerView = findViewById(R.id.recyclerView)
        adaptador = Adaptador(reservaciones)
        recyclerView.adapter = adaptador
        recyclerView.layoutManager = LinearLayoutManager(this)


    }
}