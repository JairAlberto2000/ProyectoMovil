package com.example.proyecto

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class registrarUsuarios : AppCompatActivity() {

    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuarios)

        val edtNombre : EditText = findViewById(R.id.edtNombre)
        val edtA : EditText = findViewById(R.id.edtApellidoPaterno)
        val edtTel : EditText = findViewById(R.id.edtTel)
        val edtCorreoU : EditText = findViewById(R.id.edtCorreoU)
        val edtContra : EditText = findViewById(R.id.edtContrasena)
        val edtVer : EditText = findViewById(R.id.edtVerificar)

        val btnRegistrar : Button = findViewById(R.id.btnRegistrar)
        val btnIniciar : Button = findViewById(R.id.btnIniciar)

        firebaseAuth = Firebase.auth
        // Inicializar Firestore
        db = FirebaseFirestore.getInstance()

        btnRegistrar.setOnClickListener {
            var contra1 = edtContra.text.toString()
            var contra2 = edtVer.text.toString()

            var nombreU = edtNombre.text.toString()
            var aU = edtA.text.toString()
            var telU = edtTel.text.toString().toInt()
            var correoU = edtCorreoU.text.toString()


            if(contra1.equals(contra2)){
                registrar(nombreU, aU, telU, correoU, contra1)

            }else{
                Toast.makeText(baseContext, "Error correo o contrasena incorrectos", Toast.LENGTH_SHORT).show()
                edtContra.requestFocus()
            }
        }

        btnIniciar.setOnClickListener {
            val intent = Intent(this, Ingreso::class.java)
            startActivity(intent)
        }
    }//override

    private fun registrar(nombre: String, a: String, telefono: Int, email: String, contra: String){
        firebaseAuth.createUserWithEmailAndPassword(email, contra)
            .addOnCompleteListener(this){task ->

                if(task.isSuccessful){
                    Toast.makeText(baseContext, "Cuenta creada correctamente", Toast.LENGTH_SHORT).show()

                    // Create a new user with a first and last name
                    val user = hashMapOf(
                        "Nombre" to nombre,
                        "Apellido" to a,
                        "Telefono" to telefono,
                        "Correo" to email,
                    )

                    db.collection("Clientes")
                        .add(user)
                        .addOnSuccessListener { documentReference ->
                            Toast.makeText(baseContext, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                            clear()
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }

                } else {

            Toast.makeText(baseContext, "Error, no se creo la cuenta", Toast.LENGTH_SHORT).show()
        }
            }
    }

    fun clear(){

        val edtNombre: EditText = findViewById(R.id.edtNombre)
        val edtA: EditText = findViewById(R.id.edtApellidoPaterno)
        val edtTel: EditText = findViewById(R.id.edtTel)
        val edtCorreoU: EditText = findViewById(R.id.edtCorreoU)
        val edtContra: EditText = findViewById(R.id.edtContrasena)
        val edtVer: EditText = findViewById(R.id.edtVerificar)

        edtNombre.text.clear()
        edtA.text.clear()
        edtTel.text.clear()
        edtCorreoU.text.clear()
        edtContra.text.clear()
        edtVer.text.clear()
    }
}