package com.example.rockpaperscissors.presenter

import com.example.rockpaperscissors.repository.StorageRepository
import com.example.rockpaperscissors.repository.StorageRepositoryImpl
import com.example.rockpaperscissors.view.CheckNameView

class CheckNamePresenterImpl(private val view: CheckNameView): CheckNamePresenter {
    private val storageRepository: StorageRepository by lazy {
        StorageRepositoryImpl(view.context())
    }
    override fun getPlayerByName(playerName: String) {
        storageRepository.getDatabaseByName(playerName) {
            view.onCheckDatabase(it)
        }
    }
}