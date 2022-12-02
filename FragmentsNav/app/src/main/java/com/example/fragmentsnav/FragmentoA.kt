package com.example.fragmentsnav

import Modelo.Persona
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.navigation.Navigation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentoA.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentoA : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var nombre = view.findViewById<EditText>(R.id.edTextFANombre)
        var edad = view.findViewById<EditText>(R.id.edTextEdad)
        val boton: Button = view.findViewById<Button>(R.id.btFA)

        boton.setOnClickListener() {
            //Enviar número.
//            if (edad.text.toString().trim().isEmpty()) {
//                //Si no envío argumentos coge el que haya puesto por defecto.
//                Navigation.findNavController(view).navigate(R.id.action_fragmentoA_to_fragmentoB)
//            } else {
//                //Si envío argumentos
//                val bun = bundleOf("numero" to edad.text.toString().toInt())
//                Navigation.findNavController(view)
//                    .navigate(R.id.action_fragmentoA_to_fragmentoB, bun)
//            }
            //Enviar objeto.
            var p = Persona(nombre.text.toString(),edad.text.toString().toInt())
            val bun = bundleOf( "pers" to p)
            Navigation.findNavController(view).navigate(R.id.action_fragmentoA_to_fragmentoB, bun)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmento_a, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentoA.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentoA().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}