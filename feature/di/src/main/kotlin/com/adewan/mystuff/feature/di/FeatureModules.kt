package com.adewan.mystuff.feature.di

import com.adewan.mystuff.feature.details.detailsModule
import com.adewan.mystuff.feature.expanded.expandedModule
import com.adewan.mystuff.feature.explore.exploreModules
import com.adewan.mystuff.feature.library.libraryModule
import com.adewan.mystuff.feature.search.searchModule
import com.adewan.mystuff.feature.splash.splashModule

val featureModules =
    listOf(
        exploreModules,
        searchModule,
        expandedModule,
        detailsModule,
        libraryModule,
        splashModule
    )
