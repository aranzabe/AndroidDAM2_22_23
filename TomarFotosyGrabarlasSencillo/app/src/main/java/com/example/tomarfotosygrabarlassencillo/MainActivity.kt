package com.example.tomarfotosygrabarlassencillo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import android.os.Environment
import android.util.Log
import com.example.tomarfotosygrabarlassencillo.databinding.ActivityMainBinding
import java.io.FileOutputStream
import java.lang.Exception


/**
 * Para este ejemplo tenemos que escribir el nombre del archivo que queremos guardar, con su extensión.
 * Para recuperar tendremos que escribir también el nombre del archivo.
 * https://tutorialesprogramacionya.com/javaya/androidya/androidstudioya/detalleconcepto.php?codigo=50&inicio=40
 */

class MainActivity : AppCompatActivity() {
    private val cameraRequest = 1888
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), cameraRequest)


        binding.btCapturar.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, cameraRequest)

            /*
            var intentFoto = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            var foto = File(getExternalFilesDir(null), edNombre.text.toString())
            intentFoto.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto))
            startActivity(intentFoto)
             */
        }

        binding.btRecuperar.setOnClickListener {
            var bitmap1 = BitmapFactory.decodeFile(getExternalFilesDir(null).toString() + "/"+binding.edNombreFoto.text.toString());
            binding.imgImagen.setImageBitmap(bitmap1)
        }

        binding.btVer.setOnClickListener {
            val intentLista = Intent(this, ListaFotos::class.java)
            startActivity(intentLista)
        }
    }


    //https://es.stackoverflow.com/questions/33561/c%C3%B3mo-guardar-un-imageview-en-android
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == cameraRequest) {
                val photo: Bitmap = data?.extras?.get("data") as Bitmap
                binding.imgImagen.setImageBitmap(photo)

                var fotoFichero = File(getExternalFilesDir(null), binding.edNombreFoto.text.toString())
                var uri = Uri.fromFile(fotoFichero)
                var fileOutStream = FileOutputStream(fotoFichero)
                photo.compress(Bitmap.CompressFormat.PNG, 100, fileOutStream);
                fileOutStream.flush();
                fileOutStream.close();
            }
        }catch(e: Exception){
            Log.e("Fernando",e.toString())
        }
    }


}