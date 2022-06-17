package com.example.rockpaperscissors.repository

import com.example.rockpaperscissors.model.Player

interface StorageRepository {
    fun getDatabase(result: (playerData: List<Player>) -> Unit)
    fun insertToDatabase(newPlayer: Player, onSaved: () -> Unit)
}