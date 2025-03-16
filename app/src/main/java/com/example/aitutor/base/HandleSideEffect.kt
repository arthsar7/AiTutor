package com.example.aitutor.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.aitutor.base.reducer.UiEffect

@Composable
fun <F : UiEffect> HandleSideEffect(sideEffect: F?, handleEffect: (F) -> Unit) {
    LaunchedEffect(sideEffect) {
        sideEffect?.let(handleEffect)
    }
}