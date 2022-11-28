package com.adewan.mystuff.feature.expanded

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val expandedModule = module {
    viewModel { ExpandedViewModel(get()) }
}
