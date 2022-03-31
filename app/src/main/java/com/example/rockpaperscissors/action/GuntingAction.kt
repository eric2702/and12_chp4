package com.example.rockpaperscissors.action

import com.example.rockpaperscissors.ActionSuit
import com.example.rockpaperscissors.DataSources
import com.example.rockpaperscissors.model.ItemSuit

class GuntingAction : ActionSuit(DataSources.guntingData) {
    override fun menang(): ItemSuit {
        val menang = DataSources.guntingData.menang
        return DataSources.convertStringToData(menang)
    }

    override fun kalah(): ItemSuit {
        val kalah = DataSources.guntingData.kalah
        return DataSources.convertStringToData(kalah)
    }
}