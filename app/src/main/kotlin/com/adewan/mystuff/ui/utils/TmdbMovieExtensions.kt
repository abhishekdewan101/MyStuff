package com.adewan.mystuff.ui.utils

import android.content.Intent
import android.net.Uri
import com.adewan.mystuff.core.model.TmdbVideo

fun TmdbVideo.buildYoutubeScreenshotUrl(): String = "https://img.youtube.com/vi/$key/0.jpg"

fun TmdbVideo.buildYoutubeUrl(): String = "https://www.youtube.com/watch?v=$key"

fun TmdbVideo.buildYoutubeIntent(): Intent =
    Intent(Intent.ACTION_VIEW, Uri.parse(buildYoutubeUrl()))
