package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.databinding.ActivityFavoritosAdapterBinding
import com.example.proyecto.databinding.ActivityPedidosHistorialAdapterBinding


class FavoritosAdapter(
    private var pedidos: List<Fav>,
    private val clickListener: PlatilloClickListener
) : RecyclerView.Adapter<FavoritosAdapter.PedidoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val binding = ActivityFavoritosAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PedidoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val pedido = pedidos[position]
        holder.bind(pedido)
    }

    override fun getItemCount(): Int {
        return pedidos.size
    }

    fun actualizarLista(nuevaLista: List<Fav>) {
        pedidos = nuevaLista
        notifyDataSetChanged()
    }

    inner class PedidoViewHolder(private val binding: ActivityFavoritosAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pedido: Fav) {
            // Puedes personalizar la visualización del pedido aquí
            binding.tvNombre.text = "Platillo favorito: ${adapterPosition + 1}"
            binding.tvPrecio.text = "Precio: ${calcularTotal(pedido)}"

            itemView.setOnClickListener {
                // Manejar clic en un pedido: abrir la actividad de detalles
                // Puedes pasar información específica del pedido si es necesario
                clickListener.onPlatilloClick(pedido.platillos.firstOrNull(), adapterPosition)
            }
        }

        private fun calcularTotal(pedido: Fav): Double {
            // Puedes personalizar la lógica para calcular el total del pedido
            return pedido.platillos.sumByDouble {it.Costo }
        }
    }

    interface PlatilloClickListener {
        fun onPlatilloClick(platillo: Platillo?, position: Int)
    }
}
