package com.example.datetimepicker

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

class DatePickerFragment : DialogFragment() {

    private var day: Int
    private var month: Int
    private var year: Int
    private var observer: DatePickerDialog.OnDateSetListener? = null

    init {
        LocalDate.now().let { now ->
            year = now.year
            month = now.monthValue - 1 //Restamos 1 porque en DatePicker los meses van de 0 a 11.
            day = now.dayOfMonth
        }
    }

    companion object {
        fun newInstance(
            listener: DatePickerDialog.OnDateSetListener
        ): DatePickerFragment {
            val datePicker = DatePickerFragment()
            datePicker.observer = listener
            return datePicker
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * Es aquí donde usas la interfaz DatePickerDialog.OnDateSetListener para detectar cuando el usuario cambia la fecha.
     * Esta cuenta con el controlador onDateSet(), donde escribes las sentencias que deseas ejecutar en los cambios de fecha.
     * Debido a que necesitamos delegar este comportamiento hacia nuestra actividad, añadimos una propiedad llamada observer que le redireccione la ejecución.
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //Sin estilos
        //val dialog = DatePickerDialog(requireContext(), observer, year, month, day)

        //Con estilos
        val dialog = DatePickerDialog(requireContext(), R.style.DatePickerStyle, observer, year, month, day)

        //Con estilos y fechas límites
        /*
        Ambos son de tipo Long, ya que reciben la fecha en milisegundos. Para ello usamos la clase LocalDataTime
        y una función de extensión que convierta a milisegundos estas instancias.
         */
        val today = LocalDateTime.now()
//        val sevenDaysAgo = today.minusDays(7)
//        dialog.datePicker.minDate= sevenDaysAgo.toMs()
//        dialog.datePicker.maxDate = today.toMs()

        val sevenDaysLater = today.plusDays(7)
        dialog.datePicker.minDate= today.toMs()
        dialog.datePicker.maxDate = sevenDaysLater.toMs()

        return dialog
    }

    private fun LocalDateTime.toMs(): Long {
        return atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        // [Acciones si deseas sobrescribir al presionar "CANCELAR"]
    }
}
