package com.adewan.mystuff.core.data.repositories

import com.adewan.mystuff.datastore.LocalDataStore

interface OnBoardingRepository {
    fun onBoardingCompleted()
    fun hasUserOnBoarded(): Boolean
}

class OnBoardRepositoryImpl(private val localDataStore: LocalDataStore) : OnBoardingRepository {
    override fun onBoardingCompleted() {
        localDataStore.onBoardingCompleted()
    }

    override fun hasUserOnBoarded(): Boolean {
        return localDataStore.getOnBoardingCompleted()
    }
}
