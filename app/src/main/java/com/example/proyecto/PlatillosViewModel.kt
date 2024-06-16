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


    //pedidos
    private val _pedidos = MutableLiveData<List<Pedido>>(emptyList())
    val pedidos: LiveData<List<Pedido>> get() = _pedidos

    //historial pedidos
    private val _pedidosH = MutableLiveData<List<HistorialP>>(emptyList())
    val pedidosH: LiveData<List<HistorialP>> get() = _pedidosH as LiveData<List<HistorialP>>

    //favoritos

    private val _favoritos = MutableLiveData<List<Fav>>(emptyList())
    val favoritos: LiveData<List<Fav>> get() = _favoritos as LiveData<List<Fav>>
// ...

    //platillos
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

    //historial del cliente
    fun obtenerPedidos1(): LiveData<List<HistorialP>> {
        Log.d("SharedViewModel", "Tamaño de pedidos: ${pedidosH.value}")
        return pedidosH
    }

    fun agregarPlatilloAlPedido1(platillo: Platillo) {
        val listaActual = _pedidosH.value.orEmpty().toMutableList()
        val ultimoPedido = listaActual.lastOrNull()

        if (ultimoPedido != null && ultimoPedido.platillos.isEmpty()) {
            // Si el último pedido está vacío, agregamos el platillo al mismo pedido
            ultimoPedido.platillos.add(platillo)
        } else {
            // Si el último pedido está lleno o no existe, creamos un nuevo pedido
            val nuevoPedido = HistorialP(mutableListOf(platillo))
            listaActual.add(nuevoPedido)
        }

        // Actualizamos la lista de pedidos en el ViewModel
        _pedidosH.value = listaActual.toList()
    }

    //favoritoscliente

    fun obtenerFavoritos(): LiveData<List<Fav>> {
        Log.d("SharedViewModel", "Tamaño de pedidos: ${favoritos.value}")
        return favoritos
    }

    fun agregarPlatilloAfavoritos(platillo: Platillo) {
        val listaActual = _favoritos.value.orEmpty().toMutableList()
        val ultimoPedido = listaActual.lastOrNull()

        if (ultimoPedido != null && ultimoPedido.platillos.isEmpty()) {
            // Si el último pedido está vacío, agregamos el platillo al mismo pedido
            ultimoPedido.platillos.add(platillo)
        } else {
            // Si el último pedido está lleno o no existe, creamos un nuevo pedido
            val nuevoPedido = Fav(mutableListOf(platillo))
            listaActual.add(nuevoPedido)
        }

        // Actualizamos la lista de pedidos en el ViewModel
        _favoritos.value = listaActual.toList()
    }


    //------------------------------------otras

    private fun logList(tag: String, list: List<Platillo>) {
        Log.d("SharedViewModel", "$tag: ${list.joinToString { it.nombre.toString() }}")
    }

    fun eliminarPedido(indice: Int) {
        val listaActual = _pedidos.value.orEmpty().toMutableList()

        if (indice in 0 until listaActual.size) {
            // Si el índice es válido, eliminamos el pedido de la lista
            listaActual.removeAt(indice)

            // Actualizamos la lista de pedidos en el ViewModel
            _pedidos.value = listaActual.toList()
        }
    }
}
