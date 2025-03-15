package com.example.aitutor.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aitutor.R

@Immutable
object AppThemeImpl: AppTheme {

    private val fontFamily = FontFamily(
        Font(R.font.sf_pro_regular, FontWeight.Normal),
        Font(R.font.sf_pro_medium, FontWeight.Medium),
        Font(R.font.sf_pro_semibold, FontWeight.SemiBold),
        Font(R.font.sf_pro_bold, FontWeight.Bold)
    )

    @Stable
    override val colors = AppColors(
        primary = Blue,
        secondary = Green,
        background = White
    )

    @Stable
    override val shapes = AppShapes(
        small = RoundedCornerShape(16.dp),
        medium = RoundedCornerShape(24.dp),
    )

    @Stable
    override val typography = AppTypography(
        bodyLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        textSplash = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            color = White
        ),
    )
}