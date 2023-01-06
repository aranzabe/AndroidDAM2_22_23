package com.example.firestore_i_2023

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.firestore_i_2023.databinding.ActivityHomeBinding
import com.example.firestore_i_2023.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


//https://firebase.google.com/docs/firestore
class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    val db = FirebaseFirestore.getInstance() //Variable con la que accederemos a Firestore. Será una instancia a la bd.



    //Esto se lanza cuando se descarga la app. Aquí también podemos poner el borrado de los datos de sesión.
    override fun onStop() {
        super.onStop()
        Log.e("Fernando","Stop app")
//        val prefs: SharedPreferences.Editor? = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
//        prefs?.clear() //Al cerrar sesión borramos los datos
//        prefs?.apply ()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle:Bundle? = intent.extras
        val email = bundle?.getString("email").toString()
        binding.txtEmail.text = email
        val prov:String = bundle?.getString("provider").toString()
        binding.txtProveedor.text = prov


        //Guardado de datos para toda la aplicación en la sesión.
        val prefs: SharedPreferences.Editor? = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs?.putString("email",bundle?.getString("email").toString())
        prefs?.putString("provider",bundle?.getString("provider").toString())
        prefs?.apply () //Con estos datos guardados en el fichero de sesión, aunque la app se detenga tendremos acceso a los mismos.


        binding.btCerrarSesion.setOnClickListener {
            val prefs: SharedPreferences.Editor? = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs?.clear() //Al cerrar sesión borramos los datos
            prefs?.apply ()
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

        binding.btGuardar.setOnClickListener {
            //Se guardarán en modo HashMap (clave, valor).
            var user = hashMapOf(
                "provider" to prov,
                "email" to binding.txtEmail.text.toString(),
                "name" to binding.edNombre.text.toString(),
                "age" to binding.edEdad.text.toString(),
                "roles" to arrayListOf(1, 2, 3)
            )

            //Si no existe el documento lo crea, si existe lo remplaza.
//            db.collection("users")
//                .document(email) //Será la clave del documento.
//                .set(user).addOnSuccessListener {
//                    Toast.makeText(this, "Almacenado",Toast.LENGTH_SHORT).show()
//                }.addOnFailureListener{
//                    Toast.makeText(this, "Ha ocurrido un error",Toast.LENGTH_SHORT).show()
//                }


            //Otra forma
            //Si no existe el documento lo crea, si existe añade otro. Las id serán asignadas autormáticamente.
            db.collection("users")
                .add(user)
                .addOnSuccessListener {
                    Toast.makeText(this, "Almacenado",Toast.LENGTH_SHORT).show()
                    Log.e("Fernando", "Documento añadido con ID: ${it.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("Fernando", "Error adding document", e.cause)
                }

        }

        binding.btRecuperar.setOnClickListener {
            var roles : ArrayList<Int>
            //Búsqueda por id del documento.
            db.collection("users").document(email).get().addOnSuccessListener {
                //Si encuentra el documento será satisfactorio este listener y entraremos en él.
                binding.edNombre.append(it.get("name") as String?)
                binding.edEdad.append(it.get("age") as String?)
                if (it.get("roles")!=null) {
                    roles = it.get("roles") as ArrayList<Int>
                    Log.e("Fernando",roles.toString())
                }
                else {
                    Log.e("Fernando", "Sin roles")
                }

                Toast.makeText(this, "Recuperado",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this, "Algo ha ido mal al recuperar",Toast.LENGTH_SHORT).show()
            }
        }

        binding.btEliminar.setOnClickListener {
            //Buscamos antes si existe un campo con ese email en un documento.
            val id = db.collection("users").whereEqualTo("email",email)
                .get()
                .addOnSuccessListener {result ->
                    //Con esto borramos el primero.
//                    db.collection("users").document(result.elementAt(0).id).delete().toString()
                    //Con esto borramos todos.
                    for (document in result) {
                        db.collection("users").document(document.id).delete().toString()
                    }

                    Toast.makeText(this, "Eliminado",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Toast.makeText(this, "No se ha encontrado el documento a eliminar",Toast.LENGTH_SHORT).show()
                }

        }

        //https://cloud.google.com/firestore/docs/query-data/queries?hl=es-419#kotlin+ktxandroid_3
        binding.btRecuperarTodos.setOnClickListener {
            var al = ArrayList<String>()
            db.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        al.add(document.data.toString())
                        Log.d("Fernando", "${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("Fernando", "Error getting documents.", exception)
                }
            Log.e("Fernando", al.toString())//Observamos que esto nos da un AL vacío porque la consulta es asíncrona y necesitaremos hacerla con una corrutina.
        }
    }
}





















