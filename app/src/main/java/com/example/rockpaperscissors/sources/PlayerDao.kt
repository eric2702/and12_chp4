package com.example.rockpaperscissors.sources

import com.example.rockpaperscissors.model.Player
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface PlayerDao {
    @Query("SELECT * FROM player")
    fun getAllPlayer(): List<Player>

    @Query("SELECT * FROM player WHERE id=:id")
    fun getDetailPlayer(id: Int): Player?

    @Query("SELECT * FROM player WHERE name=:name")
    fun getPlayerByName(name: String): Player?

    @Insert(onConflict = REPLACE)
    fun addPlayer(player: Player): Long

    @Update
    fun updatePlayer(player: Player): Int

}