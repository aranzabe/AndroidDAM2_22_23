package com.example.transiciones

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.transiciones.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

//https://www.develou.com/usar-transiciones-en-android-con-material-design//
//https://developer.android.com/studio/write/motion-editor?hl=es-419
//https://www.techotopia.com/index.php/A_Kotlin_Android_Scene_Transitions_Tutorial
//https://www.techotopia.com/index.php/A_Kotlin_Android_Scene_Transitions_Tutorial
//https://9to5answer.com/activity-transition-in-android
//https://stackoverflow.com/questions/3389501/activity-transition-in-android
//https://www.geeksforgeeks.org/how-to-add-slide-animation-between-activities-in-android/
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSiguiente.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            //Con esta manera no usamos lo definido en /res/transition
            startActivity(intent)
            overridePendingTransition(
                androidx.appcompat.R.anim.abc_slide_in_bottom,
                androidx.appcompat.R.anim.abc_fade_out);
            //Usando lo definido en /res/transition
//            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())

        }


        binding.btnSnack.setOnClickListener {
            val snack = Snackbar.make(it,"Ejemplo de SnackBar",Snackbar.LENGTH_LONG)
            snack.setAction("Ocultar", View.OnClickListener {
                Log.e("Fernando","Snackbar - OnClick.")
            })
            snack.show()
        }
    }
}