package com.myapp.screenoffapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Simple light color scheme for basic theming
private val LightColorScheme = lightColorScheme(
    // Using default Material 3 colors
)

@Composable
fun ScreenoffappTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}