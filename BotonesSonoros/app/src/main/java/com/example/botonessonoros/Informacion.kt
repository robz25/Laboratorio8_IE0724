package com.example.botonessonoros

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Informacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion)

        val actionBar = supportActionBar
        actionBar!!.title = "Información"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

       // saveData()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun saveData(){

        val posicion1 = "1" +"\t Ricardo"+"\t24"+"\t11"
        val posicion2 = "2" +"\t Ricardo"+"\t24"+"\t8"
        val posicion3 = "3" +"\t Ricardo"+"\t24"+"\t5"
        val posicion4 = "4" +"\t Ricardo"+"\t24"+"\t0"
        val posicion5 = "5" +"\t Ricardo"+"\t24"+"\t0"

        /*val insertedText = exText.text.toString()
        tv_text.text = isertedText*/
        val sharedPreferences = getSharedPreferences("TablaPuntos", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("P1",posicion1)
            putString("P2",posicion2)
            putString("P3",posicion3)
            putString("P4",posicion4)
            putString("P5",posicion5)
        }.apply()
    }

}