package com.example.aitutor.screens

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.aitutor.base.mviComposable
import com.example.aitutor.screens.onboarding.privatetutor.PrivateTutorEffect
import com.example.aitutor.screens.onboarding.privatetutor.PrivateTutorEvent
import com.example.aitutor.screens.onboarding.privatetutor.PrivateTutorScreen
import com.example.aitutor.screens.onboarding.privatetutor.PrivateTutorState
import com.example.aitutor.screens.onboarding.privatetutor.PrivateTutorViewModel
import com.example.aitutor.screens.onboarding.steps.StepsEffect
import com.example.aitutor.screens.onboarding.steps.StepsEvent
import com.example.aitutor.screens.onboarding.steps.StepsScreen
import com.example.aitutor.screens.onboarding.steps.StepsState
import com.example.aitutor.screens.onboarding.steps.StepsViewModel
import com.example.aitutor.screens.splash.SplashEffect
import com.example.aitutor.screens.splash.SplashEvent
import com.example.aitutor.screens.splash.SplashScreen
import com.example.aitutor.screens.splash.SplashState
import com.example.aitutor.screens.splash.SplashViewModel

@Composable
fun MainNavigation(navController: NavHostController = rememberNavController()) {
    var showDialog by remember { mutableStateOf(false) }
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        mviComposable<SplashState, SplashEffect, SplashEvent, SplashViewModel>(
            screen = Screen.Splash
        ) { state, sideEffect, sendEvent ->
            SplashScreen(
                state = state,
                sideEffect = sideEffect,
                onNavigateToHome = {
                    navController.navigate(Screen.OnboardingPrivateTutor.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                },
                sendEvent = sendEvent,
                onShowError = { showDialog = true }
            )
        }

        mviComposable<PrivateTutorState, PrivateTutorEffect, PrivateTutorEvent, PrivateTutorViewModel>(
            screen = Screen.OnboardingPrivateTutor
        ) { state, sideEffect, sendEvent ->
            PrivateTutorScreen(
                state = state,
                sideEffect = sideEffect,
                sendEvent = sendEvent,
                onNavigateToStart = { navController.navigate(Screen.Steps.route) }
            )
        }
        mviComposable<StepsState, StepsEffect, StepsEvent, StepsViewModel>(
            screen = Screen.Steps
        ) { state, sideEffect, sendEvent ->
            StepsScreen(
                state = state,
                sideEffect = sideEffect,
                sendEvent = sendEvent,
                onBack = navController::popBackStack
            )
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Error") },
            text = { Text("Please restart the app") },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text(
                        text = "Confirm",
                        color = Color.White
                    )
                }
            }
        )
    }
}