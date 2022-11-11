package com.adewan.mystuff.core.models.games

import proto.Game

data class GameList(val title: String, val query: String, val games: List<Game>)
