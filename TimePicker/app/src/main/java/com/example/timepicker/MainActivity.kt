package com.example.timepicker

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.timepicker.databinding.ActivityMainBinding
import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    //private val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    //private val formatter = DateTimeFormatter.ofPattern("HH:mm a", Locale.ENGLISH)
    private val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpStartTime()

        setUpEndTime()

        binding.btnDiferencia.setOnClickListener {
            val start = binding.fromTime.getTime()
            val end = binding.toTime.getTime()

            val hours = hoursBetween(start,end)

            binding.duracion.text = "%.1f Horas".format(hours)
        }
    }

    private fun setUpStartTime() {

        binding.fromTime.text = LocalTime.now().format(formatter)
        binding.fromTime.setOnClickListener {
            showStartTimePicker()
        }
    }

    private fun showStartTimePicker() {
        //showDialog()
        showDialog { _, hour, minute ->
            val currentTime = LocalTime.of(hour, minute)

            if (isValidStartTime(currentTime)) {
                binding.fromTime.setTime(currentTime)
            }
        }
    }


    private fun setUpEndTime() {
        binding.toTime.text = LocalTime.now().plusHours(2).format(formatter)
        binding.toTime.setOnClickListener {
            showEndTimePicker()
        }
    }

    private fun showEndTimePicker() {
        //showDialog()
        showDialog{ _, hour, minute ->
            val currentTime = LocalTime.of(hour, minute)

            if (isValidEndTime(currentTime)) {
                binding.toTime.setTime(currentTime)
            }
        }
    }

    private fun isValidStartTime(time: LocalTime): Boolean {
        return time < binding.toTime.getTime()
    }

    private fun isValidEndTime(time: LocalTime): Boolean {
        return time > binding.fromTime.getTime()
    }

    private fun showDialog() {
        TimePickerFragment().show(supportFragmentManager, "time-picker")
    }

    private fun showDialog(observer: TimePickerDialog.OnTimeSetListener) {
        TimePickerFragment.newInstance(observer)
            .show(supportFragmentManager, "time-picker")
    }

    fun TextView.getTime(): LocalTime {
        return LocalTime.parse(text, formatter)
    }

    fun TextView.setTime(time: LocalTime) {
        text = time.toTimeText()
    }

    fun LocalTime.toTimeText(): String {
        return format(formatter)
    }

    fun hoursBetween(start:LocalTime, end: LocalTime): Double {
        return Duration.between(start, end).toMinutes() / 60.0
    }


}