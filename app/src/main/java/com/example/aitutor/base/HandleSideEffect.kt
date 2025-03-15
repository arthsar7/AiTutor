package com.example.aitutor.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.aitutor.base.reducer.UiEffect

@Composable
fun <T : UiEffect> HandleSideEffect(sideEffect: T?, handleEffect: (T) -> Unit) {
    LaunchedEffect(sideEffect) {
        sideEffect?.let { handleEffect(it) }
    }
}