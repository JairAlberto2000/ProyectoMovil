package com.example.proyecto

import android.app.Application
import androidx.lifecycle.ViewModelProvider

class MyApp : Application() {
    val platillosViewModel: PlatillosViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(this).create(PlatillosViewModel::class.java)
    }
}