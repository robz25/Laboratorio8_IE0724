package com.example.botonessonoros

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

const val verde_MESSAGE = "com.example.verde.MESSAGE"

class verde : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verde)
    }

    // Fetch buttons and text areas
    //val title = findViewById<TextView>(R.id.titleText)
    val scoreText = findViewById<TextView>(R.id.puntuacion)
    val verde = findViewById<Button>(R.id.button1)
    val magenta = findViewById<Button>(R.id.button2)
    val amarillo = findViewById<Button>(R.id.button3)
    val morado = findViewById<Button>(R.id.button4)
    val anaranjado = findViewById<Button>(R.id.button5)
    val azul = findViewById<Button>(R.id.button6)
    val restart = findViewById<ImageView>(R.id.reinicio)
    val activitiesArray = arrayOf(verde::class.java, magenta::class.java, amarillo::class.java, morado::class.java, anaranjado::class.java, azul::class.java)

    // Get count, index and color from intent
    var score = intent.getIntExtra("score", -2)
    var count = intent.getIntExtra("count", -3)
    val colors = intent.getStringArrayListExtra("colors")

    // Update displayed score
    //puntuacion.text = score.toString()



    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.nombreJugador)
        val message = editText.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
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
    override fun onStop() {
        super.onStop()
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }

    fun play_sound_1(view: View){
        //if (mMediaPlayer == null) {
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
    }

    fun play_sound_2(view: View){
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
    }

    fun play_sound_3(view: View){
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
    }

    fun play_sound_4(view: View){
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
    }

    fun play_sound_5(view: View){
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
    }

    fun play_sound_6(view: View){
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
    }

}