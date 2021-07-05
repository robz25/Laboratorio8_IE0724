package com.example.botonessonoros

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.media.MediaPlayer
import android.os.*
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.io.IOException
import java.util.logging.Logger


//var mMediaPlayer: MediaPlayer? = null
//var global?



//var tablaDePosiciones = arrayOf<Array<String>>()//arreglo 2D Strings
//var tablaDePosiciones = arrayOf(arrayOf("1.","","",""),arrayOf("2.","","",""),arrayOf("3.","","",""),arrayOf("4.","","",""),arrayOf("5.","","",""))//arreglo 2D Strings
var listPos1 = listOf<String>()
var listPos2 = listOf<String>()
var listPos3 = listOf<String>()
var listPos4 = listOf<String>()
var listPos5 = listOf<String>()

val textoPuntuacion = null
//var mMediaPlayer: MediaPlayer? = null
var puntos: Int = 0//puntos que va obteniendo el jugador
var niveles: Int = 0//niveles que va obteniendo el jugador

var tiempoAntesNuevaSecuencia: Long = 1200//tiempo para empezar/seguir demo
var tiempoCambioColorBotonesDemo: Long = 800//tiempo que dura cada cambio de color de boton en demo
var tiempoSonidoBotonesDemo: Long = 800//tiempo que dura cada sonido de boton en demo
var nivelesPantalla = ""//niveles a mostrar en texto en pantalla
var puntosPantalla = ""//puntos a mostrar en texto en pantalla
var contadorNivel: Int = 0//contador de niveles para control del juego
var countHandler = 0//contador que se usa para recorrer arreglo de botones a presionar durante demo
var contadorJuego = 0//contador para barrer arreglo de parte del jugador para verificar botones\
// presionados con arreglo
var arregloDeBotones = arrayOf<Int>()//arreglo de botones para guardar secuencias
var demoPatron = false//booleano para saber esta en modo demo o no
var juegoTerminado = false//booleano para indicar si el jugador ya perdió
var jugadorActual = "Anónimo"//nombre para juardar en tabla de records
var puntosRondaActual = mutableListOf("Nombre",0,0)//nombre del jugador, nivel, puntos

class MainActivity3 : AppCompatActivity() {

    //private static final String TAG = "MainActivity3";


    override fun onStop() {
        super.onStop()
        if (mMediaPlayer != null) {//apagar reproductor de medios
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
        //si quito esto al salirse no se borran variables
        reiniciarVariables()//para que vuelva a correr y actualice datos de nombre de jugador

        //saveData()//guardar info de tabla

    /*    // Save all data which you want to persist.
        val savedList = StringBuilder()
        for (task in taskList) {
            savedList.append(task)
            savedList.append(",")
        }

        getSharedPreferences(PREFS_TASKS, Context.MODE_PRIVATE).edit()
            .putString(KEY_TASKS_LIST, savedList.toString()).apply()*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        /*val botonFinalJuego = findViewById<Button>(R.id.finalJuego)//boton de pop up

        botonFinalJuego.setOnClickListener{
            //suave este es para el final final
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }*/

    /*    fun mostrarPopUpfinal(view: View){
            //AlertDialog.Builder dialogo_final = new AlertDialog.Builder(MainActivity3.this);
            val dialogo_final = AlertDialog.Builder(this)
            dialogo_final.setTitle("holi");
            dialogo_final.setTitle("ingresa tu nombre");
            LayoutInflater inflater = this.getLayoutInflater();
            View sign_in = inflater.inflater(R.layout.final_juego,null);

            dialogo_final.setView(dialogo_final);
            dialogo_final.show();

        }*/




        // Actualizar puntaje en view
        //como hago para que se actulicen siempre y no solo al crear?


        val boton1 = findViewById<Button>(R.id.button1)
        val boton2 = findViewById<Button>(R.id.button2)
        val boton3 = findViewById<Button>(R.id.button3)
        val boton4 = findViewById<Button>(R.id.button4)
        val boton5 = findViewById<Button>(R.id.button5)
        val boton6 = findViewById<Button>(R.id.button6)

/*

        fun append(arr: Array<Int>, element: Int): Array<Int> {//https://www.techiedelight.com/add-new-element-array-kotlin/
            val list: MutableList<Int> = arr.toMutableList()
            list.add(element)
            return list.toTypedArray()
        }

        for (i in 1..5) {

            var x: Int = 0
            var arregloDeBotones = arrayOf<Int>()

            while (x < i){
                var numeroDeBoton = (1..6).random()
                arregloDeBotones = append(arregloDeBotones, numeroDeBoton)
                x++
            }
            for (j in arregloDeBotones.indices) {

                when (arregloDeBotones[j]) {
                    1 -> Handler().postDelayed(Runnable { boton1.performClick() }, 1000)
                    2 -> Handler().postDelayed(Runnable { boton2.performClick() }, 1000)
                    3 -> Handler().postDelayed(Runnable { boton3.performClick() }, 1000)
                    4 -> Handler().postDelayed(Runnable { boton4.performClick() }, 1000)
                    5 -> Handler().postDelayed(Runnable { boton5.performClick() }, 1000)
                    6 -> Handler().postDelayed(Runnable { boton6.performClick() }, 1000)
                    else -> { // Note the block
                        print("x no es 1 o 2")
                    }
                }
            }

        }*/
/*


        Handler().postDelayed(Runnable { boton1.performClick() }, 4000)//presional bootn automaticamente
*/
        //tocaToca()

        /*val botSalir = findViewById<Button>(R.id.botonSalir)

        botSalir.setOnClickListener{
            //eliminar aqui todas las variables o reiniciarlas
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }*/
        //botonReinicio.performClick()//no sirve
        //patron(v: View)
        loadData()//cargar tabla de puntos ANTES de SAVE DATA en reiniciarVariables
        reiniciarVariables()//reiniciamos variables cada vez que alguien entra a actividad
        puntos = 0//reinicamos variables
        niveles = 0
        juegoTerminado = false
        patron(null)

        val actionBar = supportActionBar
        actionBar!!.title = "Jugando Simón dice"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)


    }

