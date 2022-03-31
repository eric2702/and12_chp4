package com.example.rockpaperscissors

import com.example.rockpaperscissors.model.ItemSuit

abstract class ActionSuit(val itemSuit: ItemSuit) : Suit {

    abstract fun menang(): ItemSuit // kalau ini batu, maka function ini adalah gunting
    abstract fun kalah(): ItemSuit

    override fun action(suitName: String): String {
        val suitDataCom = DataSources.convertStringToData(suitName)

        val isMenang = menang() == suitDataCom
        val isKalah = kalah() == suitDataCom
        val isDraw = itemSuit == suitDataCom

        return when {
            isMenang -> "win"
            isKalah -> "lose"
            else -> "draw"
        }
    }
}