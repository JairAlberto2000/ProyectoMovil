package com.example.proyecto

data class Pedido(
    val platillos: MutableList<Platillo> = mutableListOf()
)