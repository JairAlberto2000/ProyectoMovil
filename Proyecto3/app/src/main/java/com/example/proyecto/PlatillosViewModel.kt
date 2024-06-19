package com.example.proyecto

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class PlatillosViewModel :ViewModel() {



    //historial pedidos
    private val _pedidosH = MutableLiveData<List<HistorialP>>(emptyList())
    val pedidosH: LiveData<List<HistorialP>> get() = _pedidosH as LiveData<List<HistorialP>>

    //favoritos

    private val _favoritos = MutableLiveData<List<Fav>>(emptyList())
    val favoritos: LiveData<List<Fav>> get() = _favoritos as LiveData<List<Fav>>
// ...


    //--------------------------------LISTAR PLATILLOS AGREGADOS POR EL ADMIN
    private val _platillos = MutableLiveData<List<Platillo>>()
    val platillos: LiveData<List<Platillo>> get() = _platillos

    init {
        // Aquí inicializas Firebase y obtienes una referencia a la colección de platillos
        val db = FirebaseFirestore.getInstance()
        val platillosRef = db.collection("Platillos")

        // Observar cambios en la colección de platillos
        platillosRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.e("PlatillosViewModel", "Error al obtener platillos: $error")
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val platillosList = mutableListOf<Platillo>()
                for (doc in snapshot.documents) {
                    val nombre = doc.getString("Nombre") ?: ""
                    val costo = doc.getDouble("Costo") ?: 0.0
                    val info = doc.getString("Info") ?: ""
                    val id = doc.id
                    val platillo = Platillo(id,nombre, costo, info)
                    platillosList.add(platillo)
                }
                _platillos.value = platillosList
            }
        }
    }

    //--------------------------LISTAR PEDIDOS DEL CLIENTE

    private val _pedidos = MutableLiveData<List<Pedido>>()
    val pedidos: LiveData<List<Pedido>> get() = _pedidos

    // Aquí inicializas Firebase y obtienes una referencia a la colección de platillos
    val db = FirebaseFirestore.getInstance()

    init {

        val pedidosRef = db.collection("Pedidos")
        // Observar cambios en la colección de platillos
        pedidosRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.e("PlatillosViewModel", "Error al obtener pedidos: $error")
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val pedidosList = mutableListOf<Pedido>()
                for (doc in snapshot.documents) {
                    val nombre = doc.getString("Nombre") ?: ""
                    val costo = doc.getDouble("Total") ?: 0.0
                    val id = doc.id
                    val pedido = Pedido(id, nombre, costo)
                    pedidosList.add(pedido)
                }
                _pedidos.value = pedidosList
            }
        }
    }

    fun eliminarPedido(pedidoId: String) {
        db.collection("Pedidos").document(pedidoId)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "Pedido eliminado correctamente") }
            .addOnFailureListener { e -> Log.w(TAG, "Error al eliminar el pedido", e) }
    }

    //-----------------

    //historial del cliente
    fun obtenerPedidos1(): LiveData<List<HistorialP>> {
        Log.d("SharedViewModel", "Tamaño de pedidos: ${pedidosH.value}")
        return pedidosH
    }



    //favoritoscliente

    fun obtenerFavoritos(): LiveData<List<Fav>> {
        Log.d("SharedViewModel", "Tamaño de pedidos: ${favoritos.value}")
        return favoritos
    }

    fun agregarPlatilloAfavoritos(platillo: Platillo) {
        val listaActual = _favoritos.value.orEmpty().toMutableList()
        val ultimoPedido = listaActual.lastOrNull()

        if (ultimoPedido != null && ultimoPedido.platillos.isEmpty()) {
            // Si el último pedido está vacío, agregamos el platillo al mismo pedido
            ultimoPedido.platillos.add(platillo)
        } else {
            // Si el último pedido está lleno o no existe, creamos un nuevo pedido
            val nuevoPedido = Fav(mutableListOf(platillo))
            listaActual.add(nuevoPedido)
        }

        // Actualizamos la lista de pedidos en el ViewModel
        _favoritos.value = listaActual.toList()
    }


    //------------------------------------otras

    private fun logList(tag: String, list: List<Platillo>) {
        Log.d("SharedViewModel", "$tag: ${list.joinToString { it.Nombre.toString() }}")
    }

    fun eliminarPedido(indice: Int) {
        val listaActual = _pedidos.value.orEmpty().toMutableList()

        if (indice in 0 until listaActual.size) {
            // Si el índice es válido, eliminamos el pedido de la lista
            listaActual.removeAt(indice)

            // Actualizamos la lista de pedidos en el ViewModel
            _pedidos.value = listaActual.toList()
        }
    }
}
