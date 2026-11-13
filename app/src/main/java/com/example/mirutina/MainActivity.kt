package com.example.mirutina

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnComenzar = findViewById<Button>(R.id.btnComenzar)
        val btnSalir = findViewById<Button>(R.id.btnSalirMain)

        btnComenzar.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        btnSalir.setOnClickListener {
            finishAffinity()
        }
    }
}