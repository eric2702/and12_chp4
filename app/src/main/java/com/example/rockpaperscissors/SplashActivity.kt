package com.example.rockpaperscissors

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rockpaperscissors.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding
    private val activityScope = CoroutineScope(Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        activityScope.launch {
            delay(3000)
            launchToIntro()
        }
    }

    private fun launchToIntro() {
        val mainIntent = Intent(this, RPSIntro::class.java) //dari splash ke main activity
        startActivity(mainIntent)
        finish() //supaya pas diback dari main activity, ga ke splash lagi
    }
}