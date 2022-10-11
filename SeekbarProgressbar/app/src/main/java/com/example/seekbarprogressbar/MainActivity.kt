package com.example.seekbarprogressbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var sebDecimal : SeekBar
    lateinit var sekDiscreta : SeekBar
    lateinit var caja1 : TextView
    lateinit var caja2 : TextView
    lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sebDecimal = findViewById(R.id.seekBarContinua)
        sekDiscreta = findViewById(R.id.seekBarDiscreta)
        caja1 = findViewById(R.id.caja1)
        caja2 = findViewById(R.id.caja2)
        progressBar = findViewById(R.id.pbBarra)

        sekDiscreta.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                caja1.text = "Cambiando progreso"
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                caja2.setText("Starting tracking")
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                caja2.setText("Stopping tracking")
                caja1.setText(sekDiscreta.progress.toString())
            }
        })

        sebDecimal.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                caja1.text = "Cambiando progreso"
                var progreso = sebDecimal.progress / 10.0
                progressBar.progress = progreso.toInt()
                caja2.text = progreso.toString()
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                caja2.setText("Starting tracking")
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                caja2.setText("Stopping tracking")
                var progreso = sebDecimal.progress / 10.0
                caja1.setText(progreso.toString())

            }
        })
    }
}