package com.example.rockpaperscissors.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.model.Player

class PlayerAdapter : RecyclerView.Adapter<PlayerViewHolder>() {
    private val playerList: MutableList<Player> = mutableListOf()

    fun addList(list: List<Player>) {
        playerList.clear()
        playerList.addAll(list)
        sortPlayerByScore()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val people = playerList[position]
        holder.bind(people)
    }

    override fun getItemCount(): Int = playerList.size

    private fun sortPlayerByScore() {
        playerList.sortByDescending { it.score }
    }
}