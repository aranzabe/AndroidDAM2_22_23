package com.example.seekbarsviewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.seekbarsviewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.seekBarDiscreta.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                binding.caja1.text = "Cambiando progreso"
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                binding.caja2.setText("Starting tracking")
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                binding.caja2.setText("Stopping tracking")
                binding.caja1.setText(binding.seekBarDiscreta.progress.toString())
            }
        })

        binding.seekBarContinua.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                binding.caja1.text = "Cambiando progreso"
                var progreso = binding.seekBarContinua.progress / 10.0
                binding.pbBarra.progress = progreso.toInt()
                binding.caja2.text = progreso.toString()
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                binding.caja2.setText("Starting tracking")
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                binding.caja2.setText("Stopping tracking")
                var progreso = binding.seekBarContinua.progress / 10.0
                binding.caja1.setText(progreso.toString())

            }
        })

    }
}