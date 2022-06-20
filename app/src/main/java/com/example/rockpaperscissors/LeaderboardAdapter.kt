package com.example.rockpaperscissors

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.databinding.ItemLeaderboardBinding
import com.example.rockpaperscissors.model.Player

class LeaderboardAdapter: RecyclerView.Adapter<LeaderboardAdapter.LeaderBoardViewHolder>() {

    private val listPlayer: MutableList<Player> = mutableListOf()

    fun addList(players: List<Player>) {
        listPlayer.clear()
        listPlayer.addAll(players)
        sortPlayerByScore()
        notifyDataSetChanged()
    }

    class LeaderBoardViewHolder(private val binding: ItemLeaderboardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player) = with(binding) {
            Log.e("player in view holder", player.toString())
            itemTvName.text = player.name
            itemTvScore.text = "Score : ${player.score}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderBoardViewHolder {
        return LeaderBoardViewHolder(
            ItemLeaderboardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LeaderBoardViewHolder, position: Int) {
        return holder.bind(listPlayer[position])
    }

    private fun sortPlayerByScore() {
        listPlayer.sortByDescending { it.score }
    }

    override fun getItemCount(): Int = if (listPlayer.size > 5) 5 else listPlayer.size
}
