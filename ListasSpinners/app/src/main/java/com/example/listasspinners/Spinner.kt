package com.example.listasspinners

import Modelo.Persona
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.listasspinners.databinding.ActivityListaBinding
import com.example.listasspinners.databinding.ActivitySpinnerBinding
import java.util.ArrayList

class Spinner : AppCompatActivity() {
    lateinit var binding: ActivitySpinnerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_spinner)
        binding = ActivitySpinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btVolverSp.setOnClickListener {
            finish()
        }

        var vector = arrayOf(1,2)
        var vec = ArrayList<Persona>(4)
        vec.add(Persona("Fernando",38))
        vec.add(Persona("Aranzabe",71))

        val adaptador = ArrayAdapter(this, R.layout.item_lista,R.id.txtItem,vec)
        binding.spSpinner.adapter = adaptador

        binding.spSpinner.setOnItemSelectedListener(object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                val texto = binding.spSpinner.getItemAtPosition(pos)
                var p = vec.get(pos)
                Log.e("Fernando",p.toString())
                Toast.makeText(applicationContext, texto.toString(), Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        })
    }
}