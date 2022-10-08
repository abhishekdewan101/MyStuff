package com.adewan.mystuff.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.mystuff.core.usecase.GetShowcaseGames
import com.adewan.mystuff.ui.composables.ImageShowcaseItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getShowcaseGames: GetShowcaseGames) :
    ViewModel() {
    private val _showcaseGames = MutableStateFlow<List<ImageShowcaseItem>?>(null)
    val showcaseGames = _showcaseGames.asStateFlow()

    init {
        viewModelScope.launch {
            _showcaseGames.value =
                getShowcaseGames().gamesList.filter { it.hasCover() && it.name.isNotEmpty() }
                    .map {
                        ImageShowcaseItem(
                            url = "https://images.igdb.com/igdb/image/upload/t_720p/${it.cover.imageId}.jpg",
                            label = it.name
                        )
                    }
        }
    }
}
