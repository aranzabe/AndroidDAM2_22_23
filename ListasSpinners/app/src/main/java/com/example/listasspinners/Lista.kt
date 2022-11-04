package com.example.listasspinners

import Modelo.Persona
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.listasspinners.databinding.ActivityListaBinding
import com.example.listasspinners.databinding.ActivityMainBinding
import java.util.ArrayList

class Lista : AppCompatActivity() {
    lateinit var binding: ActivityListaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_lista)
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btVolver.setOnClickListener {
            finish()
        }

        var vector = arrayOf(1,2,3,4,6,8,9)

        var vec = ArrayList<Persona>(4)
        vec.add(Persona("Fernando",38))
        vec.add(Persona("Aranzabe",71))



        val adaptador = ArrayAdapter(this, R.layout.item_lista,R.id.txtItem,vec)
        binding.spLista.adapter = adaptador

        binding.spLista.onItemClickListener = object: AdapterView.OnItemClickListener {

            override fun onItemClick(parent: AdapterView<*>?, vista: View?, pos: Int, idElemento: Long) {
                val texto = binding.spLista.getItemAtPosition(pos) //as Persona
                var p = vec.get(pos)
                Log.e("Fernando",p.nombre)
                Log.e("Fernando",texto.toString())
                Toast.makeText(applicationContext, texto.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}