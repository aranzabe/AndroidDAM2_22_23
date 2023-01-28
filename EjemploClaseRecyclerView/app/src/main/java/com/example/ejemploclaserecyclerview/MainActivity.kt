package com.example.ejemploclaserecyclerview

import Adaptadores.MiAdaptadorRecycler
import Modelo.FactoriaListaPersonaje
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejemploclaserecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var personajes = FactoriaListaPersonaje.generaLista(12)

    /**
     * El recyclerview lo tenemos que instanciar en el método setUpRecyclerView() por lo que tenemos que
     * ponerle la propiedad lateinit a la variable, indicándole a Kotlin que la instanciaremos más tarde.
     */
    lateinit var miRecyclerView : RecyclerView
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e("Fernando", personajes.toString())

        miRecyclerView = binding.listaPersonajesRecycler as RecyclerView
        miRecyclerView.setHasFixedSize(true)
        miRecyclerView.layoutManager = LinearLayoutManager(this)
        var miAdapter = MiAdaptadorRecycler(personajes, this)
        miRecyclerView.adapter = miAdapter

        binding.btnDetalle.setOnClickListener {
            if (MiAdaptadorRecycler.seleccionado >= 0) {
                personajes.get(MiAdaptadorRecycler.seleccionado)
//                Log.
            }
            else {
                Toast.makeText(this,"Selecciona algo previamente",Toast.LENGTH_SHORT).show()
            }
        }

    }
}