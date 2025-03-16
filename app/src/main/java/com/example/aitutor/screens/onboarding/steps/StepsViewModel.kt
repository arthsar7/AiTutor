 package com.example.aitutor.screens.onboarding.steps 
import com.example.aitutor.base.BaseViewModel

 class StepsViewModel : BaseViewModel<StepsState, StepsEvent, StepsEffect>(
    initialState = StepsState(),
    reducer = StepsReducer()
)