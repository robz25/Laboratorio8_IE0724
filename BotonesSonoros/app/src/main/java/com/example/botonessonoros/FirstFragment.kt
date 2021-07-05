package com.example.botonessonoros

import android.media.MediaPlayer
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.botonessonoros.databinding.FragmentFirstBinding
import java.io.IOException

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
var mmMediaPlayer: MediaPlayer? = null

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button1.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
        var mmMediaPlayer: MediaPlayer? = null
        try {
            //mmMediaPlayer = MediaPlayer.create(this, R.raw.a)
            mmMediaPlayer!!.isLooping = false
            mmMediaPlayer!!.start()
        } catch (e: IOException){
            mmMediaPlayer = null
            mmMediaPlayer?.release()
        }
        //} else mMediaPlayer!!.start()
    }

    fun playSound2(view: View){
        // if (mMediaPlayer == null) {
        var mmMediaPlayer: MediaPlayer? = null
        try {
            //mmMediaPlayer = MediaPlayer.create(this, R.raw.b)
            mmMediaPlayer!!.isLooping = false
            mmMediaPlayer!!.start()
        } catch (e: IOException){
            mmMediaPlayer = null
            mmMediaPlayer?.release()
        }
        //  } else mMediaPlayer!!.start()
    }

    fun playSound3(view: View){
        //  if (mMediaPlayer == null) {
        var mmMediaPlayer: MediaPlayer? = null
        try {
            //mmMediaPlayer = MediaPlayer.create(this, R.raw.c)
            mmMediaPlayer!!.isLooping = false
            mmMediaPlayer!!.start()
        } catch (e: IOException){
            mmMediaPlayer = null
            mmMediaPlayer?.release()
        }
        //   } else mMediaPlayer!!.start()
    }

    fun playSound4(view: View){
        //  if (mMediaPlayer == null) {
        var mmMediaPlayer: MediaPlayer? = null
        try {
            //mmMediaPlayer = MediaPlayer.create(this, R.raw.d)
            mmMediaPlayer!!.isLooping = false
            mmMediaPlayer!!.start()
        } catch (e: IOException){
            mmMediaPlayer = null
            mmMediaPlayer?.release()
        }
        //  } else mMediaPlayer!!.start()
    }

    fun pplaySound5(view: View){
        //  if (mMediaPlayer == null) {
        var mmMediaPlayer: MediaPlayer? = null
        try {
            //mmMediaPlayer = MediaPlayer.create(this@FirstFragment, R.raw.e)
            val uri = Settings.System.DEFAULT_ALARM_ALERT_URI ?: Settings.System.DEFAULT_RINGTONE_URI
            uri?.let {
                val mmMediaPlayer = MediaPlayer()

                mmMediaPlayer!!.isLooping = false
                mmMediaPlayer!!.start()
            }
        } catch (e: IOException){
            mmMediaPlayer = null
            mmMediaPlayer?.release()
        }
        //  } else mMediaPlayer!!.start()
    }

    fun playSound6(view: View){
        //   if (mMediaPlayer == null) {
        var mmMediaPlayer: MediaPlayer? = null
        try {
            //mmMediaPlayer = MediaPlayer.create(this, R.raw.f)
            mmMediaPlayer!!.isLooping = false
            mmMediaPlayer!!.start()
        } catch (e: IOException){
            mmMediaPlayer = null
            mmMediaPlayer?.release()
        }
        //   } else mMediaPlayer!!.start()
    }
}