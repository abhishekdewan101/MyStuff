package com.adewan.mystuff.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.mystuff.core.usecase.GetShowcaseGames
import com.adewan.mystuff.core.usecase.GetTopRatedGames
import com.adewan.mystuff.ui.composables.ImageCarouselWithTitleData
import com.adewan.mystuff.ui.composables.ImageShowcaseItem
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HomeViewState(
    val showcaseGames: List<ImageShowcaseItem>,
    val topRatedGames: ImageCarouselWithTitleData
)

class HomeViewModel(
    private val getShowcaseGames: GetShowcaseGames,
    private val getTopRatedGames: GetTopRatedGames
) : ViewModel() {
    private val _viewState = MutableStateFlow<HomeViewState?>(null)
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            val data1 = async {
                getShowcaseGames().gamesList.filter { it.hasCover() && it.name.isNotEmpty() }
                    .map {
                        ImageShowcaseItem(
                            url = "https://images.igdb.com/igdb/image/upload/t_720p/${it.cover.imageId}.jpg",
                            label = it.name
                        )
                    }
            }
            val data2 = async {
                ImageCarouselWithTitleData(
                    title = "Top Rated",
                    images = getTopRatedGames().gamesList.filter { it.hasCover() }
                        .map { "https://images.igdb.com/igdb/image/upload/t_720p/${it.cover.imageId}.jpg" }
                )
            }

            _viewState.value =
                HomeViewState(showcaseGames = data1.await(), topRatedGames = data2.await())
        }
    }
}
