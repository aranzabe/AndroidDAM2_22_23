package com.example.recursos

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.recursos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mediaPlayer =  MediaPlayer.create(this, R.raw.mememe)

        binding.boton.setOnClickListener {
            binding.imagen.setImageResource(R.drawable.ascopas)
            mediaPlayer.start()
        }

        binding.boton2.setOnClickListener {
                mediaPlayer.stop()
                mediaPlayer = MediaPlayer.create(this, R.raw.mememe)
        }
    }
}