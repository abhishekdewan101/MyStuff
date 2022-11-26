package com.adewan.mystuff.core.data.repositories

import com.adewan.mystuff.core.models.games.buildSearchQuery
import com.adewan.mystuff.core.models.games.posterUrl
import com.adewan.mystuff.datastore.LocalDataStore
import com.adewan.mystuff.network.NetworkDataSource
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

data class SearchResult(
    val name: String,
    val poster: String,
    val releasing: String,
    val rating: String,
    val id: String
)

interface SearchRepository {
    suspend fun searchSources(searchTerm: String): List<SearchResult>
}

class SearchRepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    dataStore: LocalDataStore
) : SearchRepository {
    private val token: String by lazy {
        val localAuthentication = dataStore.getLocalAuthentication() ?: throw IllegalStateException(
            "Local authentication shouldn't be null when accessing GameRepository"
        )
        localAuthentication.accessToken
    }

    override suspend fun searchSources(searchTerm: String): List<SearchResult> {
        val searchResults =
            networkDataSource.searchForGame(
                token = token,
                query = buildSearchQuery(searchTerm = searchTerm).buildQuery()
            )
        return searchResults.gamesList.sortedByDescending { it.firstReleaseDate.seconds }.map {
            val releasingInstant = Instant
                .ofEpochSecond(it.firstReleaseDate.seconds)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            val formattedReleasedDate =
                DateTimeFormatter.ofPattern("MM/dd/yyyy").format(releasingInstant)

            val ratingString = if (it.totalRating <= 0) {
                "Rating - N.A"
            } else {
                "Rating - ${it.totalRating.roundToInt()} of 100"
            }
            SearchResult(
                name = it.name,
                poster = it.posterUrl(),
                releasing = "Releasing - $formattedReleasedDate ",
                rating = ratingString,
                id = it.slug
            )
        }
    }
}
