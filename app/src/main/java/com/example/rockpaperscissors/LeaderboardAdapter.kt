package com.example.rockpaperscissors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.databinding.ItemLeaderboardBinding
import com.example.rockpaperscissors.model.Player

class LeaderboardAdapter(private val listPlayer: List<Player>)
    :RecyclerView.Adapter<LeaderboardAdapter.LeaderBoardViewHolder>() {

    class LeaderBoardViewHolder(private val binding: ItemLeaderboardBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player) = with(binding) {
            itemTvName.text = player.name
            itemTvScore.text = player.score.toString()
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

    override fun getItemCount(): Int = if (listPlayer.size > 5) 5 else listPlayer.size
}
