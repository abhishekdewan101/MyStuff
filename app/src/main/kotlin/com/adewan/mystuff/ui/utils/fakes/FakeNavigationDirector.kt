package com.adewan.mystuff.ui.utils.fakes

import android.content.Context
import android.content.Intent
import com.adewan.mystuff.ui.navigation.NavigationDirector

class FakeNavigationDirector : NavigationDirector {
    override fun navigateToHome() {
    }

    override fun navigateToGameDetails(identifier: String) {
    }

    override fun navigateToMovieDetails(identifier: String) {
    }

    override fun navigateToExpandedImageview(url: String) {
    }

    override fun navigateToExternalIntent(intent: Intent, context: Context) {
    }
}
