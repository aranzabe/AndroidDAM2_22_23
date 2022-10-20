package Modelo

object Almacen {
    var persona = ArrayList<Persona>()

    fun addPersona(pe:Persona){
        this.persona.add(pe)
    }
}