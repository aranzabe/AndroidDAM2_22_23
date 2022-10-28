package com.example.ejemplofichero

import Auxiliar.Fichero
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ejemplofichero.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var manejoFichero:Fichero = Fichero("otro.txt", this)
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* //Con estas líneas podemos ver los ficheros definidos en la aplicación.
        var ed:EditText = findViewById(R.id.edContenidoFichero)*/
        for(i in 0..this.fileList().size-1)
        {
            Log.e("Fernando",this.fileList().get(i).toString())
        }

        binding.btnBorrar.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
                .setTitle("Borrado del contenido.")
                .setMessage("¿Estás seguro?")
                .setNegativeButton("No") { view, _ ->
                    Toast.makeText(this, "Se ha cancelado el borrado", Toast.LENGTH_SHORT).show()
                    view.dismiss()
                }
                .setPositiveButton("Sí") { view, _ ->
                    Toast.makeText(this, "Se ha borrado el contenido del fichero", Toast.LENGTH_SHORT).show()
                    this.manejoFichero.borrarFichero()
                    binding.edContenidoFichero.setText("")
                    view.dismiss()
                }
                .setCancelable(false)
                .create()

            dialog.show()
        }

        binding.btnBorrarFisico.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
                .setTitle("Borrado del fichero físico.")
                .setMessage("¿Estás seguro?")
                .setNegativeButton("No") { view, _ ->
                    Toast.makeText(this, "Se ha cancelado el borrado", Toast.LENGTH_SHORT).show()
                    view.dismiss()
                }
                .setPositiveButton("Sí") { view, _ ->
                    Toast.makeText(this, "Se ha borrado el fichero físico", Toast.LENGTH_SHORT).show()
                    manejoFichero.borrarFicheroFisico()
                    val inte = intent
                    Toast.makeText(this,"Fichero borrado",Toast.LENGTH_LONG).show()
                    finish()
                    startActivity(inte)
                    view.dismiss()
                }
                .setCancelable(false)
                .create()

            dialog.show()
        }

        binding.btnGuardar.setOnClickListener {
            manejoFichero.escribirLinea(binding.edLinea.text.toString())
            binding.edContenidoFichero.setText("")
            if(!manejoFichero.existeFichero()) {
                binding.edContenidoFichero.setText("El fichero no existe.")
            }
            else {
                binding.edContenidoFichero.setText(manejoFichero.leerFichero())
            }
        }

        binding.btnRecuperarFichero.setOnClickListener {
            binding.edContenidoFichero.setText("")
            if(!manejoFichero.existeFichero()) {
                binding.edContenidoFichero.setText("El fichero no existe.")
            }
            else {
                binding.edContenidoFichero.setText(manejoFichero.leerFichero())
            }
        }
    }
}