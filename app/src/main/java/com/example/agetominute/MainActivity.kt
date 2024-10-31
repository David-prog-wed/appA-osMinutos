package com.example.agetominute

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var tvSelectedDate: TextView
    private lateinit var tvAgeInMinute: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinute = findViewById(R.id.tvAgeInMinute)

        btnDatePicker.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Crear el DatePickerDialog sin límites de fecha
        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            tvSelectedDate.text = selectedDate

            // Formatear la fecha seleccionada
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val date = sdf.parse(selectedDate)

            // Obtener la fecha actual
            val currentDate = Calendar.getInstance()

            // Calcular la edad en años
            val birthDate = Calendar.getInstance()
            birthDate.time = date
            var age = currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR)
            if (currentDate.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
                age--
            }

            // Calcular la diferencia en minutos
            val differenceInMinutes = (currentDate.timeInMillis - birthDate.timeInMillis) / (1000 * 60)

            // Mostrar el resultado en el TextView
            tvAgeInMinute.text = "Edad: $age años\nTiempo en minutos: $differenceInMinutes"
        }, year, month, day)

        datePickerDialog.show()
    }
}
