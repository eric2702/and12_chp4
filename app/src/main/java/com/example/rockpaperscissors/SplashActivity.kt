package com.example.rockpaperscissors

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            launchToIntro()
        }, 3000)
    }

    private fun launchToIntro() {
        val mainIntent = Intent(this, RPSIntro::class.java) //dari splash ke main activity
        startActivity(mainIntent)
        finish() //supaya pas diback dari main activity, ga ke splash lagi
    }
}