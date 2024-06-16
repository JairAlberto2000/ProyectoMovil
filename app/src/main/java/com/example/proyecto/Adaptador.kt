package com.example.proyecto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador(private val reservaciones: List<Reservacion>) : RecyclerView.Adapter<Adaptador.ReservacionViewHolder>() {

    class ReservacionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun enlazar(reservacion: Reservacion) {
            itemView.findViewById<TextView>(R.id.textoFecha).text = "Fecha: ${reservacion.fecha}"
            itemView.findViewById<TextView>(R.id.textoHora).text = "Hora: ${reservacion.hora}"
            itemView.findViewById<TextView>(R.id.textoInvitados).text = "Invitados: ${reservacion.numeroDeInvitados}"
            itemView.findViewById<TextView>(R.id.textoNombreCliente).text = "Cliente: ${reservacion.nombreCliente}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservacionViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_reservacion, parent, false)
        return ReservacionViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ReservacionViewHolder, position: Int) {
        holder.enlazar(reservaciones[position])
    }

    override fun getItemCount() = reservaciones.size
}