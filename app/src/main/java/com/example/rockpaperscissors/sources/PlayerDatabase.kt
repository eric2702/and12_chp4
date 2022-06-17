package com.example.rockpaperscissors.sources

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rockpaperscissors.model.Player

@Database(entities = [Player::class], version = 1)
abstract  class PlayerDatabase : RoomDatabase() {
    abstract  fun playerDao(): PlayerDao

    companion object {
        private var _instance: PlayerDatabase? = null

        fun getInstance(context: Context): PlayerDatabase? {
            if (_instance == null) {
                synchronized(PlayerDatabase::class) {
                    _instance = Room.databaseBuilder(
                        context.applicationContext,
                        PlayerDatabase::class.java,
                        "player.db"
                    ).build()
                }
            }
            return _instance
        }

        fun destroyDatabase() {
            _instance = null
        }
    }
}