package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.databinding.ActivityPedidosAdapterBinding
import com.example.proyecto.databinding.ActivityPedidosHistorialAdapterBinding

class PedidosHistorialAdapter (private var pedidos: List<HistorialP>,
                               private val clickListener: PlatilloClickListener
) : RecyclerView.Adapter<PedidosHistorialAdapter.PedidoViewHolder>() {

    var numeroPedido: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val binding =
            ActivityPedidosHistorialAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PedidoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val pedido = pedidos[position]
        holder.bind(pedido)
    }

    override fun getItemCount(): Int {
        return pedidos.size
    }

    fun actualizarLista(nuevaLista: List<HistorialP>) {
        pedidos = nuevaLista
        notifyDataSetChanged()
    }

    inner class PedidoViewHolder(private val binding: ActivityPedidosHistorialAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pedido: HistorialP) {
            // Puedes personalizar la visualización del pedido aquí
            binding.tvNombre.text = "Pedido ${adapterPosition + 1}"
            numeroPedido=adapterPosition+1
            binding.tvPrecio.text = "Total: ${calcularTotal(pedido)}"

            itemView.setOnClickListener {
                // Manejar clic en un pedido: abrir la actividad de detalles
                // Puedes pasar información específica del pedido si es necesario
                clickListener.onPlatilloClick(pedido.platillos.firstOrNull(), adapterPosition)
            }
        }

        private fun calcularTotal(pedido: HistorialP): Double {
            // Puedes personalizar la lógica para calcular el total del pedido
            return pedido.platillos.sumByDouble { it.precio }
        }
    }

    interface PlatilloClickListener {
        fun onPlatilloClick(platillo: Platillo?, position: Int)
    }

}