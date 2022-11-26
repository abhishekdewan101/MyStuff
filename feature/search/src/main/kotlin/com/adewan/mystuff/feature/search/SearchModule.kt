package com.adewan.mystuff.feature.search

import com.adewan.mystuff.core.data.repositories.SearchRepository
import com.adewan.mystuff.core.data.repositories.SearchRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    factory<SearchRepository> { SearchRepositoryImpl(get(), get()) }
    viewModel { SearchViewModel(get()) }
}
