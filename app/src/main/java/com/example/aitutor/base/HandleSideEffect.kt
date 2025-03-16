package com.example.aitutor.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.aitutor.base.reducer.UiEffect
import kotlinx.coroutines.CoroutineScope

@Composable
fun <F : UiEffect> HandleSideEffect(
    sideEffect: F?,
    handleEffect: suspend CoroutineScope.(F) -> Unit
) = LaunchedEffect(sideEffect) {
    sideEffect?.let {
        handleEffect(it)
    }
}