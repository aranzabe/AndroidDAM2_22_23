package models

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

class UserProvider {
    companion object{
        suspend fun getUser(email: String): QuerySnapshot? {
            val db = FirebaseFirestore.getInstance()
            return db.collection("users").whereEqualTo("email", email).get().await()
        }

        fun addUser(email:String, nombre:String, edad:String, proveedor:String) : User?{
            val db = FirebaseFirestore.getInstance()
            var u :User? = null
            //Se guardarán en modo HashMap (clave, valor).
            var user = hashMapOf(
                "provider" to proveedor,
                "email" to email,
                "name" to nombre,
                "age" to edad
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
                    u = User(edad, email, nombre, proveedor)
                    Log.e("Fernando", "Documento añadido con ID: ${it.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("Fernando", "Error adding document", e.cause)
                }
            return u
        }
    }
}