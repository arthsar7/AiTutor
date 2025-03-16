package com.example.aitutor.screens.onboarding.privatetutor

import com.example.aitutor.base.BaseViewModel

class PrivateTutorViewModel: BaseViewModel<PrivateTutorState, PrivateTutorEvent, PrivateTutorEffect>(
    initialState = PrivateTutorState(),
    reducer = PrivateTutorReducer()
)