package com.example.proyecto

import java.io.Serializable

data class Reservacion(
    val id: String,
    val fecha: String,
    val hora: String,
    val numeroDeInvitados: Int,
    val nombreCliente: String
) : Serializable