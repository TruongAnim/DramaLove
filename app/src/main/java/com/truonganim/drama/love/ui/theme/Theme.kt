package com.truonganim.drama.love.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = DramaLoveColors.Purple400,
    onPrimary = DramaLoveColors.White,
    primaryContainer = DramaLoveColors.Purple700,
    onPrimaryContainer = DramaLoveColors.Purple100,

    secondary = DramaLoveColors.Pink400,
    onSecondary = DramaLoveColors.White,
    secondaryContainer = DramaLoveColors.Pink700,
    onSecondaryContainer = DramaLoveColors.Pink100,

    tertiary = DramaLoveColors.Orange400,
    onTertiary = DramaLoveColors.White,
    tertiaryContainer = DramaLoveColors.Orange500,
    onTertiaryContainer = DramaLoveColors.Orange300,

    background = DramaLoveColors.Grey900,
    onBackground = DramaLoveColors.Grey100,
    surface = DramaLoveColors.Grey800,
    onSurface = DramaLoveColors.Grey100,
    surfaceVariant = DramaLoveColors.Grey700,
    onSurfaceVariant = DramaLoveColors.Grey300,

    error = DramaLoveColors.Error,
    onError = DramaLoveColors.White,
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = DramaLoveColors.Grey600,
    outlineVariant = DramaLoveColors.Grey700,
    scrim = DramaLoveColors.Black,
    inverseSurface = DramaLoveColors.Grey200,
    inverseOnSurface = DramaLoveColors.Grey800,
    inversePrimary = DramaLoveColors.Purple700,
)

private val LightColorScheme = lightColorScheme(
    primary = DramaLoveColors.Purple700,
    onPrimary = DramaLoveColors.White,
    primaryContainer = DramaLoveColors.Purple100,
    onPrimaryContainer = DramaLoveColors.Purple900,

    secondary = DramaLoveColors.Pink700,
    onSecondary = DramaLoveColors.White,
    secondaryContainer = DramaLoveColors.Pink100,
    onSecondaryContainer = DramaLoveColors.Pink900,

    tertiary = DramaLoveColors.Orange500,
    onTertiary = DramaLoveColors.White,
    tertiaryContainer = DramaLoveColors.Orange300,
    onTertiaryContainer = DramaLoveColors.Orange500,

    background = DramaLoveColors.White,
    onBackground = DramaLoveColors.Grey900,
    surface = DramaLoveColors.White,
    onSurface = DramaLoveColors.Grey900,
    surfaceVariant = DramaLoveColors.Grey100,
    onSurfaceVariant = DramaLoveColors.Grey700,

    error = DramaLoveColors.Error,
    onError = DramaLoveColors.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),

    outline = DramaLoveColors.Grey600,
    outlineVariant = DramaLoveColors.Grey300,
    scrim = DramaLoveColors.Black,
    inverseSurface = DramaLoveColors.Grey800,
    inverseOnSurface = DramaLoveColors.Grey100,
    inversePrimary = DramaLoveColors.Purple400,
)

@Composable
fun DramaLoveTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}