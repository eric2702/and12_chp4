package com.example.rockpaperscissors.view

import android.content.Context

interface UpdateView {
    fun context(): Context
    fun onUpdateDatabase()
}