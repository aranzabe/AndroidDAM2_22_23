package com.example.bundle

import Modelo.Persona
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.bundle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSegunda.setOnClickListener {
            //Recogemos los valores de las cajas y los asignamos a variables primitivas y a un objeto.
            val nombre = binding.edNombre.text.toString()
            val edad = binding.edEdad.text.toString().toInt()
            val p = Persona(nombre, edad)

            //Montamos el intent con el que llamaremos a la segunda activity.
            val inte = Intent(this, MainActivity2::class.java)
            //Definimos el Bundle que viajará en el Intent.
            //Opción a)
//            var b = Bundle()
//            b.putString("nom", nombre)
//            b.putInt("ed",edad)
//            b.putSerializable("per",p)
//
            //Opción b)
            var b = bundleOf("nom" to nombre, "ed" to edad, "per" to p)
            inte.putExtras(b)
            startActivity(inte)
        }
    }
}