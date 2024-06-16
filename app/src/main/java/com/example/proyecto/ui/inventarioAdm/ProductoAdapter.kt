package com.example.proyecto.ui.inventarioAdm
// ProductoAdapter.kt
import android.content.Context
import android.widget.ArrayAdapter
import com.example.proyecto.Producto

class ProductoAdapter(context: Context, resource: Int, objects: List<Producto>) :
    ArrayAdapter<Producto>(context, resource, objects) {

    override fun getItemId(position: Int): Long {
        return getItem(position)?.id?.toLong() ?: 0
    }

    override fun getItem(position: Int): Producto? {
        return super.getItem(position)
    }

    override fun getCount(): Int {
        return super.getCount()
    }
}
