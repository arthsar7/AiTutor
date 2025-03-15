package com.example.aitutor.screens

import com.example.aitutor.base.BaseViewModel
import com.example.aitutor.base.reducer.UiEffect
import com.example.aitutor.base.reducer.UiEvent
import com.example.aitutor.base.reducer.UiState
import com.example.aitutor.screens.splash.SplashEffect
import com.example.aitutor.screens.splash.SplashEvent
import com.example.aitutor.screens.splash.SplashState
import com.example.aitutor.screens.splash.SplashViewModel

sealed class Screen<S : UiState, E : UiEvent, F : UiEffect, VM : BaseViewModel<S, E, F>>(val route: String) {
    data object Splash : Screen<SplashState, SplashEvent, SplashEffect, SplashViewModel>("splash")
}