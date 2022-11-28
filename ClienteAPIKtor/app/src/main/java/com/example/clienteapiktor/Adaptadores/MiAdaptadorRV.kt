package com.example.clienteapiktor.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.clienteapiktor.Modelo.Usuario
import com.example.clienteapiktor.R

class MiAdaptadorRV (private var context: Context,
                            private var personas : ArrayList<Usuario>
) :
RecyclerView.Adapter<MiAdaptadorRV.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return MyViewHolder(v)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.DNI.text = personas[position].dni
        holder.nombre.text = personas[position].nombre
        holder.tfno.text = personas[position].tfno

        holder.itemView.setOnClickListener {
            Toast.makeText(context, personas[position].dni, Toast.LENGTH_SHORT).show()
        }
    }
    override fun getItemCount(): Int {
        return personas.size
    }
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var DNI: TextView = itemView.findViewById<View>(R.id.txtDNI) as TextView
        var nombre: TextView = itemView.findViewById<View>(R.id.txtNombre) as TextView
        var tfno: TextView = itemView.findViewById<View>(R.id.txtTfno) as TextView
    }
}