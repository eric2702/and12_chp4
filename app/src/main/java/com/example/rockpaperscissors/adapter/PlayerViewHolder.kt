package com.example.rockpaperscissors.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.databinding.ItemPlayerBinding
import com.example.rockpaperscissors.model.Player

class PlayerViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    fun bind(player: Player) = ItemPlayerBinding.bind(itemView).run {
        itemTvName.text = player.name
        itemTvScore.text = player.score.toString()

        itemView.setOnClickListener {
            Toast.makeText(itemView.context, "clicked on ${player.name}", Toast.LENGTH_SHORT).show()
        }
    }
}