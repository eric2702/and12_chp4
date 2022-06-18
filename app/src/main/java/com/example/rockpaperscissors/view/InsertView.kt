package com.example.rockpaperscissors.view

import android.content.Context

interface InsertView {
    fun context(): Context
    fun onSaveDatabase()
}