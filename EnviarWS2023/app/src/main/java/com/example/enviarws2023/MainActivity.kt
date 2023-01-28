package com.example.enviarws2023

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.enviarws2023.databinding.ActivityMainBinding

/*
Para este ejemplo es importante saber que el sdk mínimo para que funcione es 33.
En buidGradle:
compileSdk 33
.....
targetSdk 33

También tenemos que poner en el manifest las siguientes líneas:
<queries>
        <package android:name="com.whatsapp"/>
        <package android:name="com.whatsapp.w4b"/> --> Este sería solo para el ws 4 business
</queries>
 */

//https://www.programandoamedianoche.com/2020/03/enviar-mensajes-y-fotos-a-whatsapp-desde-nuestra-aplicacion-android/
//https://developer.android.com/training/data-storage/shared/photopicker?hl=es-419
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var uriImagen : Uri? = null
    // Registers a photo picker activity launcher in single-select mode.
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            Log.d("Fernando", "Selected URI: $uri")
            this.uriImagen = uri
            binding.imgImagen.setImageURI(uri)
        } else {
            Log.d("Fernando", "No media selected")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btEnviar.setOnClickListener {
            sendMessage(binding.edTexto.text.toString())
        }

        binding.btnSeleccion.setOnClickListener{
            // Launch the photo picker and allow the user to choose only images.
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.btEnviarImg.setOnClickListener {
            Log.e("Fernando",this.uriImagen.toString())
            sendImage(this.uriImagen)
        }
    }

    fun sendImage(uri:Uri?){
        // Creating intent with action send
        val intent = Intent(Intent.ACTION_SEND)
        // Setting Intent type
        intent.type = "image/*"
        // Setting whatsapp package name
        intent.setPackage("com.whatsapp")

        intent.putExtra(Intent.EXTRA_STREAM, uri);

        // Checking whether whatsapp is installed or not
        if (intent.resolveActivity(packageManager) == null) {
            Toast.makeText(this,
                "Please install whatsapp first.",
                Toast.LENGTH_SHORT).show()
            return
        }

        // Starting Whatsapp
        startActivity(intent)
    }


    fun sendMessage(message: String) {

        // Creating intent with action send
        val intent = Intent(Intent.ACTION_SEND)

        // Setting Intent type
        intent.type = "text/plain"

        // Setting whatsapp package name
        intent.setPackage("com.whatsapp")

        // Give your message here
        intent.putExtra(Intent.EXTRA_TEXT, message)

        // Checking whether whatsapp is installed or not
        if (intent.resolveActivity(packageManager) == null) {
            Toast.makeText(this,
                "Please install whatsapp first.",
                Toast.LENGTH_SHORT).show()
            return
        }

        // Starting Whatsapp
        startActivity(intent)
    }
}