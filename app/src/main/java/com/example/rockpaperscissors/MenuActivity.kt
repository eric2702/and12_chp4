package com.example.rockpaperscissors

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rockpaperscissors.databinding.ActivityMenuBinding
import com.google.android.material.snackbar.Snackbar

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var nameData: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nameData = intent.getStringExtra("NAME_DATA").toString()

        setMenuName()
        val message = "Selamat Datang $nameData"
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
        snackbar.setActionTextColor(Color.parseColor("#ff0000"))
        snackbar.setAction("Tutup") {
            snackbar.dismiss()
        }
        snackbar.show()
        binding.comMenuImg.setOnClickListener {
            launchToMain()
        }
        binding.comMenuTxt.setOnClickListener {
            launchToMain()
        }
        binding.pemainMenuImg.setOnClickListener {
            launchToMain2()
        }
        binding.pemainMenuTxt.setOnClickListener {
            launchToMain2()
        }
        binding.btnLead.setOnClickListener {
            launchToLeaderboard()
        }
    }

    private fun launchToMain() {
        val mainIntent = Intent(this, MainActivity::class.java) //dari splash ke main activity
        mainIntent.putExtra("NAME_DATA", nameData)
        startActivity(mainIntent)
    }

    private fun launchToMain2() {
        val main2Intent = Intent(this, Main2Activity::class.java) //dari splash ke main activity
        main2Intent.putExtra("NAME_DATA", nameData)
        startActivity(main2Intent)
    }

    private fun launchToLeaderboard() {
        val leadIntent = Intent(this, LeaderboardActivity::class.java)
        startActivity(leadIntent)
    }

    @SuppressLint("SetTextI18n")
    private fun setMenuName() {
        binding.pemainMenuTxt.text = "$nameData VS Pemain"
        binding.comMenuTxt.text = "$nameData VS CPU"
    }
}