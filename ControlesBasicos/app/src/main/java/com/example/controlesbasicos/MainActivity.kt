package com.example.controlesbasicos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var bt : Button
    lateinit var txtCaja:TextView
    lateinit var chBox : CheckBox
    lateinit var edCaja : EditText
    lateinit var rb1 : RadioButton
    lateinit var rb2 : RadioButton
    lateinit var tb: ToggleButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt = findViewById(R.id.btBoton)
        txtCaja = findViewById(R.id.txtCaja)
        chBox = findViewById(R.id.chB)
        edCaja = findViewById(R.id.editCaja)
        rb1 = findViewById(R.id.rb1)
        rb2 = findViewById(R.id.rb2)
        tb = findViewById(R.id.tBot)

        bt.setOnClickListener {
            if (chBox.isChecked){
                Toast.makeText(this,"Seleccionado",Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "No seleccionado",Toast.LENGTH_SHORT).show()
            }
            if (rb1.isChecked){
                txtCaja.text = "Opción 1"
            }
            else {
                edCaja.setText("Opción 2")
            }
        }

        chBox.setOnClickListener {
            if (chBox.isChecked){
                chBox.text = "Seleccionado"
            }
            else {
                chBox.text = "No seleccionado"
            }
        }

        tb.setOnClickListener {
            if (tb.isChecked){
                tb.text = "Pulsado"
            }
            else {
                tb.text = "No pulsado"
            }
        }


    }
}