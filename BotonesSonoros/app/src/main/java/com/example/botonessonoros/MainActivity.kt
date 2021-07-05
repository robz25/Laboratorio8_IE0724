package com.example.botonessonoros

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds


const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"
var mMediaPlayer: MediaPlayer? = null

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var mAdView: AdView

    /*private var mLoadAdButton: Button? = null
    private var mInterstitialAd: InterstitialAd? = null*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botInicio = findViewById<Button>(R.id.jugarBoton)
        val botPuntajes = findViewById<Button>(R.id.puntuacionesBoton)
        val botInformacion = findViewById<Button>(R.id.informacionBoton)


        botInicio.setOnClickListener{
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
  /*
        val listaPuntuacionesMain = mutableListOf(mutableListOf("pos","\t Nombre\tNivel", "\tPuntos",),mutableListOf("pos","\t Nombre\tNivel", "\tPuntos",),"1.\t Robin\t33 \tb157", "hi", "lego",123,"deweff",35235,"",null,"hoola",321,"0",1231313,12222222222222222,"hola este es yb texti kargi")
        val listaPuntuacionesMainArrayString = ArrayList(elements: listaPuntuacionesMain<E>)

        //@Parcelize
        data class ExampleModel(
            var stringOne: String,
            var stringTwo: String): Parcelable

        private var exampleMutableList: MutableList<ExampleModel> = arrayListOf()
        exampleMutableList.add(ExampleModel("hello", "world"))
        intent.putExtra("example", ArrayList(exampleMutableList))
*/
        val list = arrayListOf<String>()
        list.addAll(listOf("1", "2", "3"))

        botPuntajes.setOnClickListener{
            val intent = Intent(this, tabla_puntajes::class.java).apply {
                putExtra(EXTRA_MESSAGE, list)
            }
            startActivity(intent)
        }

    /*    fun sendMessage(view: View) {
            val editText = findViewById<EditText>(R.id.nombreJugador)
            val message = editText.text.toString()
            val intent = Intent(this, DisplayMessageActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
        }*/


        botInformacion.setOnClickListener{
            val intent = Intent(this, Informacion::class.java)
            startActivity(intent)
        }

        MobileAds.initialize(this)
        bannderAd()

/*
        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        mInterstitialAd = newInterstitialAd()
        loadInterstitial()

        // Create the load ad button, tries to show an interstitial when clicked.
        //mLoadAdButton = findViewById(R.id.load_ad_button) as Button
        mLoadAdButton!!.isEnabled = false
        mLoadAdButton!!.setOnClickListener {
            showInterstitial()
        }*/
    }

    private fun bannderAd(){//https://www.youtube.com/watch?v=vRqb-ZDe45A
        mAdView = findViewById(R.id.adView1)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        mAdView.adListener = object : AdListener(){
            override fun onAdLoaded() {
                Log.d(TAG, "Ad Loaded")
                //...
            }
        }
    }
        /*    val anuncio1 = findViewById<AdView>(R.id.adView1)
            MobileAds.initialize(this)
            val adRequest = AdRequest.Builder().build()
            anuncio1.loadAd(adRequest)
            val interstitialAd = InterstitialAd(this)
            interstitialAd.setAdUnitId("ca-app-pub-3940256099942544~3347511713")*/

     /*   private fun newInterstitialAd(): InterstitialAd {
            val interstitialAd = InterstitialAd(this)
            interstitialAd.adUnitId = getString(R.string.interstitial_ad_unit_id)
            interstitialAd.adListener = object : AdListener() {
                override fun onAdLoaded() {
                    mLoadAdButton!!.isEnabled = true
                    Toast.makeText(applicationContext, "Ad Loaded", Toast.LENGTH_SHORT).show()
                }
                override fun onAdFailedToLoad(errorCode: Int) {
                    mLoadAdButton!!.isEnabled = true
                    Toast.makeText(applicationContext, "Ad Failed To Load", Toast.LENGTH_SHORT).show()
                }

                override fun onAdClosed() {
                    // Proceed to the next level.
                    // goToNextLevel()
                    Toast.makeText(applicationContext, "Ad Closed", Toast.LENGTH_SHORT).show()
                    tryToLoadAdOnceAgain()
                }
            }
            return interstitialAd
        }

        private fun loadInterstitial() {
            // Disable the load ad button and load the ad.
            mLoadAdButton!!.isEnabled = false
            val adRequest = AdRequest.Builder().build()
            mInterstitialAd!!.loadAd(adRequest)
        }

        private fun showInterstitial() {
            // Show the ad if it is ready. Otherwise toast and reload the ad.
            if (mInterstitialAd != null && mInterstitialAd!!.isLoaded) {
                mInterstitialAd!!.show()
            } else {
                Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show()
                tryToLoadAdOnceAgain()
            }
        }

        private fun tryToLoadAdOnceAgain() {
            mInterstitialAd = newInterstitialAd()
            loadInterstitial()
        }*/






   /* /** Called when the user taps the inicio button */
    fun iniciarVerde(view: View) {
        //val editText = findViewById<EditText>(R.id.editText)
        //val message = editText.text.toString()
        val intent = Intent(this, verde::class.java).apply {
        //    putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }*/


    /** Called when the user taps the Send button */
    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.nombreJugador)
        val message = editText.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
/*
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

    fun playSound2(view: View){
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

    fun playSound3(view: View){
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

    fun playSound4(view: View){
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

    fun playSound5(view: View){
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

    fun playSound6(view: View){
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
    }*/

}