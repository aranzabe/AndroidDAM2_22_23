package com.example.fragmentos2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentos2023.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btF1.setOnClickListener {
            val fragmentoA = FragmentoA()

            val fragmentTransaction =supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.miFragmento, fragmentoA)
            fragmentTransaction.commit()
        }

        binding.btF2.setOnClickListener {
            val fragmentoB = FragmentoB()

            val fragmentTransaction =supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.miFragmento, fragmentoB)
            fragmentTransaction.commit()
        }
    }
}