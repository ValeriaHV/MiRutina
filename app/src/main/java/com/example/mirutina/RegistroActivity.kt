package com.example.mirutina

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NOMBRE = "nombre"
        const val EXTRA_CORREO = "correo"
        const val EXTRA_ACTIVIDAD = "actividad"
        const val EXTRA_RECORDATORIO = "recordatorio"
        const val EXTRA_META = "meta"
        const val EXTRA_DIAS_META = "dias_meta"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etDiasMeta = findViewById<EditText>(R.id.etDiasMeta)
        val radioGrupo = findViewById<RadioGroup>(R.id.radioGrupo)
        val cbRecordatorio = findViewById<CheckBox>(R.id.cbRecordatorio)
        val cbMeta = findViewById<CheckBox>(R.id.cbMeta)
        val btnContinuar = findViewById<Button>(R.id.btnContinuar)
        val btnSalir = findViewById<Button>(R.id.btnSalirRegistro)

        cbMeta.setOnCheckedChangeListener { _, marcada ->
            etDiasMeta.visibility = if (marcada) View.VISIBLE else View.GONE
            if (!marcada) {
                etDiasMeta.text.clear()
            }
        }

        btnContinuar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val correo = etCorreo.text.toString().trim()

            if (nombre.isEmpty()) {
                etNombre.error = getString(R.string.error_nombre)
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                etCorreo.error = getString(R.string.error_correo)
                return@setOnClickListener
            }

            val radioId = radioGrupo.checkedRadioButtonId
            if (radioId == -1) {
                Toast.makeText(this, R.string.error_actividad, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val actividad = findViewById<RadioButton>(radioId).text.toString()

            val diasMeta = if (cbMeta.isChecked) {
                etDiasMeta.text.toString().toIntOrNull() ?: 0
            } else {
                0
            }

            if (cbMeta.isChecked && diasMeta !in 1..365) {
                etDiasMeta.error = getString(R.string.error_dias_meta)
                return@setOnClickListener
            }

            val intent = Intent(this, ConfirmacionActivity::class.java).apply {
                putExtra(EXTRA_NOMBRE, nombre)
                putExtra(EXTRA_CORREO, correo)
                putExtra(EXTRA_ACTIVIDAD, actividad)
                putExtra(EXTRA_RECORDATORIO, cbRecordatorio.isChecked)
                putExtra(EXTRA_META, cbMeta.isChecked)
                putExtra(EXTRA_DIAS_META, diasMeta)
            }

            startActivity(intent)
        }

        btnSalir.setOnClickListener {
            finishAffinity()
        }
    }
}