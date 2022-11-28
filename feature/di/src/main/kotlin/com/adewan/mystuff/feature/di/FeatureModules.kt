package com.adewan.mystuff.feature.di

import com.adewan.mystuff.feature.expanded.expandedModule
import com.adewan.mystuff.feature.explore.exploreModules
import com.adewan.mystuff.feature.search.searchModule
import com.adewan.mystuff.features.landing.landingScreenModule

val featureModules = listOf(landingScreenModule, exploreModules, searchModule, expandedModule)
