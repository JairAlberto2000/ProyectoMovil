package com.example.proyecto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlatillosViewModel : ViewModel() {

    private val platillos = mutableListOf<Platillo>()
    private val pedidos = mutableListOf<Platillo>()

    fun getPlatillos(): List<Platillo> {
        return platillos
    }

    fun agregarPlatillo(platillo: Platillo) {
        platillos.add(platillo)
    }

    fun getPedidos(): List<Platillo> {
        return pedidos
    }

    fun agregarPlatilloAlPedido(platillo: Platillo) {
        pedidos.add(platillo)
    }

}
