package com.example.proyecto

class Clientes (
    //var CleinteID: int,
    var nombre: String,
    var apellidoPaterno: String,
    var ApellidoMaterno: String,
    var NumeroTelefonico: String,
    var CorreoEletronico: String,
){
    fun getMNombre(): String = nombre
    fun getMapellidoPaterno(): String = apellidoPaterno
    fun getMapellidoMaterno(): String = ApellidoMaterno
    fun getMNumeroTelefonico(): String = NumeroTelefonico
    fun getMCorreoEletronico(): String = CorreoEletronico

    fun setMNombre(value: String) {
        nombre = value
    }
    fun getMapellidoPaterno(value: String) {
        apellidoPaterno = value
    }
    fun getMapellidoMaterno(value: String) {
        ApellidoMaterno = value
    }
    fun gatMNumeroTelefonico(value: String) {
        NumeroTelefonico = value
    }
    fun getMCorreoEletronico(value: String) {
        CorreoEletronico = value
    }
}