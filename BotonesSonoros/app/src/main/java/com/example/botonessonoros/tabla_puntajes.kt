package com.example.botonessonoros

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.util.logging.Logger

val listaGuardada = arrayListOf<String>()

class tabla_puntajes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabla_puntajes)

        //val message = intent.getStringExtra(EXTRA_MESSAGE)

        val actionBar = supportActionBar
        actionBar!!.title = "Tabla de puntajes"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        //saveData()

        loadData()

        //val puntaciones = message
        val arrayAdapter: ArrayAdapter<*>
        //val puntaciones = mutableListOf(mutableListOf("pos","\t Nombre\tNivel", "\tPuntos",),mutableListOf("pos","\t Nombre\tNivel", "\tPuntos",),"1.\t Robin\t33 \tb157", "hi", "lego",123,"deweff",35235,"",null,"hoola",321,"0",1231313,12222222222222222,"hola este es yb texti kargi")
        val lvDatos = findViewById<ListView>(R.id.listaPuntaciones)
        //val multitext = findViewById<TextView>(R.id.editTextTextMultiLine)
        //val list = arrayListOf<String>()
        //list.addAll(listOf("1", "2", "3"))

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaGuardada)

        lvDatos.adapter = arrayAdapter
        /*puntosPantalla = "Puntos: " + message.toString()
        multitext.text = puntosPantalla*/
    }

    val Log = Logger.getLogger(MainActivity::class.java.name)
/*
        val posicion1 = arrayListOf<String>("1.\t","Robin\t","33\t","3333")
        val posicion2 = arrayListOf<String>("2.\t","Robin2\t","6756\t","3333")
        val posicion3 = arrayListOf<String>("3.\t","Robin3\t","312\t","3333")
        val posicion4 = arrayListOf<String>("4.\t","Robin4\t","123123\t","3333")
        val posicion5 = arrayListOf<String>("5.\t","Robin5\t","55\t","3333")
        */


        private fun saveData(){

            val posicion1 = "1.\t"+"Robin\t"+"33\t"+"3333"
            val posicion2 = "2.\t"+"Robin\t"+"33\t"+"3333"
            val posicion3 = "3.\t"+"Robin\t"+"33\t"+"3333"
            val posicion4 = "4.\t"+"Robin\t"+"33\t"+"3333"
            val posicion5 = "5.\t"+"Robin\t"+"33\t"+"3333"

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

        private fun loadData(){
            val sharedPreferences = getSharedPreferences("TablaPuntos", Context.MODE_PRIVATE)
            val pos1 = sharedPreferences.getString("P1",null)
            val pos2 = sharedPreferences.getString("P2",null)
            val pos3 = sharedPreferences.getString("P3",null)
            val pos4 = sharedPreferences.getString("P4",null)
            val pos5 = sharedPreferences.getString("P5",null)

            Log.warning("loadData1: "+pos1)
            Log.warning("loadData2: "+pos2)
            Log.warning("loadData3: "+pos3)
            Log.warning("loadData4: "+pos4)
            Log.warning("loadData5: "+pos5)

            //val list = arrayListOf<String>(pos1.toString(),pos2.toString(),pos3.toString(),pos4.toString(),pos5.toString())
            listaGuardada.clear()//borramos contenido actual antes de agregar nuevo
            listaGuardada.addAll(listOf(pos1.toString(),pos2.toString(),pos3.toString(),pos4.toString(),pos5.toString()))

        }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}