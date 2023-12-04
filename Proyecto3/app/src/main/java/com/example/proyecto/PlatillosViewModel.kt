package com.example.proyecto

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlatillosViewModel :ViewModel() {
    private val _platillos = MutableLiveData<List<Platillo>>(emptyList())
    val platillos: LiveData<List<Platillo>> get() = _platillos


    private val _pedidos = MutableLiveData<List<Pedido>>(emptyList())
    val pedidos: LiveData<List<Pedido>> get() = _pedidos as LiveData<List<Pedido>>

// ...

    fun obtenerPlatillos(): LiveData<List<Platillo>> {
        Log.d("SharedViewModel", "Tamaño de platillos: ${platillos.value}")
        return platillos
    }

    fun agregarPlatillo(platillo: Platillo) {
        val listaActual = _platillos.value.orEmpty().toMutableList()
        listaActual.add(platillo)
        _platillos.value = listaActual.toList()
    }

    fun obtenerPedidos(): LiveData<List<Pedido>> {
        Log.d("SharedViewModel", "Tamaño de pedidos: ${pedidos.value}")
        return pedidos
    }

    fun agregarPlatilloAlPedido(platillo: Platillo) {
        val listaActual = _pedidos.value.orEmpty().toMutableList()
        val ultimoPedido = listaActual.lastOrNull()

        if (ultimoPedido != null && ultimoPedido.platillos.isEmpty()) {
            // Si el último pedido está vacío, agregamos el platillo al mismo pedido
            ultimoPedido.platillos.add(platillo)
        } else {
            // Si el último pedido está lleno o no existe, creamos un nuevo pedido
            val nuevoPedido = Pedido(mutableListOf(platillo))
            listaActual.add(nuevoPedido)
        }

        // Actualizamos la lista de pedidos en el ViewModel
        _pedidos.value = listaActual.toList()
    }


    private fun logList(tag: String, list: List<Platillo>) {
        Log.d("SharedViewModel", "$tag: ${list.joinToString { it.nombre.toString() }}")
    }
}
