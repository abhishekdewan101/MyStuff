package com.adewan.mystuff.core.models.games

import proto.Artwork
import proto.Game
import proto.GameVideo
import proto.Screenshot

fun Game.posterUrl(): String =
    "https://images.igdb.com/igdb/image/upload/t_720p/${cover.imageId}.jpg"

fun Screenshot.imageUrl(): String =
    "https://images.igdb.com/igdb/image/upload/t_1080p/$imageId.jpg"

fun Artwork.imageUrl(): String =
    "https://images.igdb.com/igdb/image/upload/t_1080p/$imageId.jpg"

fun GameVideo.buildYoutubeScreenshotUrl(): String = "https://img.youtube.com/vi/$videoId/0.jpg"

fun GameVideo.buildYoutubeUrl(): String = "https://www.youtube.com/watch?v=$videoId"