    val Log = Logger.getLogger(MainActivity::class.java.name)

    private fun loadData(){
        val sharedPreferences = getSharedPreferences("TablaPuntos", Context.MODE_PRIVATE)
        val pos1 = sharedPreferences.getString("P1",null)
        val pos2 = sharedPreferences.getString("P2",null)
        val pos3 = sharedPreferences.getString("P3",null)
        val pos4 = sharedPreferences.getString("P4",null)
        val pos5 = sharedPreferences.getString("P5",null)
        val njugador = sharedPreferences.getString("jugador",null)

        if (njugador != null) {
            jugadorActual = njugador
        }

        Log.warning("loadData1: "+pos1)
        Log.warning("loadData2: "+pos2)
        Log.warning("loadData3: "+pos3)
        Log.warning("loadData4: "+pos4)
        Log.warning("loadData5: "+pos5)
        /*
        Log.d(TAG, "loadData: $pos1")
        Log.d(TAG, "loadData: $pos2")
        Log.d(TAG, "loadData: $pos3")
        Log.d(TAG, "loadData: $pos4")
        Log.d(TAG, "loadData: $pos5")
        */


        val delim = '\t'

        if (pos1 != null) {
            listPos1 = pos1.split(delim)
        }else{
            listPos1 = listOf<String>("","","","0")
        }
        if (pos2 != null) {
            listPos2 = pos2.split(delim)
        }else{
            listPos2 = listOf<String>("","","","0")
        }
        if (pos3 != null) {
            listPos3 = pos3.split(delim)
        }else{
            listPos3 = listOf<String>("","","","0")
        }
        if (pos4 != null) {
            listPos4 = pos4.split(delim)
        }else{
            listPos4 = listOf<String>("","","","0")
        }
        if (pos5 != null) {
            listPos5 = pos5.split(delim)
        }else{
            listPos5 = listOf<String>("","","","0")
        }

        //val list = arrayListOf<String>(pos1.toString(),pos2.toString(),pos3.toString(),pos4.toString(),pos5.toString())
        //listaGuardada.clear()//borramos contenido actual antes de agregar nuevo
        //listaGuardada.addAll(listOf(pos1.toString(),pos2.toString(),pos3.toString(),pos4.toString(),pos5.toString()))

    }

