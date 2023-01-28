package com.example.ejemplorecycler2223

import Adaptadores.MiAdaptadorRecycler
import Modelo.FactoriaListaPersonaje
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejemplorecycler2223.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var personajes = FactoriaListaPersonaje.generaLista(12)
    lateinit var miRecyclerView : RecyclerView
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e("Fernando", personajes.toString())

        miRecyclerView = binding.listaPersonajesRecycler as RecyclerView
        miRecyclerView.setHasFixedSize(true)
        miRecyclerView.layoutManager = LinearLayoutManager(this)
        var miAdapter = MiAdaptadorRecycler(personajes, this)
        miRecyclerView.adapter = miAdapter



        binding.btDetalle.setOnClickListener {
            if (MiAdaptadorRecycler.seleccionado >= 0) {
                val pe = personajes.get(MiAdaptadorRecycler.seleccionado)
                Log.e("Fernando",pe.toString())
            }
            else {
                Toast.makeText(this,"Selecciona algo previamente", Toast.LENGTH_SHORT).show()
            }
        }

    }
}