package com.adewan.mystuff.core.utils

import android.net.Uri

interface UriDecoder {
    fun decodeUriString(encodedString: String): String
}

class UriDecoderImpl : UriDecoder {
    override fun decodeUriString(encodedString: String): String = Uri.decode(encodedString)
}
