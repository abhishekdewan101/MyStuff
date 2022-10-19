package com.adewan.mystuff.ui.utils // ktlint-disable filename

import androidx.compose.ui.Modifier

fun Modifier.conditional(
    condition: Boolean,
    conditionalModifier: Modifier.() -> Modifier
): Modifier {
    return if (condition) {
        then(conditionalModifier(Modifier))
    } else {
        this
    }
}
