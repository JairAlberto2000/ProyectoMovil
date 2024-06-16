package com.example.proyecto.ui.inventarioAdm
// InventarioAdmFragment.kt
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.proyecto.Producto
import com.example.proyecto.R

class InventarioAdmFragment : Fragment() {

    private lateinit var productoAdapter: ProductoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inventario_adm, container, false)

        val listaProductos = listOf(
            Producto(1, "Azúcar", 100),
            Producto(2, "Aceite", 200),
            Producto(3, "Sal", 300),
            Producto(4, "Carne", 400),
            Producto(5, "Tomillo", 500),
            Producto(6, "Pasta", 600),
            Producto(7, "Harina", 700),
            Producto(8, "Pollo", 800),
            Producto(9, "Arroz", 900),
            Producto(10, "Pescado", 1000),
            Producto(11, "Leche", 1100),
            Producto(12, "Queso", 1200),
            Producto(13, "Huevos", 1300),
            Producto(14, "Pan", 1400),
            Producto(15, "Cebolla", 1500),
            Producto(16, "Ajo", 1600),
            Producto(17, "Papas", 1700),
            Producto(18, "Zanahorias", 1800),
            Producto(19, "Yogur", 1900),
            Producto(20, "Espinacas", 2000),
            Producto(21, "Fresas", 2100),
            Producto(22, "Plátanos", 2200),
            Producto(23, "Chocolate", 2300),
            Producto(24, "Café", 2400),
            Producto(25, "Té", 2500),
            Producto(26, "Sopa", 2600),
            Producto(27, "Vinagre", 2700),
            Producto(28, "Salsa", 2800),
            Producto(29, "Cerveza", 2900),
            Producto(30, "Vino", 3000)
        )


        productoAdapter = ProductoAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            listaProductos
        )

        val listView: ListView = view.findViewById(R.id.listView)
        listView.adapter = productoAdapter

        val searchView: SearchView = view.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                productoAdapter.filter.filter(newText)
                return false
            }
        })

        return view
    }
}
