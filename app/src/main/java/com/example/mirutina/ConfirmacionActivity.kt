package com.example.mirutina

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConfirmacionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmacion)

        val txtDatos = findViewById<TextView>(R.id.txtDatos)
        val btnFinalizar = findViewById<Button>(R.id.btnFinalizar)
        val btnSalir = findViewById<Button>(R.id.btnSalirConfirmacion)

        val nombre = intent.getStringExtra(RegistroActivity.EXTRA_NOMBRE).orEmpty()
        val correo = intent.getStringExtra(RegistroActivity.EXTRA_CORREO).orEmpty()
        val actividad = intent.getStringExtra(RegistroActivity.EXTRA_ACTIVIDAD)
            ?: getString(R.string.actividad_no_seleccionada)

        val recordatorioMarcado =
            intent.getBooleanExtra(RegistroActivity.EXTRA_RECORDATORIO, false)
        val tieneMeta = intent.getBooleanExtra(RegistroActivity.EXTRA_META, false)
        val diasMeta = intent.getIntExtra(RegistroActivity.EXTRA_DIAS_META, 0)

        val recordatorio = getString(
            if (recordatorioMarcado) R.string.valor_si else R.string.valor_no
        )

        val lineas = mutableListOf(
            getString(R.string.etiqueta_nombre, nombre),
            getString(R.string.etiqueta_correo, correo),
            getString(R.string.etiqueta_actividad, actividad),
            getString(R.string.etiqueta_recordatorio, recordatorio)
        )

        if (tieneMeta) {
            lineas.add(getString(R.string.etiqueta_meta_dias, diasMeta))
        } else {
            lineas.add(
                getString(
                    R.string.etiqueta_meta,
                    getString(R.string.valor_no)
                )
            )
        }

        txtDatos.text = lineas.joinToString(separator = "\n")

        btnFinalizar.setOnClickListener {
            val intentInicio = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intentInicio)
            finish()
        }

        btnSalir.setOnClickListener {
            finishAffinity()
        }
    }
}