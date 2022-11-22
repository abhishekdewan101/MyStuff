package com.adewan.mystuff.feature.explore

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val exploreModules = module {
    viewModel { GameExploreViewModel(get()) }
}
