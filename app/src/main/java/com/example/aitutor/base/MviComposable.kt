package com.example.aitutor.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.aitutor.base.reducer.UiEffect
import com.example.aitutor.base.reducer.UiEvent
import com.example.aitutor.base.reducer.UiState
import com.example.aitutor.screens.Screen
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.compose.koinViewModel

inline fun <S : UiState, F : UiEffect, E : UiEvent, reified VM : BaseViewModel<S, E, F>> NavGraphBuilder.mviComposable(
    screen: Screen,
    crossinline getViewModel: @Composable () -> VM = { koinViewModel() },
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    crossinline content: @Composable (state: S, sideEffect: F?, sendEvent: (E) -> Unit) -> Unit
) {
    composable(screen.route, arguments, deepLinks) {
        val viewModel = getViewModel.invoke()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val effect by viewModel.effect.collectAsStateWithLifecycle(
            initialValue = null, context = Dispatchers.Main.immediate
        )
        content(state, effect, viewModel::sendEvent)
    }
}