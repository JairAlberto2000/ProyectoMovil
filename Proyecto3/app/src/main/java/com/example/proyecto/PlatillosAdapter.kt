package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.proyecto.databinding.ActivityPlatillosAdapterBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class PlatillosAdapter(
    private var platillos: List<Platillo>,
    private val clickListener: PlatilloClickListener
) : RecyclerView.Adapter<PlatillosAdapter.PlatilloViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatilloViewHolder {
        val binding = ActivityPlatillosAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlatilloViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlatilloViewHolder, position: Int) {
        val platillo = platillos[position]
        holder.bind(platillo)
    }

    override fun getItemCount(): Int {
        return platillos.size
    }

    fun actualizarLista(nuevaLista: List<Platillo>) {
        platillos = nuevaLista
        notifyDataSetChanged()
    }

    inner class PlatilloViewHolder(private val binding: ActivityPlatillosAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(platillo: Platillo) {
            binding.tvNombre.text = "Nombre: ${platillo.Nombre}"
            binding.tvCosto.text = "Costo: ${platillo.Costo}"
            binding.tvInfo.text = "Info: ${platillo.Info}"

            itemView.setOnClickListener {
                clickListener.onPlatilloClick(platillo)
            }
        }
    }

    interface PlatilloClickListener {
        fun onPlatilloClick(platillo: Platillo)
    }
}
