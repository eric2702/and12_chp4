package com.example.rockpaperscissors.view

import android.content.Context
import com.example.rockpaperscissors.model.Player

interface UpdateView {
    fun context(): Context
    fun onUpdateDatabase()
}