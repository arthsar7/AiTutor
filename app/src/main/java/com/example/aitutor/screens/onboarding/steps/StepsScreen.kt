package com.example.aitutor.screens.onboarding.steps

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.withStyle
import com.example.aitutor.R
import com.example.aitutor.base.HandleSideEffect
import com.example.aitutor.ui.theme.AppGradientButton
import com.example.aitutor.ui.theme.Theme
import com.example.aitutor.ui.theme.dep
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun StepsScreen(
    state: StepsState,
    sideEffect: StepsEffect?,
    sendEvent: (StepsEvent) -> Unit,
    onBack: () -> Unit
) {
    val pager = rememberPagerState { state.onboardingSteps.size }

    LaunchedEffect(pager.currentPage) {
        sendEvent(StepsEvent.StepChanged(pager.currentPage))
    }

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
                stepsSize = state.onboardingSteps.size,
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
                    text = state.currentOnboardingStep.title,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = Theme.typography.title
                )
                StepContent(
                    state = state,
                    onEvent = sendEvent
                )
            }
        }
        if (!state.isLastStep) {
            AppGradientButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 20.dep)
                    .padding(bottom = 36.dep),
                text = stringResource(id = R.string.steps_continue),
                onClick = {
                    sendEvent(StepsEvent.ContinueClicked)
                },
                enabled = state.isContinueAvailable,
                inlineEndContent = {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_right),
                        contentDescription = null,
                        tint = Theme.colors.background,
                        modifier = Modifier.size(20.dep)
                    )
                }
            )
        }
    }
}

@Composable
private fun StepsIndicator(
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
                isSelected = i <= currentPage
            )
        }
    }
}

@Composable
private fun StepIndicator(
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

@Composable
private fun StepContent(
    state: StepsState,
    onEvent: (StepsEvent) -> Unit
) {
    when (state.currentOnboardingStep) {
        OnboardingStep.NativeLanguage -> {
            NativeLanguageStep(
                modifier = Modifier.padding(horizontal = 20.dep),
                nativeLanguages = state.nativeLanguages,
                selectedLanguage = state.selectedNativeLanguage,
                onSelectLanguage = { onEvent(StepsEvent.SelectNativeLanguage(it)) }
            )
        }
        OnboardingStep.EnglishLevel -> {
            EnglishLevelStep(
                modifier = Modifier.padding(horizontal = 20.dep),
                englishLevels = state.englishLevels,
                selectedLevel = state.selectedEnglishLevel,
                onSelectLevel = { onEvent(StepsEvent.SelectEnglishLevel(it)) }
            )
        }
        OnboardingStep.StudyReasons -> {
            StudyReasonsStep(
                modifier = Modifier.padding(horizontal = 20.dep),
                studyReasons = state.studyReasons,
                selectedReasons = state.selectedStudyReasons,
                onSelectReasons = { onEvent(StepsEvent.SelectStudyReason(it)) }
            )
        }
        OnboardingStep.YourName -> {
            YourNameStep(
                modifier = Modifier.padding(horizontal = 20.dep),
                name = state.yourName,
                onNameChanged = { onEvent(StepsEvent.SetYourName(it)) }
            )
        }
        OnboardingStep.InterestingTopics -> {}
        OnboardingStep.PracticeTime -> {}
    }
}

@Composable
private fun YourNameStep(
    modifier: Modifier = Modifier,
    name: String,
    onNameChanged: (String) -> Unit
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(20.dep))
        Text(
            text = stringResource(id = R.string.steps_your_name),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = Theme.typography.title
        )
        Spacer(modifier = Modifier.height(20.dep))
        TextField(
            value = name,
            onValueChange = onNameChanged,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.steps_your_name_placeholder),
                    style = Theme.typography.body
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Theme.colors.background,
                focusedIndicatorColor = Theme.colors.secondary,
                unfocusedIndicatorColor = Theme.colors.secondaryLight
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
            ),
        )
        Spacer(modifier = Modifier.height(20.dep))
    }
}

@Composable
private fun NativeLanguageStep(
    modifier: Modifier = Modifier,
    nativeLanguages: ImmutableList<NativeLanguage>,
    selectedLanguage: NativeLanguage,
    onSelectLanguage: (NativeLanguage) -> Unit
) {
    LazyColumn(modifier) {
        item {
            Spacer(Modifier.height(20.dep))
        }
        items(nativeLanguages) { nativeLanguage ->
            NativeLanguageItem(
                modifier = Modifier.fillMaxWidth(),
                nativeLanguage = nativeLanguage,
                isSelected = nativeLanguage == selectedLanguage,
                onSelectLanguage = onSelectLanguage
            )
            Spacer(Modifier.height(10.dep))
        }
        item {
            Spacer(Modifier.height(10.dep))
        }
    }
}

