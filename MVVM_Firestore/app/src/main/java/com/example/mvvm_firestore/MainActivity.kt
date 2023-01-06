package com.example.mvvm_firestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.mvvm_firestore.databinding.ActivityMainBinding
import viewModel.UserViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val usuarioViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usuarioViewModel.userModel.observe(this, Observer {
            if (it != null) {
                binding.edEmail.append(it.email.toString())
                binding.edName.append(it.name.toString())
                binding.edEdad.append(it.age.toString())
                binding.edProveedor.append(it.provider.toString())
            }
            else {
                Toast.makeText(this, "No se han encontrado registros con ese email",Toast.LENGTH_SHORT).show()
            }
            Log.e("Fernando",it.toString())
        })

        binding.btRecuperar.setOnClickListener {
            usuarioViewModel.getUser(binding.edEmail.text.toString())
        }

        binding.btGuardar.setOnClickListener {
            usuarioViewModel.addUser(binding.edEmail.text.toString(), binding.edName.text.toString(), binding.edEdad.text.toString(), binding.edProveedor.text.toString())
            Toast.makeText(this,"Registrado",Toast.LENGTH_SHORT).show()
        }
    }
}