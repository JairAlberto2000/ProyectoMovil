package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.databinding.ActivityHistorialClienteBinding
import com.example.proyecto.databinding.ActivityPedidosAdapterBinding
import com.example.proyecto.databinding.ActivityPedidosHistorialAdapterBinding

class PedidosHistorialAdapter(
    private var pedidos: List<HistorialP>,
    private val clickListener: PlatilloClickListener
) : RecyclerView.Adapter<PedidosHistorialAdapter.PedidoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val binding =ActivityHistorialClienteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class PedidoViewHolder(private val binding: ActivityHistorialClienteBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pedido: HistorialP) {
            binding.tvNombre.text = "Pedido #${adapterPosition + 1}"
            binding.tvTotal.text = "Total: ${calcularTotal(pedido)}"

            itemView.setOnClickListener {
                clickListener.onPlatilloClick(pedido.platillos.firstOrNull(), adapterPosition)
            }
        }

        private fun calcularTotal(pedido: HistorialP): Double {
            return pedido.platillos.sumByDouble { it.Costo.toDouble() }
        }
    }

    interface PlatilloClickListener {
        fun onPlatilloClick(platillo: Platillo?, position: Int)
    }
}
