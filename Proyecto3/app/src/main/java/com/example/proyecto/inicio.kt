package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class inicio : AppCompatActivity() {

    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        val btnIngresar : Button = findViewById(R.id.btnIngresar)
        val edtCorreo : EditText = findViewById(R.id.edtCorreo)
        val edtContra : EditText = findViewById(R.id.edtContra)
        firebaseAuth = Firebase.auth

        btnIngresar.setOnClickListener {
            if(edtCorreo.text.isNotEmpty() && edtCorreo.text.isNotBlank() &&
                edtContra.text.isNotEmpty() && edtContra.text.isNotBlank()){

                signIn(edtCorreo.text.toString(), edtContra.text.toString())

            }else{
                Toast.makeText(this,"Ingresar informacion", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun signIn(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    Toast.makeText(baseContext, "Ingreso exitoso", Toast.LENGTH_SHORT).show()

                    if(email=="admin@gmail.com"){
                        val intent = Intent(this, MainActivity2::class.java)
                        startActivity(intent)
                    }else{
                        val intent1 = Intent(this, MainActivity::class.java)
                        startActivity(intent1)
                    }

                } else {
                    val error = task.exception?.message
                    Toast.makeText(baseContext, "Error: $error", Toast.LENGTH_SHORT).show()
                }
            }

    }
}