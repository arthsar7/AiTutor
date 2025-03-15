package com.example.aitutor.base.reducer

data class ReducerResult<out S : UiState, out F : UiEffect>(
    val state: S,
    val effect: F? = null
)