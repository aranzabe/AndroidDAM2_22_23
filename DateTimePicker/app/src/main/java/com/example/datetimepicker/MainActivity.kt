package com.example.datetimepicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.datetimepicker.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val formatter = DateTimeFormatter.ofPattern("dd/mm/yy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.datefield.setOnClickListener{
            showDatePicker()
        }
    }

    /**
     * Este método fue modificado para invocar a newInstance() con el observador de cambios de fecha.
     * En el cuerpo de la lambda que representa a la instancia OnDateSetListener, asignamos un texto
     * que crea un string a partir de los parámetros year, month y day.
     */
    private fun showDatePicker() {
        //DatePickerFragment().show(supportFragmentManager, "date-picker")
        DatePickerFragment.newInstance{ _, year, month, day ->
            binding.datefield.setText(formatDate(year, month, day))
        }.show(supportFragmentManager, "date-picker")
    }


    /**
     * Recibe el año, mes y día para crear un String formateado para mostrar en el campo de texto.
     * Para ello se usa format() con el formateador existente. Adicionalmente sumamos la unidad al mes,
     * ya que DatePickerDialog interpreta al mes con el rango [0, 11].
     */
    private fun formatDate(year: Int, month: Int, day: Int): String {
        val sanitizeMonth = month + 1
        return LocalDate.of(year, sanitizeMonth, day).format(formatter)
    }
}