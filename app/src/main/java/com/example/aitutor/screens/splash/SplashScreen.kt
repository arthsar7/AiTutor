package com.example.aitutor.screens.splash

import androidx.compose.runtime.Composable
import com.example.aitutor.base.HandleSideEffect

@Composable
fun SplashScreen(
    state: SplashState,
    sideEffect: SplashEffect?,
    sendEvent: (SplashEvent) -> Unit
) {
    HandleSideEffect(sideEffect) {
        when (it) {
            is SplashEffect.NavigateToHome -> {}
            is SplashEffect.Error -> {}
        }
    }
}