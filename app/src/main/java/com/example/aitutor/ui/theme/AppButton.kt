package com.example.aitutor.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppGradientButton(
    modifier: Modifier = Modifier,
    text: String,
    gradient: Brush = Theme.colors.buttonGradientBrush,
    enabled: Boolean = true,
    textStyle: TextStyle = Theme.typography.button,
    shape: CornerBasedShape = Theme.shapes.circle,
    paddingValues: PaddingValues = PaddingValues(20.dep),
    inlineStartContent: @Composable () -> Unit = {},
    inlineEndContent: @Composable () -> Unit = {},
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Theme.colors.background
        ),
        shape = shape,
        elevation = null,
        onClick = onClick,
        contentPadding = PaddingValues(Dp.Hairline)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(
                    if (enabled) 1f else 0.5f
                )
                .background(
                    brush = gradient,
                    shape = shape
                )
        ) {
            Row(
                modifier = Modifier
                    .padding(paddingValues)
                    .align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                inlineStartContent()
                Text(
                    text = text,
                    style = textStyle,
                )
                inlineEndContent()
            }
        }
    }
}