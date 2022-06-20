package com.example.rockpaperscissors.presenter

import com.example.rockpaperscissors.model.Player
import com.example.rockpaperscissors.repository.StorageRepository
import com.example.rockpaperscissors.repository.StorageRepositoryImpl
import com.example.rockpaperscissors.view.UpdateView

class UpdatePresenterImpl(private val view: UpdateView) : UpdatePresenter {
    private val storageRepository: StorageRepository by lazy {
        StorageRepositoryImpl(view.context())
    }

    override fun updateDatabase(player: Player) {
        storageRepository.updateDatabase(player) {
            view.onUpdateDatabase()
        }
    }
}