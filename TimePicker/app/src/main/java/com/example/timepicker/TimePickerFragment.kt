package com.example.timepicker

import android.app.Dialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.time.LocalTime

class TimePickerFragment: DialogFragment() {

    private var hour: Int
    private var minute: Int
    private var timeObserver: TimePickerDialog.OnTimeSetListener? = null

    init {
        val now = LocalTime.now()
        hour = now.hour
        minute = now.minute
    }

    companion object{
        fun newInstance(observer: OnTimeSetListener):TimePickerFragment{
            return TimePickerFragment().apply {
                timeObserver = observer
            }
        }
    }


    /**
     * Usaremos dos propiedades mutables para almacenar la hora y minuto del tiempo que se pasarán en la construcción del TimePickerDialog. Y en el bloque de inicio asignaremos los valores enteros del tiempo actual.

       Por otro lado, los parámetros pasados al constructor público de TimePickerDialog tienen el siguiente propósito:
        context: Contexto en el que vivirá el diálogo
        themeResId: El ID del recurso de estilo que deseas aplicar al diálogo. Más adelante lo usaremos.
        listener: Observador OnTimeSetListener que notificará el momento en que se seleccione una fecha. Por el momento es null, pero ahora asignaremos una propiedad para que cumpla con este rol.
        hourOfDay: Representa la hora con que se inicializará el TimePicker
        minute: Representa los minutos iniciales del TimePicker
        is24HourView: Si es modo 24 horas o AM/PM. Pero si quisieras usar el formato que el usuario ha declarado en sus ajustes de preferencias, puedes averiguarlo con DateFormat.is24hourView().
                      Con este resultado podrás maniobrar la lógica al construir el TimePicker.
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //return TimePickerDialog(requireActivity(), null, hour, minute, false)
        //val dialog = TimePickerDialog(requireActivity(), timeObserver, hour, minute, false)

        //DateFormat.is24HourFormat()
        val dialog = TimePickerDialog(requireActivity(), R.style.ThemeOverlay_App_TimePicker, timeObserver, hour, minute, false)
        return dialog
    }
}