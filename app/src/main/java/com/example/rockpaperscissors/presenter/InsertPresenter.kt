package com.example.rockpaperscissors.presenter

import com.example.rockpaperscissors.model.Player

interface InsertPresenter {
    fun saveToDatabase(newPlayer: Player)
}