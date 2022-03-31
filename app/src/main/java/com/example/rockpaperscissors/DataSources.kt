package com.example.rockpaperscissors

import com.example.rockpaperscissors.model.ItemSuit

object DataSources {
    val guntingData = ItemSuit(
        name = "gunting",
        kalah = "batu",
        menang = "kertas"
    )

    val batuData = ItemSuit(
        name = "batu",
        kalah = "kertas",
        menang = "gunting"
    )

    val kertasData = ItemSuit(
        name = "kertas",
        kalah = "gunting",
        menang = "batu"
    )


    fun convertStringToData(name: String): ItemSuit {
        val data = when (name) {
            "gunting" -> guntingData
            "batu" -> batuData
            else -> kertasData
        }

        return data
    }

    fun getRandomSuit(): ItemSuit {
        val dataList = listOf(guntingData, batuData, kertasData)
        return dataList.random()
    }

}