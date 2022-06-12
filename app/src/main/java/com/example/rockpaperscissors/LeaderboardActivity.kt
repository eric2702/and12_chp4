package com.example.rockpaperscissors

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.adapter.PlayerAdapter
import com.example.rockpaperscissors.databinding.ActivityLeaderboardBinding
import com.example.rockpaperscissors.model.Player

class LeaderboardActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLeaderboardBinding

    private val playerLists = listOf<Player>(
        Player(1,"Ucup", 31),
        Player(2,"Anton", 21),
        Player(3, "Naruto", 100),
        Player(4, "Ronaldo", 34),
        Player(5,"Eric", 20),
        Player(6, "Sasuke", 100),
        Player(7, "Tara", 34),
        Player(8,"Budi", 20),
        Player(9,"Dimas", 20),
        Player(10, "Joko", 100),
        Player(11, "Kezia", 34),
        Player(12,"Dia", 20),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaderboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val playersRecyclerView: RecyclerView = binding.leadRv
        val playersLayoutManager = LinearLayoutManager(this)
        val playerAdapter = PlayerAdapter()

        playersRecyclerView.layoutManager = playersLayoutManager
        playersRecyclerView.adapter = playerAdapter

        playerAdapter.addList(playerLists)


    }
}