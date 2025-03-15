package com.example.aitutor.ui.theme

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


private const val FULL_WIDTH_FIGMA_LAYOUT = 393f
private const val FULL_HEIGHT_FIGMA_LAYOUT = 852f
private const val RELATIVE_SIZE_FIGMA_LAYOUT =
    ((FULL_WIDTH_FIGMA_LAYOUT + FULL_HEIGHT_FIGMA_LAYOUT) / 2)

@Composable
private fun ep(
    dimension: Float,
    configuration: Configuration = LocalConfiguration.current,
): Float {
    val widthDp = configuration.screenWidthDp
    val heightDp = configuration.screenHeightDp
    val relativeScreen = (widthDp.toFloat() + heightDp.toFloat()) / 2
    val relativePercent = relativeScreen / RELATIVE_SIZE_FIGMA_LAYOUT
    return dimension * relativePercent
}

val Number.dep: Dp
    @Composable
    get() = ep(dimension = toFloat()).dp
