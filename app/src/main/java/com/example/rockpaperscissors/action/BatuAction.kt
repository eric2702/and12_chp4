package com.example.rockpaperscissors.action

import com.example.rockpaperscissors.ActionSuit
import com.example.rockpaperscissors.DataSources
import com.example.rockpaperscissors.model.ItemSuit

class BatuAction : ActionSuit(DataSources.batuData){
    override fun menang(): ItemSuit {
        val menang = DataSources.batuData.menang // gunting
        return DataSources.convertStringToData(menang) // item gunting
    }

    override fun kalah(): ItemSuit {
        val kalah = DataSources.batuData.kalah
        return DataSources.convertStringToData(kalah)
    }
}