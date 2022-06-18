package com.example.rockpaperscissors.repository

import android.content.Context
import com.example.rockpaperscissors.model.Player
import com.example.rockpaperscissors.sources.PlayerDao
import com.example.rockpaperscissors.sources.PlayerDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class StorageRepositoryImpl(private val context: Context) : StorageRepository {

    private val playerDatabase: PlayerDatabase? by lazy {
        PlayerDatabase.getInstance(context)
    }

    private val playerDao: PlayerDao? by lazy {
        playerDatabase?.playerDao()
    }

    override fun getDatabase(result: (playerData: List<Player>) -> Unit) {
        GlobalScope.launch {
            val playerData = playerDao?.getAllPlayer().orEmpty()
            MainScope().launch {
                result.invoke(playerData)
            }
        }
    }

    override fun insertToDatabase(newPlayer: Player, onSaved: () -> Unit) {
        GlobalScope.launch {
            val addPlayer = playerDao?.addPlayer(newPlayer)
            MainScope().launch {
                onSaved.invoke()
            }
        }
    }

    override fun getDatabaseByName(playerName: String, result: (player: List<Player>)->Unit) {
        GlobalScope.launch {
            val playerData = playerDao?.getPlayerByName(playerName).orEmpty()
            MainScope().launch {
                if (playerData != null) {
                    result.invoke(playerData)
                }
            }
        }
    }

    override fun updateDatabase(player: Player, onSaved: () -> Unit) {
        GlobalScope.launch {
            val updatePlayer = playerDao?.updatePlayer(player)
            MainScope().launch {
                onSaved.invoke()
            }
        }
    }
}