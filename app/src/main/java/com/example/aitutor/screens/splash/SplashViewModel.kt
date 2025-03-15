package com.example.aitutor.screens.splash

import androidx.lifecycle.viewModelScope
import com.example.aitutor.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel<SplashState, SplashEvent, SplashEffect>(
    initialState = SplashState(),
    reducer = SplashReducer()
) {
    init {
        viewModelScope.launch {
            delay(3000)
            sendEvent(SplashEvent.InitSuccess)
        }
    }
}