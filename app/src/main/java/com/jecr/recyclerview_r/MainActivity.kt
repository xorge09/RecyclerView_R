package com.jecr.recyclerview_r

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.LinearLayout
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        val dataSet = arrayOf("January", "February", "March","April", "May", "June","July")
        val customAdapter = CustomAdapter(dataSet)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter
        // Añadir calenndario (datePicker)
        val calendario =  Calendar.getInstance()
        val cita = DatePickerDialog.OnDateSetListener {datepicker, year, month, day ->
            calendario.set(Calendar.YEAR,year)
            calendario.set(Calendar.MONTH,month)
            calendario.set(Calendar.DAY_OF_MONTH,day)
        }

        val cita_btn:Button = findViewById(R.id.btn_cita)

        cita_btn.setOnClickListener{
            DatePickerDialog(
                this,
                cita,
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

    }

}
