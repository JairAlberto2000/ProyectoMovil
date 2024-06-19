package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.databinding.ActivityPedidosAdapterBinding
import com.example.proyecto.databinding.ActivityPlatillosAdapterBinding

class PedidosAdapter(
    private var pedidos: List<Pedido>,
    private val clickListener: PlatilloClickListener
) : RecyclerView.Adapter<PedidosAdapter.PedidoViewHolder>() {

    var numeroPedido: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val binding =
            ActivityPedidosAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PedidoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val pedido = pedidos[position]
        holder.bind(pedido)
    }

    override fun getItemCount(): Int {
        return pedidos.size
    }

    fun actualizarLista(nuevaLista: List<Pedido>) {
        pedidos = nuevaLista
        notifyDataSetChanged()
    }

    inner class PedidoViewHolder(private val binding: ActivityPedidosAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pedido: Pedido) {
            // Puedes personalizar la visualización del pedido aquí
            binding.tvNumero.text = "Pedido ${adapterPosition + 1}"
            numeroPedido=adapterPosition+1
            binding.tvNombre.text = "Nombre: ${pedido.nombre}"
            binding.tvPrecio.text = "Total: ${pedido.total}"

            itemView.setOnClickListener {
                // Manejar clic en un pedido: abrir la actividad de detalles
                // Puedes pasar información específica del pedido si es necesario
                clickListener.onPlatilloClick(pedido)
            }
        }


    }

    interface PlatilloClickListener {
        fun onPlatilloClick(pedido: Pedido)
    }
}
