package com.example.listasspinners

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listasspinners.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btListas.setOnClickListener {
            var intentListasSEncillas = Intent(this,Lista::class.java)
            startActivity(intentListasSEncillas)
        }

        binding.btSpiners.setOnClickListener {
            var intentSpin = Intent(this,Spinner::class.java)
            startActivity(intentSpin)
        }
    }
}