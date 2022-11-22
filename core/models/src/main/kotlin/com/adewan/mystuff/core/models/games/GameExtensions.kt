package com.adewan.mystuff.core.models.games

import proto.Game

fun Game.posterUrl(): String =
    "https://images.igdb.com/igdb/image/upload/t_720p/${cover.imageId}.jpg"
