package com.example.storage2023

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.storage2023.databinding.ActivityMainBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val FICHERO = 100
    /*
    https://firebase.google.com/docs/storage/android/create-reference
    Crea una referencia para subir, descargar o borrar un archivo, o para obtener o actualizar sus metadatos.
    Se puede decir que una referencia es un indicador que apunta a un archivo en la nube. Las referencias son livianas,
    por lo que puedes crear todas las que necesites. También se pueden reutilizar en varias operaciones.
     */
    var storage = Firebase.storage

    // Crea una referencia con la instancia singleton FirebaseStorage y con una llamada al método reference.
    var storageRef = storage.reference

    val TAG = "Fernando"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create a child reference
        // imagesRef now points to "images"
        var imagesRef: StorageReference? = storageRef.child("images")

        // Child references can also take paths
        // spaceRef now points to "images/space.jpg
        // imagesRef still points to "images"
        var spaceRef = storageRef.child("images/spock.jpg")


        // parent allows us to move our reference to a parent node
        // imagesRef now points to 'images'
        imagesRef = spaceRef.parent
        Log.e(TAG, imagesRef!!.path.toString())

        // root allows us to move all the way back to the top of our bucket
        // rootRef now points to the root
        val rootRef = spaceRef.root
        Log.e(TAG, rootRef.path.toString())



        // File path is "images/spock.jpg"
        val path = spaceRef.path
        Log.e(TAG, path.toString())

        // File name is "spock.jpg"
        val name = spaceRef.name
        Log.e(TAG, name.toString())

        binding.btCargar.setOnClickListener {
            fileUpload()
        }

        binding.btnDescargar.setOnClickListener {
            fileDownload()
        }
    }

    /**
     * Método que descarga el fichero usando un archivo temporal.
     */
    fun fileDownload() {
        var spaceRef = storageRef.child("images/spock.jpg")

        val localfile  = File.createTempFile("tempImage","jpg")
        spaceRef.getFile(localfile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            binding.imgImagen.setImageBitmap(bitmap)
        }.addOnFailureListener{
            Toast.makeText(this,"Algo ha fallado en la descarga", Toast.LENGTH_SHORT).show()
        }

    }


    /**
     * Éste método abre el explorador de archivos del móvil par elegir una imagen.
     * https://firebase.google.com/docs/storage/android/upload-files
     */
    fun fileUpload() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        startActivityForResult(intent, FICHERO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FICHERO) {
            if (resultCode == RESULT_OK) {
                val FileUri = data!!.data
                Log.e("Fernando",FileUri.toString())
                val Folder: StorageReference =
                    FirebaseStorage.getInstance().getReference().child("images")
                val file_name: StorageReference = Folder.child("" + FileUri!!.lastPathSegment)
                file_name.putFile(FileUri).addOnSuccessListener { taskSnapshot ->
                    file_name.getDownloadUrl().addOnSuccessListener { uri ->
                        Toast.makeText(this,"Imagen subida correctamente", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}