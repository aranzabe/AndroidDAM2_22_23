package com.example.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mnOp1 -> Toast.makeText(this, "Opción 1",Toast.LENGTH_LONG).show()
            R.id.mnOp2 -> Toast.makeText(this, "Opción 2",Toast.LENGTH_SHORT).show()
            R.id.mnOp3 -> Toast.makeText(this, "Opción 3",Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}