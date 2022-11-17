package com.adewan.mystuff.features.landing

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val landingScreenModule = module {
    viewModel { LandingScreenViewModel(get()) }
}
