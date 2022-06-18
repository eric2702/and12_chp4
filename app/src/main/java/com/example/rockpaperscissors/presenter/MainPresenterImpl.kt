package com.example.rockpaperscissors.presenter

import com.example.rockpaperscissors.repository.StorageRepository
import com.example.rockpaperscissors.repository.StorageRepositoryImpl
import com.example.rockpaperscissors.view.MainView

class MainPresenterImpl(private val view: MainView): MainPresenter {

    private val storageRepository: StorageRepository by lazy {
        StorageRepositoryImpl(view.context())
    }

    override fun getLeaderBoard() {
        storageRepository.getDatabase {
            view.onResultDatabase(it)
        }
    }

}