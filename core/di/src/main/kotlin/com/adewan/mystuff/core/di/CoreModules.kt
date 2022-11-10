package com.adewan.mystuff.core.di

import com.adewan.mystuff.core.data.di.dataModule
import com.adewan.mystuff.di.dataStoreModule
import com.adewan.mystuff.di.networkModule

val coreModules = listOf(dataStoreModule, networkModule, dataModule)
