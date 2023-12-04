package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class Ingreso : AppCompatActivity() {

    private lateinit var  email: EditText
    private lateinit var password: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso)
        //Asociar instancias con componentes fraficos
        email = findViewById(R.id.edtEmail)
        password = findViewById(R.id.btnCancelar)

    }//onCreaate

    fun onClick(view: View?){
        when(view?.id){
            R.id.btnAceptar -> aceptar()
            R.id.button2 -> cancelar()
        }
    }//onClick

    private fun aceptar(){
        //validar que las cajas de texto no esten vacias o con espacion en blanco
        if(email.text.isNotEmpty() && email.text.isNotBlank() &&
            password.text.isNotEmpty() && password.text.isNotBlank()){
            //validar si los datos son correctos
            if(email.text.toString() == "user" && password.text.toString() == "12345"){
                //regresa al menu principal
                val intent = Intent(this,MainActivity2::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Correo o contraseña incorrecto", Toast.LENGTH_SHORT).show()
            }//if-else validacion
            Toast.makeText(this,"Ingresar informacion", Toast.LENGTH_SHORT).show()
        }//else-if no vacios
    }//aceptar

    private fun cancelar(){
        Toast.makeText(this,"Ingresar informacion", Toast.LENGTH_SHORT).show()
        finish()
    }



}//Class