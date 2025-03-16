package com.example.aitutor.screens.onboarding.steps

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.aitutor.R
import com.example.aitutor.base.HandleSideEffect
import com.example.aitutor.ui.theme.AppGradientButton
import com.example.aitutor.ui.theme.Theme
import com.example.aitutor.ui.theme.dep

@Composable
fun StepsScreen(
    state: StepsState,
    sideEffect: StepsEffect?,
    sendEvent: (StepsEvent) -> Unit,
    onBack: () -> Unit
) {
    val pager = rememberPagerState { state.steps.size }

    HandleSideEffect(sideEffect) { effect ->
        when (effect) {
            is StepsEffect.NavigateToStep -> pager.animateScrollToPage(effect.step)
            StepsEffect.Back -> onBack()
        }
    }

    BackHandler { sendEvent(StepsEvent.BackClicked) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background)
            .systemBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .padding(top = 10.dep)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
        ) {
            IconButton(
                onClick = {
                    if (!state.isFirstStep) {
                        sendEvent(StepsEvent.BackClicked)
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 20.dep)
                    .size(45.dep)
                    .alpha(if (state.isFirstStep) 0f else 1f)
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_back),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(40.dep)
                )
            }
            StepsIndicator(
                stepsSize = state.steps.size,
                currentPage = state.currentStepIndex,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.size(20.dep))
        HorizontalPager(
            state = pager,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = state.currentStep.title,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = Theme.typography.title
                )

            }
        }
        if (!state.isLastStep) {
            AppGradientButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 20.dep),
                text = stringResource(id = R.string.steps_continue),
                onClick = {
                    sendEvent(StepsEvent.ContinueClicked)
                }
            )
        }
    }
}

@Composable
fun StepsIndicator(
    modifier: Modifier = Modifier,
    stepsSize: Int,
    currentPage: Int,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until stepsSize) {
            StepIndicator(
                modifier = Modifier.padding(horizontal = 2.dep),
                isSelected = i == currentPage
            )
        }
    }
}

@Composable
fun StepIndicator(
    modifier: Modifier = Modifier,
    isSelected: Boolean
) {
    Box(
        modifier = modifier
            .size(30.dep, 6.dep)
            .background(
                color = if (isSelected) {
                    Theme.colors.secondary
                } else {
                    Theme.colors.secondaryLight
                },
                shape = Theme.shapes.circle
            )
    )
}
