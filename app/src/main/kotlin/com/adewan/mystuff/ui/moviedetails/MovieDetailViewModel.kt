package com.adewan.mystuff.ui.moviedetails

import androidx.lifecycle.ViewModel
import timber.log.Timber

class MovieDetailViewModel : ViewModel() {

    fun getMovieDetails(identifier: Int) {
        Timber.d("Getting movie details: $identifier")
    }
}
