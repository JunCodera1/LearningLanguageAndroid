package com.example.learninglanguage.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background

fun Modifier.gradientBackground(topColor: Color, bottomColor: Color): Modifier {
    return this.background(
        brush = Brush.verticalGradient(
            colors = listOf(topColor, bottomColor)
        )
    )
}