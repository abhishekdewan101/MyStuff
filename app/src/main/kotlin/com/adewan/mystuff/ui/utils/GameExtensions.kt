package com.adewan.mystuff.ui.utils

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import proto.GameVideo

fun GameVideo.buildYoutubeScreenshotUrl(): String = "https://img.youtube.com/vi/$videoId/0.jpg"

fun GameVideo.buildYoutubeUrl(): String = "https://www.youtube.com/watch?v=$videoId"

fun GameVideo.buildYoutubeIntent(): Intent = Intent(ACTION_VIEW, Uri.parse(buildYoutubeUrl()))
