package com.example.aitutor.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.aitutor.R
import com.example.aitutor.base.HandleSideEffect
import com.example.aitutor.ui.theme.Theme
import com.example.aitutor.ui.theme.dep

@Composable
fun SplashScreen(
    state: SplashState,
    sideEffect: SplashEffect?,
    sendEvent: (SplashEvent) -> Unit,
    onNavigateToHome: () -> Unit,
    onShowError: () -> Unit
) {
    HandleSideEffect(sideEffect) {
        when (it) {
            is SplashEffect.NavigateToHome -> {
                onNavigateToHome()
            }
            is SplashEffect.Error -> {
                onShowError()
            }
        }
    }
    Box(
        modifier = Modifier
            .background(Theme.colors.splashGradientBrush)
            .systemBarsPadding()
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_splash_logo),
            contentDescription = null,
            modifier = Modifier
                .size(142.dep)
                .align(Alignment.Center)
        )

        Text(
            text = stringResource(R.string.splash_loading),
            style = Theme.typography.textSplash,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 59.dep),
        )
    }
}