    private fun saveData(){
        /*listPos1 = listOf<String>("1","F","1","0")
        listPos2 = listOf<String>("","","","0")
        listPos3 = listOf<String>("3","I","3",)
        listPos4 = listOf<String>("4","L","4","")
        listPos5 = listOf<String>("5","U","5","0")*/
        //funcional
        val posicion1 = listPos1.elementAtOrNull(0) +"\t"+listPos1.elementAtOrNull(1) +"\t"+listPos1.elementAtOrNull(2) +"\t"+listPos1.elementAtOrNull(3)
        val posicion2 = listPos2.elementAtOrNull(0) +"\t"+listPos2.elementAtOrNull(1) +"\t"+listPos2.elementAtOrNull(2) +"\t"+listPos2.elementAtOrNull(3)
        val posicion3 = listPos3.elementAtOrNull(0) +"\t"+listPos3.elementAtOrNull(1) +"\t"+listPos3.elementAtOrNull(2) +"\t"+listPos3.elementAtOrNull(3)
        val posicion4 = listPos4.elementAtOrNull(0) +"\t"+listPos4.elementAtOrNull(1) +"\t"+listPos4.elementAtOrNull(2) +"\t"+listPos4.elementAtOrNull(3)
        val posicion5 = listPos5.elementAtOrNull(0) +"\t"+listPos5.elementAtOrNull(1) +"\t"+listPos5.elementAtOrNull(2) +"\t"+listPos5.elementAtOrNull(3)
        val njugador = jugadorActual
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
            putString("jugador",njugador)
        }.apply()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    val positiveButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(applicationContext,
            android.R.string.yes, Toast.LENGTH_SHORT).show()
    }

   /* fun withItems(view: View?) {

        val items = arrayOf("Red", "Puntos obtenidos: "+puntos.toString())
        val builder = AlertDialog.Builder(this)

        //val builder = AlertDialog.Builder(ContextThemeWrapper(this, android.R.style.Widget_DatePicker))

        with(builder)
        {
            setTitle("Fin del juego")
            setItems(items) { dialog, which ->
                Toast.makeText(applicationContext, items[which] + " is clicked", Toast.LENGTH_SHORT).show()
            }

            setPositiveButton("OK", positiveButtonClick)
            show()
        }
    }*/
 /*   alertDialogBuilder.setMessage("Your username or password is incorrect! Please try again!").setCancelable(false)
    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            // if this button is clicked, close
            // current activity
            // MainActivity.this.finish();
            dialog.cancel();
        }
    }
    );*/

    fun mostrarPopUpfinal(view: View?) {

        //val items = arrayOf("Nivel alcanzado: "+niveles.toString(), "Puntos obtenidos: "+puntos.toString())

        val builder = AlertDialog.Builder(this)

        val inflater = layoutInflater
        builder.setTitle("Fin del juego")

        val nivelFinal = "Nivel alcanzado: "+niveles.toString()

        val puntajeFinal =  nivelFinal + "\nPuntos obtenidos: "+puntos.toString()

        builder.setMessage(puntajeFinal)

        /*builder.setItems(items) { dialog, //which ->
            //Toast.makeText(applicationContext, items[which] + " is clicked", Toast.LENGTH_SHORT).show()
        }*/
        val dialogLayout = inflater.inflate(R.layout.final_juego, null)
        builder.setView(dialogLayout)
        val editText  = dialogLayout.findViewById<EditText>(R.id.nombreJugador)
        editText.setHint(jugadorActual)
        val stringVacio = ""
        var textoIngresado = editText.text.toString()
        if(textoIngresado == stringVacio) {
            //jugadorActual = jugadorActual
        }else
        {
            jugadorActual = textoIngresado
        }

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->

            textoIngresado = editText.text.toString()
            if(textoIngresado == stringVacio) {
                //jugadorActual = jugadorActual
            }else
            {
                jugadorActual = textoIngresado
            }
            juegoTerminado = true//indicar que juego terminó hasta que jugador presionó Ok

            //dialogInterface -> Toast.makeText(applicationContext
            //"Record guardado como: "+ jugadorActual
            //Toast.LENGTH_SHORT).show()}
        })
        //builder.setPositiveButton("OK") { dialogInterface, i -> Toast.makeText(applicationContext, "EditText is  + textoPuntosFinales.text.toString()", Toast.LENGTH_SHORT).show() }
        builder.show()
    }

    val handler = Handler()


    val runnable: Runnable = object : Runnable {

        override fun run() {

            val boton1 = findViewById<Button>(R.id.button1)
            val boton2 = findViewById<Button>(R.id.button2)
            val boton3 = findViewById<Button>(R.id.button3)
            val boton4 = findViewById<Button>(R.id.button4)
            val boton5 = findViewById<Button>(R.id.button5)
            val boton6 = findViewById<Button>(R.id.button6)
            // need to do tasks on the UI thread
            if(countHandler < contadorNivel) {
                when (arregloDeBotones[countHandler]) {//aca esta el problema, en accesar al arreglo
                    //when (2) {
                    1 -> {
                        Handler().postDelayed(Runnable { boton1.performClick() }, tiempoSonidoBotonesDemo)
                        //Thread.sleep(1000);
                    }
                    2 -> {
                        Handler().postDelayed(Runnable { boton2.performClick() }, tiempoSonidoBotonesDemo)
                        //Thread.sleep(1000);
                    }
                    3 -> {
                        Handler().postDelayed(Runnable { boton3.performClick() }, tiempoSonidoBotonesDemo)
                        //                      Thread.sleep(1000);
                    }
                    4 -> {
                        Handler().postDelayed(Runnable { boton4.performClick() }, tiempoSonidoBotonesDemo)
                        //Thread.sleep(1000);
                    }
                    5 -> {
                        Handler().postDelayed(Runnable { boton5.performClick() }, tiempoSonidoBotonesDemo)
                        //Thread.sleep(1000);
                    }
                    6 -> {
                        Handler().postDelayed(Runnable { boton6.performClick() }, tiempoSonidoBotonesDemo)
                        //Thread.sleep(1000);
                    }
                    else -> { // innecesario
                        Handler().postDelayed(Runnable { }, tiempoSonidoBotonesDemo)
                        //print("x no es 1 o 2")
                    }
                }
            }else{
                Handler().postDelayed(Runnable { }, tiempoSonidoBotonesDemo)
            }

            //Log.d(TAG, "Run test count: $count")
            if (countHandler++ <= contadorNivel) {// hay que ponerle -1 par que no cuente uno mas??? por que??
                handler.postDelayed(this, tiempoSonidoBotonesDemo)
                //puntos = 999//borrar luego
                //variables de View
                //val textoPuntuacion = findViewById<TextView>(R.id.puntuacion)
                //textoPuntuacion.text = countHandler.toString()
                //countHandler++
            //}else if (countHandler++ == contadorNivel){
             //   handler.postDelayed(this, 1000)

                }else{//al terminar arreglo habilito nuevamente los botones
                    countHandler-=3//para que quede en la posicion actual del arreglo

                    // si contadorNivel = x, handler se llama x + 2 veces dentro de sí
                    // pero countHandler aumenta cada entrada por lo cual aumenta x + 3 veces
                    // recordar que la primera vez que entra el handler se llama de afuera
                    boton1.isEnabled = true
                    boton2.isEnabled = true
                    boton3.isEnabled = true
                    boton4.isEnabled = true
                    boton5.isEnabled = true
                    boton6.isEnabled = true
                    demoPatron = false
                }
        }
    }

    fun append(arr: Array<Int>, element: Int): Array<Int> {//https://www.techiedelight.com/add-new-element-array-kotlin/
        val list: MutableList<Int> = arr.toMutableList()
        list.add(element)
        return list.toTypedArray()
    }

    fun patron(view: View?){//? nos permite poner null para cuando quiero llamar patron aca dentro
        //puntos = 0//reinicar puntaje


        if(juegoTerminado){//reiniciar variables de puntaje si se inicia un juego nuevo
            puntos = 0
            niveles = 0
            juegoTerminado = false
            val textoPuntuacion = findViewById<TextView>(R.id.puntuacion)//borrar puntaje en pantalla inmediatamente
            puntosPantalla = "Puntos: "+puntos.toString()
            textoPuntuacion.text = puntosPantalla
        }
        //val botonReinicio = findViewById<ImageView>(R.id.reinicio)
        val botonReinicio = findViewById<Button>(R.id.reinicio_boton)
        botonReinicio.visibility = View.INVISIBLE

        ++contadorNivel //aumentar nivel para aumentar tamaño de arreglo en una unidad
        ++niveles//aumentar el nivel alcanzado por el jugador
        val textoNivel = findViewById<TextView>(R.id.textView3)
        nivelesPantalla = "Nivel "+niveles.toString()
        textoNivel.text = nivelesPantalla//actualizar puntos en pantalla

        val boton1 = findViewById<Button>(R.id.button1)
        val boton2 = findViewById<Button>(R.id.button2)
        val boton3 = findViewById<Button>(R.id.button3)
        val boton4 = findViewById<Button>(R.id.button4)
        val boton5 = findViewById<Button>(R.id.button5)
        val boton6 = findViewById<Button>(R.id.button6)

        //var ifCounter: Int = 0
        //if(ifCounter++ < 5){
        var i: Int = 0
        while (i < contadorNivel){
        //for (i in 1..5) {

            var x: Int = 0
            //arregloDeBotones = arrayOf<Int>()//lo declaro arriba como global
            //necesito reinicar el arreglo de alguna forma aqui

            //while (x < i){
                var numeroDeBoton = (1..6).random()
                arregloDeBotones = append(arregloDeBotones, numeroDeBoton)
                x++

            i++
        }
        boton1.isEnabled = false
        boton2.isEnabled = false
        boton3.isEnabled = false
        boton4.isEnabled = false
        boton5.isEnabled = false
        boton6.isEnabled = false
        demoPatron = true//ignorir the last element if repeated? or always

        //contadorNivel++
        countHandler = 0//reinicar cuenta para handler cada vez que corra, va a inicidar desde la
        //primera posicion para ir aumentando la secuencia.
        //hacer diferentes modos de juego, uno loco con diferentes secuencias, basta con no ponerle 0
        //uno con velocidad mas rapida
        //uno con velocidad que vaya aumentando cada 10 puntos y sonido también debe ser más rápido
        handler.post(runnable);//lamar a handlers por primera vez
        //handler se llama y el codigo sigue
        contadorJuego = 0 //reiniciar contador  de verificacion tras inicio de nuevo demo
        //contadorNivel++ //aumentar el contador de nivel cada vez que se llame a generar el patron se cae

    }

    fun sendViewToBack(child: View) {
        val parent = child.parent as ViewGroup
        if (null != parent) {
            parent.removeView(child)
            parent.addView(child, 0)
        }
    }

    fun Fragment.vibratePhone() {
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(200)
        }
    }

    fun sobreEscribirPuntuaciones(){//para qye actualice nombre de usuario sin desplazar al entrar y salir
        if(puntos >= listPos1.elementAtOrNull(3)?.toInt() ?:0 ){//

            listPos1= listOf<String>("1", jugadorActual, niveles.toString(), puntos.toString())
        }else if(puntos >= listPos2.elementAtOrNull(3)?.toInt() ?:0 ){

            listPos2= listOf<String>("2", jugadorActual, niveles.toString(), puntos.toString())
        }else if(puntos >= listPos3.elementAtOrNull(3)?.toInt() ?:0 ){

            listPos3= listOf<String>("3", jugadorActual, niveles.toString(), puntos.toString())
        }else if(puntos >= listPos4.elementAtOrNull(3)?.toInt() ?:0 ){

            listPos4= listOf<String>("4", jugadorActual, niveles.toString(), puntos.toString())
        }else if(puntos >= listPos5.elementAtOrNull(3)?.toInt() ?:0 ){
            listPos5= listOf<String>("5", jugadorActual, niveles.toString(), puntos.toString())
        }
        saveData()//guardar info de tabla
    }

    fun insertarNuevaPuntuacion(){//solo la primera vez que se genera nueva puntuacion
        if(puntos >= listPos1.elementAtOrNull(3)?.toInt() ?:0 ){//
            listPos5 = listOf<String>("5", listPos4.elementAtOrNull(1).toString(), listPos4.elementAtOrNull(2).toString(), listPos4.elementAtOrNull(3).toString())
            listPos4 = listOf<String>("4", listPos3.elementAtOrNull(1).toString(), listPos3.elementAtOrNull(2).toString(), listPos3.elementAtOrNull(3).toString())
            listPos3 = listOf<String>("3", listPos2.elementAtOrNull(1).toString(), listPos2.elementAtOrNull(2).toString(), listPos2.elementAtOrNull(3).toString())
            listPos2 = listOf<String>("2", listPos1.elementAtOrNull(1).toString(), listPos1.elementAtOrNull(2).toString(), listPos1.elementAtOrNull(3).toString())
            listPos1 = listOf<String>("1", jugadorActual, niveles.toString(), puntos.toString())
        }else if(puntos >= listPos2.elementAtOrNull(3)?.toInt() ?:0 ){
            listPos5 = listOf<String>("5", listPos4.elementAtOrNull(1).toString(), listPos4.elementAtOrNull(2).toString(), listPos4.elementAtOrNull(3).toString())
            listPos4 = listOf<String>("4", listPos3.elementAtOrNull(1).toString(), listPos3.elementAtOrNull(2).toString(), listPos3.elementAtOrNull(3).toString())
            listPos3 = listOf<String>("3", listPos2.elementAtOrNull(1).toString(), listPos2.elementAtOrNull(2).toString(), listPos2.elementAtOrNull(3).toString())
            listPos2= listOf<String>("2", jugadorActual, niveles.toString(), puntos.toString())
        }else if(puntos >= listPos3.elementAtOrNull(3)?.toInt() ?:0 ){
            listPos5 = listOf<String>("5", listPos4.elementAtOrNull(1).toString(), listPos4.elementAtOrNull(2).toString(), listPos4.elementAtOrNull(3).toString())
            listPos4 = listOf<String>("4", listPos3.elementAtOrNull(1).toString(), listPos3.elementAtOrNull(2).toString(), listPos3.elementAtOrNull(3).toString())
            listPos3= listOf<String>("3", jugadorActual, niveles.toString(), puntos.toString())
        }else if(puntos >= listPos4.elementAtOrNull(3)?.toInt() ?:0 ){
            listPos5 = listOf<String>("5", listPos4.elementAtOrNull(1).toString(), listPos4.elementAtOrNull(2).toString(), listPos4.elementAtOrNull(3).toString())
            listPos4= listOf<String>("4", jugadorActual, niveles.toString(), puntos.toString())
        }else if(puntos >= listPos5.elementAtOrNull(3)?.toInt() ?:0 ){
            listPos5= listOf<String>("5", jugadorActual, niveles.toString(), puntos.toString())
        }
        saveData()//guardar info de tabla
    }

    fun reiniciarVariables(){//al finalizar el juego
       /* var fragment = supportFragmentManager.findFragmentById(R.id.VIBRATOR_SERVICE) as WebViewFragment
        fragment.callAboutUsActivity()
        Fragment.vibratePhone()*/
        //puntajes se reinician dentro de patron
        //val botonReinicio = findViewById<ImageView>(R.id.reinicio)
        //puntosRondaActual = mutableListOf(jugadorActual,niveles,puntos)//poner resultados de ronda en lista
        //listPos1= listOf<String>("45", jugadorActual, niveles.toString(), puntos.toString())
        //listPos2= listOf<String>("43", jugadorActual, niveles.toString(), puntos.toString())
        sobreEscribirPuntuaciones()
        //listPos1.elementAtOrNull(0) +"\t"+listPos1.elementAtOrNull(1) +"\t"+listPos1.elementAtOrNull(2) +"\t"+listPos1.elementAtOrNull(3)
        val botonReinicio = findViewById<Button>(R.id.reinicio_boton)
        botonReinicio.visibility = View.VISIBLE
        //val boton3 = findViewById<Button>(R.id.button3)
        //val boton4 = findViewById<Button>(R.id.button4)
        //sendViewToBack(boton3)
        //sendViewToBack(boton4)
        //botonReinicio.bringToFront()//traer boton al frente, no se por qué sale atras
        //botonReinicio.sendToBack()
      //  puntos = 0//reinicamos variables pero las actualizamos en la pantalla hasta patron
      //  niveles = 0
        //juegoTerminado = true//indicar final de juego para reiniciar puntajes si inicia otro juego
        contadorJuego = 0
        countHandler = 0
        contadorNivel = 0
        arregloDeBotones = arrayOf<Int>()

        saveData()//guardar info de tabla

    }



    // 2. Pause playback
    fun pauseSound(view: View) {
        if (mMediaPlayer != null && mMediaPlayer!!.isPlaying) mMediaPlayer!!.pause()
    }

    // 3. {optional} Stops playback
    fun stopSound(view: View) {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }

    // 4. Closes the MediaPlayer when the app is closed
    /*override fun onStop() {
        super.onStop()
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }*/

    fun playSound1(view: View){
        val boton1 = findViewById<Button>(R.id.button1)
        if(demoPatron){//modo demo
            Handler().postDelayed(Runnable { boton1.setBackgroundColor(Color.parseColor("#4CAF50")) }, tiempoCambioColorBotonesDemo)
        }else if (juegoTerminado == false && contadorNivel > 0 && arregloDeBotones[contadorJuego] == 1){//modo de juego y toca este boton
            puntos++
            val textoPuntuacion = findViewById<TextView>(R.id.puntuacion)
            puntosPantalla = "Puntos: "+puntos.toString()
            textoPuntuacion.text = puntosPantalla
            ++contadorJuego//no importa que aumente si es posicion final, igual en patron() se borra
            if(contadorJuego >= contadorNivel) {//fin de secuencia exitoso
                Handler().postDelayed(Runnable { patron(null) }, tiempoAntesNuevaSecuencia)
            }

        }else if(juegoTerminado == false){//modo de juego y boton equivocado
            mostrarPopUpfinal(null)//aqui se actualiza el nombre del jugador
            insertarNuevaPuntuacion()
            reiniciarVariables()//y guardar resultados en Preferences
            //reinicio de variables
            //y
            //fin de juego
        }

        var mMediaPlayer: MediaPlayer? = null
        try {
            mMediaPlayer = MediaPlayer.create(this, R.raw.a)
            mMediaPlayer!!.isLooping = false
            mMediaPlayer!!.start()
        } catch (e: IOException){
            mMediaPlayer = null
            mMediaPlayer?.release()
        }
        //} else mMediaPlayer!!.start()
        if(demoPatron) {
            boton1.setBackgroundColor(Color.parseColor("#b7ffb9"))
        }
    }

    fun playSound2(view: View){
        val boton2 = findViewById<Button>(R.id.button2)
        if(demoPatron){
            Handler().postDelayed(Runnable { boton2.setBackgroundColor(Color.parseColor("#E91E63")) }, tiempoCambioColorBotonesDemo)
        }else if (juegoTerminado == false && contadorNivel > 0 && arregloDeBotones[contadorJuego] == 2){
            ++contadorJuego
            puntos++
            val textoPuntuacion = findViewById<TextView>(R.id.puntuacion)
            puntosPantalla = "Puntos: "+puntos.toString()
            textoPuntuacion.text = puntosPantalla
            if(contadorJuego >= contadorNivel) {//fin de secuencia exitoso
                Handler().postDelayed(Runnable { patron(null) }, tiempoAntesNuevaSecuencia)
            }
        }else if(juegoTerminado == false){
            mostrarPopUpfinal(null)//aqui se actualiza el nombre del jugador
            insertarNuevaPuntuacion()
            reiniciarVariables()//y guardar resultados en Preferences
            //reinicio de variables
            //y
            //fin de juego
        }

        // if (mMediaPlayer == null) {
        var mMediaPlayer: MediaPlayer? = null
        try {
            mMediaPlayer = MediaPlayer.create(this, R.raw.b)
            mMediaPlayer!!.isLooping = false
            mMediaPlayer!!.start()
        } catch (e: IOException){
            mMediaPlayer = null
            mMediaPlayer?.release()
        }
        //  } else mMediaPlayer!!.start()
        if(demoPatron){
            boton2.setBackgroundColor(Color.parseColor("#ffb4cd"))
        }
    }

    fun playSound3(view: View){
        val boton3 = findViewById<Button>(R.id.button3)
        if(demoPatron){
            Handler().postDelayed(Runnable { boton3.setBackgroundColor(Color.parseColor("#FFCE1B")) }, tiempoCambioColorBotonesDemo)
        }else if (juegoTerminado == false && contadorNivel > 0 && arregloDeBotones[contadorJuego] == 3){
            ++contadorJuego
            puntos++
            val textoPuntuacion = findViewById<TextView>(R.id.puntuacion)
            puntosPantalla = "Puntos: "+puntos.toString()
            textoPuntuacion.text = puntosPantalla
            if(contadorJuego >= contadorNivel) {//fin de secuencia exitoso
                Handler().postDelayed(Runnable { patron(null) }, tiempoAntesNuevaSecuencia)
            }
        }else if(juegoTerminado == false){
            mostrarPopUpfinal(null)//aqui se actualiza el nombre del jugador
            insertarNuevaPuntuacion()
            reiniciarVariables()//y guardar resultados en Preferences
            //reinicio de variables
            //y
            //fin de juego
        }
       // puntos++//borrar luego
        //variables de View
        //  if (mMediaPlayer == null) {
        var mMediaPlayer: MediaPlayer? = null
        try {
            mMediaPlayer = MediaPlayer.create(this, R.raw.c)
            mMediaPlayer!!.isLooping = false
            mMediaPlayer!!.start()
        } catch (e: IOException){
            mMediaPlayer = null
            mMediaPlayer?.release()
        }
        //   } else mMediaPlayer!!.start()
        if(demoPatron){
            boton3.setBackgroundColor(Color.parseColor("#fff9c0"))
        }
    }

    fun playSound4(view: View){
        val boton4 = findViewById<Button>(R.id.button4)
        if(demoPatron){
            Handler().postDelayed(Runnable { boton4.setBackgroundColor(Color.parseColor("#3F51B5")) }, tiempoCambioColorBotonesDemo)
        }else if (juegoTerminado == false && contadorNivel > 0 && arregloDeBotones[contadorJuego] == 4){
            ++contadorJuego
            puntos++
            val textoPuntuacion = findViewById<TextView>(R.id.puntuacion)
            puntosPantalla = "Puntos: "+puntos.toString()
            textoPuntuacion.text = puntosPantalla
            if(contadorJuego >= contadorNivel) {//fin de secuencia exitoso
                Handler().postDelayed(Runnable { patron(null) }, tiempoAntesNuevaSecuencia)
            }
        }else if(juegoTerminado == false){
            mostrarPopUpfinal(null)//aqui se actualiza el nombre del jugador
            insertarNuevaPuntuacion()
            reiniciarVariables()//y guardar resultados en Preferences
            //reinicio de variables
            //y
            //fin de juego
        }
        //boton4.setBackgroundColor(Color.parseColor("#f7fefc"));
        //boton4.setBackgroundColor(0xff09c0)//primer byte es transparencia
      //  puntos++//borrar luego
        //variables de View
        //  if (mMediaPlayer == null) {
        var mMediaPlayer: MediaPlayer? = null
        try {
            mMediaPlayer = MediaPlayer.create(this, R.raw.d)
            mMediaPlayer!!.isLooping = false
            mMediaPlayer!!.start()
        } catch (e: IOException){
            mMediaPlayer = null
            mMediaPlayer?.release()
        }
        //  } else mMediaPlayer!!.start()
        if(demoPatron){
            boton4.setBackgroundColor(Color.parseColor("#adb8f6"))
        }
        //boton4.setBackgroundColor(0xffffff)//no sirve lo deja transparente
    }

    fun playSound5(view: View){
        val boton5 = findViewById<Button>(R.id.button5)
        if(demoPatron){
            Handler().postDelayed(Runnable { boton5.setBackgroundColor(Color.parseColor("#FF5722")) }, tiempoCambioColorBotonesDemo)
        }else if (juegoTerminado == false && contadorNivel > 0 && arregloDeBotones[contadorJuego] == 5){
            ++contadorJuego
            puntos++
            val textoPuntuacion = findViewById<TextView>(R.id.puntuacion)
            puntosPantalla = "Puntos: "+puntos.toString()
            textoPuntuacion.text = puntosPantalla
            if(contadorJuego >= contadorNivel) {//fin de secuencia exitoso
                Handler().postDelayed(Runnable { patron(null) }, tiempoAntesNuevaSecuencia)
            }
        }else if(juegoTerminado == false){
            mostrarPopUpfinal(null)//aqui se actualiza el nombre del jugador
            insertarNuevaPuntuacion()
            reiniciarVariables()//y guardar resultados en Preferences
            //reinicio de variables
            //y
            //fin de juego
        }
        //puntos++//borrar luego
        //variables de View
        //  if (mMediaPlayer == null) {
        var mMediaPlayer: MediaPlayer? = null
        try {
            mMediaPlayer = MediaPlayer.create(this, R.raw.e)
            mMediaPlayer!!.isLooping = false
            mMediaPlayer!!.start()
        } catch (e: IOException){
            mMediaPlayer = null
            mMediaPlayer?.release()
        }
        //  } else mMediaPlayer!!.start()
        if(demoPatron){
            boton5.setBackgroundColor(Color.parseColor("#fabaa6"))
        }
    }

    fun playSound6(view: View){
        val boton6 = findViewById<Button>(R.id.button6)
        if(demoPatron){
            Handler().postDelayed(Runnable { boton6.setBackgroundColor(Color.parseColor("#00BCD4")) }, tiempoCambioColorBotonesDemo)
        }
        else if (juegoTerminado == false && contadorNivel > 0 && arregloDeBotones[contadorJuego] == 6){
            ++contadorJuego
            puntos++
            val textoPuntuacion = findViewById<TextView>(R.id.puntuacion)
            puntosPantalla = "Puntos: "+puntos.toString()
            textoPuntuacion.text = puntosPantalla
            if(contadorJuego >= contadorNivel) {//fin de secuencia exitoso
                Handler().postDelayed(Runnable { patron(null) }, tiempoAntesNuevaSecuencia)
            }
        }else if(juegoTerminado == false){
            mostrarPopUpfinal(null)//aqui se actualiza el nombre del jugador
            insertarNuevaPuntuacion()
            reiniciarVariables()//y guardar resultados en Preferences
            //reinicio de variables
            //y
            //fin de juego
        }
       // puntos++//borrar luego

        //   if (mMediaPlayer == null) {
        var mMediaPlayer: MediaPlayer? = null
        try {
            mMediaPlayer = MediaPlayer.create(this, R.raw.f)
            mMediaPlayer!!.isLooping = false
            mMediaPlayer!!.start()
        } catch (e: IOException){
            mMediaPlayer = null
            mMediaPlayer?.release()
        }
        //   } else mMediaPlayer!!.start()
        if(demoPatron){
            boton6.setBackgroundColor(Color.parseColor("#bcf1f8"))
        }
    }
}