@Composable
private fun StudyReasonsStep(
    modifier: Modifier = Modifier,
    studyReasons: ImmutableList<StudyReason>,
    selectedReasons: ImmutableList<StudyReason>,
    onSelectReasons: (StudyReason) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(10.dep),
        horizontalArrangement = Arrangement.spacedBy(10.dep),
        contentPadding = PaddingValues(10.dep)
    ) {
        items(studyReasons) { studyReason ->
            StudyReasonItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(124.dep),
                studyReason = studyReason,
                isSelected = studyReason in selectedReasons,
                onSelectReason = onSelectReasons
            )
        }
    }
}

@Composable
private fun StudyReasonItem(
    modifier: Modifier = Modifier,
    studyReason: StudyReason,
    isSelected: Boolean,
    onSelectReason: (StudyReason) -> Unit
) {
    OutlinedCard(
        modifier = modifier,
        onClick = { onSelectReason(studyReason) },
        shape = Theme.shapes.small,
        colors = Theme.colors.backgroundElevatedCardColors,
        border = if (isSelected) {
            BorderStroke(3.dep, Theme.colors.primary)
        } else {
            BorderStroke(0.dep, Color.Unspecified)
        },
        elevation = Theme.elevations.card
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dep),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = studyReason.icon,
                    contentDescription = null,
                    modifier = Modifier.size(40.dep),
                    tint = if (isSelected) {
                        Theme.colors.primary
                    } else {
                        Theme.colors.onBackground
                    }
                )
                Spacer(Modifier.height(10.dep))
                Text(
                    text = studyReason.title,
                    style = Theme.typography.bodyBold,
                )
            }
            if (isSelected) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dep)
                        .size(24.dep)
                )
            }
        }
    }
}


@Composable
private fun EnglishLevelStep(
    modifier: Modifier = Modifier,
    englishLevels: ImmutableList<EnglishLevel>,
    selectedLevel: EnglishLevel,
    onSelectLevel: (EnglishLevel) -> Unit
) {
    LazyColumn(modifier) {
        item {
            Spacer(Modifier.height(20.dep))
        }
        items(englishLevels) { englishLevel ->
            EnglishLevelItem(
                modifier = Modifier.fillMaxWidth(),
                englishLevel = englishLevel,
                isSelected = englishLevel == selectedLevel,
                onSelectLevel = onSelectLevel
            )
            Spacer(Modifier.height(10.dep))
        }
        item {
            Spacer(Modifier.height(10.dep))
        }
    }
}

@Composable
private fun EnglishLevelItem(
    modifier: Modifier = Modifier,
    englishLevel: EnglishLevel,
    isSelected: Boolean,
    onSelectLevel: (EnglishLevel) -> Unit
) {
    OutlinedCard(
        modifier = modifier,
        onClick = { onSelectLevel(englishLevel) },
        shape = Theme.shapes.small,
        colors = Theme.colors.backgroundElevatedCardColors,
        elevation = Theme.elevations.card,
        border = if (isSelected) {
            BorderStroke(3.dep, Theme.colors.primary)
        } else {
            BorderStroke(0.dep, Color.Unspecified)
        },
    ) {
        Row(
            modifier = Modifier.padding(17.dep),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column {
                Text(
                    text = englishLevel.title,
                    style = Theme.typography.bodyBold
                )
                Spacer(Modifier.height(4.dep))
                Text(
                    text = englishLevel.description,
                    style = Theme.typography.body.copy(color = Theme.colors.onBackgroundLight)
                )
            }
            if (isSelected) {
                Spacer(Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(24.dep)
                )
            }
        }
    }
}

@Composable
private fun NativeLanguageItem(
    modifier: Modifier = Modifier,
    nativeLanguage: NativeLanguage,
    isSelected: Boolean,
    onSelectLanguage: (NativeLanguage) -> Unit
) {
    val bodyBoldSpan = Theme.typography.bodyBold.toSpanStyle()
    val bodySpan = Theme.typography.body.copy(color = Theme.colors.onBackgroundLight).toSpanStyle()
    val annotatedText = remember(nativeLanguage) {
        buildAnnotatedString {
            withStyle(bodyBoldSpan) {
                append(nativeLanguage.title)
            }
            if (nativeLanguage.nativeTitle.isNotEmpty()) {
                withStyle(bodySpan) {
                    append(" / ${nativeLanguage.nativeTitle}")
                }
            }
        }
    }
    OutlinedCard(
        modifier = modifier,
        onClick = { onSelectLanguage(nativeLanguage) },
        shape = Theme.shapes.small,
        colors = Theme.colors.backgroundElevatedCardColors,
        elevation = Theme.elevations.card,
        border = if (isSelected) {
            BorderStroke(3.dep, Theme.colors.primary)
        } else {
            BorderStroke(0.dep, Color.Unspecified)
        },
    ) {
        Row(
            modifier = Modifier.padding(17.dep),
        ){
            Text(
                text = annotatedText,
            )
            if (isSelected) {
                Spacer(Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(24.dep)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}