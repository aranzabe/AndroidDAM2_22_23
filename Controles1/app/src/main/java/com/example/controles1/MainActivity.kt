package com.example.controles1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var boton:Button
    lateinit var caja:TextView
    lateinit var edText:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.boton = findViewById(R.id.btnPulsar)
        this.caja = findViewById(R.id.txtCaja)
        this.edText = findViewById(R.id.edNombre)

        //boton.setOnClickListener {
        //    caja.text = "Toda la energía"
        //}
    }

    fun alPulsar(v : View){
        //caja.text = "Desde la función"
        Toast.makeText(this, "Hola ${edText.text}",Toast.LENGTH_LONG).show()
    }

}