package com.adewan.mystuff.core.di

import android.content.Context
import com.adewan.mystuff.core.network.NetworkDataSource
import com.adewan.mystuff.core.repository.IgdbRepository
import com.adewan.mystuff.core.repository.TmdbRepository
import com.adewan.mystuff.ui.gamedetails.GameDetailViewModel
import com.adewan.mystuff.ui.home.HomeViewModel
import com.adewan.mystuff.ui.moviedetails.MovieDetailViewModel
import com.adewan.mystuff.ui.tvshowdetails.TvShowDetailViewModel
import java.time.Clock
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * AppModule defines app level declarations of dependencies that can be used everywhere in the app.
 */
val appModule = module {
    single {
        androidContext().getSharedPreferences("MY_STUFF_SHARED_PREFS", Context.MODE_PRIVATE)
    }

    single {
        Clock.systemDefaultZone()
    }

    // ViewModel
    viewModel {
        HomeViewModel(get(), get())
    }

    viewModel {
        GameDetailViewModel(get())
    }

    viewModel {
        MovieDetailViewModel(get())
    }

    viewModel {
        TvShowDetailViewModel(get())
    }

    // Repositories
    single { IgdbRepository(get(), get()) }
    single { TmdbRepository(get()) }

    // DataSources
    single { NetworkDataSource(get()) }
}
