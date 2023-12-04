package com.example.proyecto.ui.inventarioAdm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.proyecto.Ingrediente
import com.example.proyecto.R

class InventarioAdmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inventario_adm, container, false)

        val listaIngredientes = mutableListOf(
            Ingrediente(1, "Ingrediente 1", 100),
            Ingrediente(2, "Ingrediente 2", 200),
            Ingrediente(3, "Ingrediente 3", 300),
            Ingrediente(4, "Ingrediente 4", 400),
            Ingrediente(5, "Ingrediente 5", 500),
            Ingrediente(6, "Ingrediente 6", 600),
            Ingrediente(7, "Ingrediente 7", 700),
            Ingrediente(8, "Ingrediente 8", 800),
            Ingrediente(9, "Ingrediente 9", 900),
            Ingrediente(10, "Ingrediente 10", 1000)
        )

        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            listaIngredientes
        )

        val listView: ListView = view.findViewById(R.id.listView)
        listView.adapter = arrayAdapter

        return view
    }
}
