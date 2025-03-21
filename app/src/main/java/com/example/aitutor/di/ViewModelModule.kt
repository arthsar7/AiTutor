package com.example.aitutor.di

import com.example.aitutor.screens.onboarding.privatetutor.PrivateTutorViewModel
import com.example.aitutor.screens.onboarding.steps.StepsViewModel
import com.example.aitutor.screens.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::SplashViewModel)
    viewModelOf(::StepsViewModel)
    viewModelOf(::PrivateTutorViewModel)
}
