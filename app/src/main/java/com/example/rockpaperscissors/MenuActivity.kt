package com.example.rockpaperscissors

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rockpaperscissors.databinding.ActivityMenuBinding
import com.google.android.material.snackbar.Snackbar

class MenuActivity : AppCompatActivity() {

    private var _binding: ActivityMenuBinding? = null
    private val binding get() = _binding
    private val playerName: String by lazy {
        intent.getStringExtra(PLAYER_NAME).toString()
    }
    private val enemyName: String by lazy {
        intent.getStringExtra(ENEMY_NAME).toString()
    }
    private val enemyType: String by lazy {
        intent.getStringExtra(ENEMY_TYPE).toString()
    }
    private val playerAvatar: Int by lazy {
        intent.getIntExtra(PLAYER_AVATAR, R.drawable.pngwing)
    }
    private val enemyAvatar: Int by lazy {
        intent.getIntExtra(ENEMY_AVATAR, R.drawable.pngwing)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val message = "Selamat Datang $playerName"
        val snackBar = binding?.let { Snackbar.make(it.root, message, Snackbar.LENGTH_LONG) }
        snackBar?.setActionTextColor(Color.parseColor("#ff0000"))
        snackBar?.setAction("Tutup") {
            snackBar.dismiss()
        }
        snackBar?.show()

        binding?.btnPlayGame?.setOnClickListener {
            launchToMainGame()
        }

        binding?.let {
            Glide.with(this)
                .load(playerAvatar)
                .apply(RequestOptions().override(120, 120))
                .into(it.imgPlayer)
        }

        binding?.let {
            Glide.with(this)
                .load(enemyAvatar)
                .apply(RequestOptions().override(120, 120))
                .into(it.imgEnemy)
        }

        binding?.tvPlayer?.text = playerName
        binding?.tvEnemy?.text = enemyName

        binding?.btnLeaderboard?.setOnClickListener {
            launchToLeaderboard()
        }
    }

    private fun launchToMainGame() {
        val intent = Intent(this@MenuActivity, MainActivity::class.java)
        intent.putExtra(MainActivity.PLAYER_NAME, playerName)
        intent.putExtra(MainActivity.PLAYER_AVATAR, playerAvatar)
        intent.putExtra(MainActivity.ENEMY_NAME, enemyName)
        intent.putExtra(MainActivity.ENEMY_AVATAR, enemyAvatar)
        intent.putExtra(MainActivity.ENEMY_TYPE, enemyType)
        startActivity(intent)
    }

    private fun launchToLeaderboard() {
        val intent = Intent(this@MenuActivity, LeaderboardActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val PLAYER_NAME = "PLAYER_NAME"
        const val PLAYER_AVATAR = "PLAYER_AVATAR"
        const val ENEMY_NAME = "ENEMY_NAME"
        const val ENEMY_TYPE = "ENEMY_TYPE"
        const val ENEMY_AVATAR = "ENEMY_AVATAR"
    }
}