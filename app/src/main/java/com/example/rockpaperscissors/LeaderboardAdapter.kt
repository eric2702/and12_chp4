package com.example.rockpaperscissors

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.ViewTarget
import com.example.rockpaperscissors.databinding.ItemLeaderboardBinding
import com.example.rockpaperscissors.model.Player

class LeaderboardAdapter: RecyclerView.Adapter<LeaderboardAdapter.LeaderBoardViewHolder>() {

    private val listPlayer: MutableList<Player> = mutableListOf()

    fun addList(players: List<Player>) {
        listPlayer.clear()
        listPlayer.addAll(players)
    }

    class LeaderBoardViewHolder(private val binding: ItemLeaderboardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player) = with(binding) {
            itemTvName.text = player.name
            itemTvScore.text = "Score : ${player.score}"
//            Glide.with(itemView.context)
//                .load(player.avatar)
//                .apply(RequestOptions().override(60, 60))
//                .into(imgAvatar)
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
