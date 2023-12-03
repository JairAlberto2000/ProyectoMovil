package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class Detalle : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)
        val id = Integer.parseInt(intent.getStringExtra("ID").toString())

        val Nombre: TextView = findViewById(R.id.tvNombrePrenda)
        val Detalles: TextView = findViewById(R.id.tvDetalles)
        val Imagen: ImageView = findViewById(R.id.imgPrenda)

        when(id){
            1 -> {
                Nombre.setText(R.string.nombre_Platillo_1)
                Detalles.setText(R.string.detalle_Platillo_1)
                Imagen.setImageResource(R.drawable.img1)
            }
            2 -> {
                Nombre.setText(R.string.nombre_Platillo_2)
                Detalles.setText(R.string.detalle_Platillo_2)
                Imagen.setImageResource(R.drawable.img2)
            }
            3 -> {
                Nombre.setText(R.string.nombre_Platillo_3)
                Detalles.setText(R.string.detalle_Platillo_3)
                Imagen.setImageResource(R.drawable.img3)
            }
            4 -> {
                Nombre.setText(R.string.nombre_Platillo_4)
                Detalles.setText(R.string.detalle_Platillo_4)
                Imagen.setImageResource(R.drawable.img4)
            }
            5 -> {
                Nombre.setText(R.string.nombre_Platillo_5)
                Detalles.setText(R.string.detalle_Platillo_5)
                Imagen.setImageResource(R.drawable.img5)
            }
            6 -> {
                Nombre.setText(R.string.nombre_Bebida_1)
                Detalles.setText(R.string.detalle_Bebida_1)
                Imagen.setImageResource(R.drawable.bebida1)
            }
            7 -> {
                Nombre.setText(R.string.nombre_Bebida_2)
                Detalles.setText(R.string.detalle_Bebida_2)
                Imagen.setImageResource(R.drawable.bebida2)
            }
            8 -> {
                Nombre.setText(R.string.nombre_Bebida_3)
                Detalles.setText(R.string.detalle_Bebida_3)
                Imagen.setImageResource(R.drawable.bebida3)
            }
            9 -> {
                Nombre.setText(R.string.nombre_Bebida_4)
                Detalles.setText(R.string.detalle_Bebida_4)
                Imagen.setImageResource(R.drawable.bebida4)
            }
            10 -> {
                Nombre.setText(R.string.nombre_Bebida_5)
                Detalles.setText(R.string.detalle_Bebida_5)
                Imagen.setImageResource(R.drawable.bebida5)
            }
        }
    }
}