package com.example.rockpaperscissors.presenter

import com.example.rockpaperscissors.model.Player
import com.example.rockpaperscissors.repository.StorageRepository
import com.example.rockpaperscissors.repository.StorageRepositoryImpl
import com.example.rockpaperscissors.view.InsertView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InsertPresenterImpl(private val view: InsertView) : InsertPresenter {
    private val storageRepository: StorageRepository by lazy {
        StorageRepositoryImpl(view.context())
    }

    override fun saveToDatabase(newPlayer: Player) {
        GlobalScope.launch {
            storageRepository.insertToDatabase(newPlayer) {
                view.onSaveDatabase()
            }
        }
    }
}