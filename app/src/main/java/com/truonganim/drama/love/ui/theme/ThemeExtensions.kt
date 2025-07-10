package com.truonganim.drama.love.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Extension functions for easy access to DramaLove theme colors and gradients
 */

// Gradient brushes for common UI patterns
object DramaLoveGradients {
    
    @Composable
    fun splashGradient(): Brush = Brush.verticalGradient(
        colors = listOf(
            DramaLoveColors.SplashGradientStart,
            DramaLoveColors.SplashGradientMiddle,
            DramaLoveColors.SplashGradientEnd
        )
    )
    
    @Composable
    fun primaryGradient(): Brush = Brush.horizontalGradient(
        colors = listOf(
            DramaLoveColors.Purple700,
            DramaLoveColors.Purple500
        )
    )
    
    @Composable
    fun secondaryGradient(): Brush = Brush.horizontalGradient(
        colors = listOf(
            DramaLoveColors.Pink700,
            DramaLoveColors.Pink500
        )
    )
    
    @Composable
    fun cardGradient(): Brush = Brush.verticalGradient(
        colors = listOf(
            DramaLoveColors.Purple600,
            DramaLoveColors.Purple800
        )
    )
    
    @Composable
    fun shimmerGradient(): Brush = Brush.horizontalGradient(
        colors = listOf(
            DramaLoveColors.Grey300.copy(alpha = 0.3f),
            DramaLoveColors.Grey200.copy(alpha = 0.5f),
            DramaLoveColors.Grey300.copy(alpha = 0.3f)
        )
    )
}

// Extension properties for ColorScheme
val ColorScheme.success: Color
    get() = DramaLoveColors.Success

val ColorScheme.warning: Color
    get() = DramaLoveColors.Warning

val ColorScheme.info: Color
    get() = DramaLoveColors.Info

// Video player specific colors
val ColorScheme.videoPlayerBackground: Color
    get() = DramaLoveColors.Black

val ColorScheme.videoPlayerControls: Color
    get() = DramaLoveColors.White.copy(alpha = 0.8f)

val ColorScheme.videoPlayerProgress: Color
    get() = DramaLoveColors.Purple500

// Social interaction colors
val ColorScheme.likeColor: Color
    get() = DramaLoveColors.Pink500

val ColorScheme.shareColor: Color
    get() = DramaLoveColors.Blue500

val ColorScheme.favoriteColor: Color
    get() = DramaLoveColors.Orange500

// Status colors
val ColorScheme.onlineStatus: Color
    get() = DramaLoveColors.Success

val ColorScheme.offlineStatus: Color
    get() = DramaLoveColors.Grey500

val ColorScheme.premiumColor: Color
    get() = DramaLoveColors.Orange500

// Text colors with semantic meaning
val ColorScheme.textPrimary: Color
    get() = onSurface

val ColorScheme.textSecondary: Color
    get() = onSurfaceVariant

val ColorScheme.textDisabled: Color
    get() = onSurface.copy(alpha = 0.38f)

val ColorScheme.textHint: Color
    get() = onSurface.copy(alpha = 0.6f)

// Border and divider colors
val ColorScheme.divider: Color
    get() = outline.copy(alpha = 0.12f)

val ColorScheme.border: Color
    get() = outline.copy(alpha = 0.2f)

val ColorScheme.borderFocused: Color
    get() = primary

// Shadow colors
val ColorScheme.shadowLight: Color
    get() = DramaLoveColors.Black.copy(alpha = 0.1f)

val ColorScheme.shadowMedium: Color
    get() = DramaLoveColors.Black.copy(alpha = 0.2f)

val ColorScheme.shadowDark: Color
    get() = DramaLoveColors.Black.copy(alpha = 0.3f)
