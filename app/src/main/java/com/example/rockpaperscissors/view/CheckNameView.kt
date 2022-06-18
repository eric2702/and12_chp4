package com.example.rockpaperscissors.view

import android.content.Context
import com.example.rockpaperscissors.model.Player

interface CheckNameView {
    fun context(): Context
    fun onCheckDatabase(player: Player)
}