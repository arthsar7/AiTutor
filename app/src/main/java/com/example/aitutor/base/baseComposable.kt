package com.example.aitutor.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.aitutor.base.reducer.UiEffect
import com.example.aitutor.base.reducer.UiEvent
import com.example.aitutor.base.reducer.UiState
import com.example.aitutor.screens.Screen

inline fun <S : UiState, F : UiEffect, E : UiEvent, reified VM : BaseViewModel<S, E, F>> NavGraphBuilder.baseComposable(
    screen: Screen,
    crossinline getViewModel: @Composable () -> VM = { viewModel() },
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    crossinline content: @Composable (state: S, sideEffect: F?, sendEvent: (E) -> Unit) -> Unit
) {
    composable(screen.route, arguments, deepLinks) {
        val viewModel = getViewModel.invoke()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val effect by viewModel.effect.collectAsStateWithLifecycle(null)
        content(state, effect, viewModel::sendEvent)
    }
}