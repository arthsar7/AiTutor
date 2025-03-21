package com.example.aitutor.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aitutor.R
import kotlinx.collections.immutable.persistentListOf

@Immutable
object AppThemeImpl: AppTheme {

    @Stable
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
        secondaryLight = LightGreen,
        background = White,
        onBackground = Black,
        onBackgroundLight = Gray,
        buttonGradientBrush = Brush.horizontalGradient(persistentListOf(Blue, Green)),
        splashGradientBrush = Brush.verticalGradient(persistentListOf(Blue, Green)),
        lightBlueGradientBrush = Brush.verticalGradient(persistentListOf(LightBlue, White)),
    )

    @Stable
    override val shapes = AppShapes(
        small = RoundedCornerShape(16.dp),
        medium = RoundedCornerShape(24.dp),
        circle = CircleShape
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
        button = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            color = White
        ),
        title = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 36.sp,
            lineHeight = 36.sp,
            color = Black,
            textAlign = TextAlign.Center
        ),
        body = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            color = Black,
            textAlign = TextAlign.Center
        ),
        bodyBold = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            color = Black,
            textAlign = TextAlign.Center
        )
    )

    @Stable
    override val elevations: AppElevations = AppElevations(
        medium = 4f
    )
}