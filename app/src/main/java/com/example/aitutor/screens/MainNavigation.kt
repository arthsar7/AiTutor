package com.example.aitutor.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.aitutor.base.baseComposable
import com.example.aitutor.screens.splash.SplashScreen

@Composable
fun MainNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        baseComposable(Screen.Splash) { state, sideEffect, sendEvent ->
            SplashScreen(state, sideEffect, sendEvent)
        }
    }
}