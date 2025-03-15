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
import com.example.aitutor.base.baseComposable
import com.example.aitutor.screens.splash.SplashEffect
import com.example.aitutor.screens.splash.SplashEvent
import com.example.aitutor.screens.splash.SplashScreen
import com.example.aitutor.screens.splash.SplashState
import com.example.aitutor.screens.splash.SplashViewModel

@Composable
fun MainNavigation(navController: NavHostController = rememberNavController()) {
    var showDialog by remember { mutableStateOf(false) }
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        baseComposable<SplashState, SplashEffect, SplashEvent, SplashViewModel>(Screen.Splash) { state, sideEffect, sendEvent ->
            SplashScreen(
                state = state,
                sideEffect = sideEffect,
                onNavigateToHome = {
//                    navController.navigate(Screen.OnboardingPrivateTutor.route) {
//                        popUpTo(Screen.Splash.route) {
//                            inclusive = true
//                        }
//                    }
                },
                sendEvent = sendEvent,
                onShowError = { showDialog = true }
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