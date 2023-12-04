package com.example.proyecto
data class Producto(val id: Int, val nombre: String, val cantidad: Int) {
    override fun toString(): String {
        return "$id - $nombre - $cantidad"
    }
}
