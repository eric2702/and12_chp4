package com.example.rockpaperscissors.action

import com.example.rockpaperscissors.ActionSuit
import com.example.rockpaperscissors.DataSources
import com.example.rockpaperscissors.model.ItemSuit

class KertasAction : ActionSuit(DataSources.kertasData) {
    override fun menang(): ItemSuit {
        val menang = DataSources.kertasData.menang
        return DataSources.convertStringToData(menang)
    }

    override fun kalah(): ItemSuit {
        val kalah = DataSources.kertasData.kalah
        return DataSources.convertStringToData(kalah)
    }
}