package com.example.proyecto.ui.reservasAdm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyecto.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.Adaptador
import com.example.proyecto.RepositorioReservaciones
import com.example.proyecto.Reservacion

class ReservasAdmFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adaptador: Adaptador
    private var reservaciones: MutableList<Reservacion> = mutableListOf() // Lista de reservaciones

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reservas_adm, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        adaptador = Adaptador(reservaciones)
        recyclerView.adapter = adaptador
        recyclerView.layoutManager = LinearLayoutManager(context)

        cargarReservaciones()

        return view
    }

    private fun cargarReservaciones() {
        // Carga las reservaciones desde el singleton
        reservaciones.clear()
        reservaciones.addAll(RepositorioReservaciones.reservaciones)
        adaptador.notifyDataSetChanged()
    }
}