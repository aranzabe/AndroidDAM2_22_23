package com.example.bundle

import Modelo.Persona
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bundle.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding:ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var p : Persona = intent.getSerializableExtra("per") as Persona
        var nom = intent.getStringExtra("nom")
        var ed = intent.getIntExtra("ed",0)

        binding.txtA2.append("Nombre: ${nom}\n")
        binding.txtA2.append("Edad: ${ed}\n")
        binding.txtA2.append("Persona: ${p}\n")

        binding.btVolver.setOnClickListener {
            finish()
        }
    }
}