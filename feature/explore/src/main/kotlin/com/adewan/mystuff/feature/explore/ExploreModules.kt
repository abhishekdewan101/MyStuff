package com.adewan.mystuff.feature.explore

import com.adewan.mystyuff.feature.games.GameExploreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val exploreModules = module {
    viewModel { GameExploreViewModel(get()) }
}
