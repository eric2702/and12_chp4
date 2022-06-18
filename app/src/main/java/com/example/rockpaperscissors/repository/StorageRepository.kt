package com.example.rockpaperscissors.repository

import com.example.rockpaperscissors.model.Player

interface StorageRepository {
    fun getDatabase(result: (playerData: List<Player>) -> Unit)
    fun insertToDatabase(newPlayer: Player, onSaved: () -> Unit)
    fun getDatabaseByName(playerName: String, result: (player: List<Player>) -> Unit)

    fun updateDatabase(player: Player, onSaved: () -> Unit)
}