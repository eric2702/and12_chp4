package com.example.rockpaperscissors.presenter

import com.example.rockpaperscissors.model.Player

interface UpdatePresenter {
    fun updateDatabase(player: Player)
}