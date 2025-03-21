package com.example.aitutor.screens.onboarding.steps

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.aitutor.R

enum class StudyReason(
    val title: String,
    private val iconRes: Int
) {
    Travel(
        title = "Travel/Live aboard",
        iconRes = R.drawable.ic_travel
    ),
    Career(
        title = "Career growth",
        iconRes = R.drawable.ic_career
    ),
    Education(
        title = "Learn new words",
        iconRes = R.drawable.ic_learn
    ),
    SelfImprovement(
        title = "Self improvement",
        iconRes = R.drawable.ic_self_improvement
    ),
    Relationships(
        title = "Relationships",
        iconRes = R.drawable.ic_relations
    ),
    MoviesBooks(
        title = "Movies & read books",
        iconRes = R.drawable.ic_tv
    ),
    SpeakFluent(
        title = "Speak more fluently",
        iconRes = R.drawable.ic_award
    ),
    Grammar(
        title = "Use correct grammar",
        iconRes = R.drawable.ic_grammar
    ),
    Other(
        title = "Other",
        iconRes = R.drawable.ic_puzzle
    );

    val icon: Painter
        @Composable
        get() = painterResource(iconRes)
}