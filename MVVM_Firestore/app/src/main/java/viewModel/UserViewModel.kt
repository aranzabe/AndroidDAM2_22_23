package viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import models.User
import models.UserProvider

class UserViewModel : ViewModel() {
    val userModel = MutableLiveData<User?>()

    fun getUser(email:String) {
        var data : QuerySnapshot?
        var u : User? = null
        runBlocking {
            val job : Job = launch(context = Dispatchers.Default) {
                data =  UserProvider.getUser(email) as QuerySnapshot //Obtenermos la colección
                u = obtenerDatos(data as QuerySnapshot?)  //'Destripamos' la colección y la metemos en nuestro ArrayList
            }
            //Con este método el hilo principal de onCreate se espera a que la función acabe y devuelva la colección con los datos.
            job.join() //Esperamos a que el método acabe: https://dzone.com/articles/waiting-for-coroutines
        }

        userModel.postValue(u)
    }

    private fun obtenerDatos(datos: QuerySnapshot?):User? {
        var al: User? = null
        for(dc: DocumentChange in datos?.documentChanges!!){
            if (dc.type == DocumentChange.Type.ADDED){
                al = User(
                    dc.document.get("age").toString(),
                    dc.document.get("email").toString(),
                    dc.document.get("name").toString(),
                    dc.document.get("provider").toString()
                )
            }
        }
        return al
    }

    fun addUser(email:String, nombre:String, edad:String, proveedor:String){
        var u = UserProvider.addUser(email, nombre, edad, proveedor)
        userModel.postValue(u)
    }
}