package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class Activity_Detalle : AppCompatActivity() {

    val tamanio = 50
    var Productos1 = Array<Productos?>(tamanio){null}

    private val Productos = mutableMapOf<Int, Productos >()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)
        val id = Integer.parseInt(intent.getStringExtra("ID").toString())

        val Nombre: TextView = findViewById(R.id.tvNombrePrenda)
        val Detalles: TextView = findViewById(R.id.tvDetalles)
        val Imagen: ImageView = findViewById(R.id.imgPrenda)
        val cantidad: TextView = findViewById(R.id.tvCantidad)
        val ID: TextView = findViewById(R.id.editID)
        val add: Button = findViewById(R.id.btnComprar)

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
        }//Fin when

        fun Agregar(view: View?){
            if (Nombre.text.isNotEmpty() && ID.text.isNotEmpty()){

                val id = ID.text.toString().toInt()

                if (!Productos.containsKey(id)){

                    val Productos1 = Productos("","",0)

                    Productos1.Producto = Nombre.text.toString()
                    Productos1.Cantidad = cantidad.text.toString()
                    Productos1.Id = id //se esta asignando un valor al atributo id de un objeto
                    Productos[id] = Productos1 //Se agrega una motocicleta al mapa

                    Toast.makeText(this, "Registrada Correctamente.",
                        Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(this,"Esta lleno el arreglo",
                        Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"No se pudo registrar.",
                    Toast.LENGTH_LONG).show()
            }//else
        }//if


        fun onClick(v: View){
            when(v?.id){
                R.id.btnComprar ->{
                    Agregar(v)

                }

            }
        }


    }
}