package com.example.aitutor.screens.onboarding.privatetutor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.aitutor.R
import com.example.aitutor.base.HandleSideEffect
import com.example.aitutor.ui.theme.AppGradientButton
import com.example.aitutor.ui.theme.Theme
import com.example.aitutor.ui.theme.dep

@Composable
fun PrivateTutorScreen(
    state: PrivateTutorState,
    sideEffect: PrivateTutorEffect?,
    sendEvent: (PrivateTutorEvent) -> Unit,
    onNavigateToStart: () -> Unit
) {
    HandleSideEffect(sideEffect) {
        when (it) {
            PrivateTutorEffect.NavigateToStart -> {
                onNavigateToStart()
            }
        }
    }

    Column(
        modifier = Modifier
            .background(Theme.colors.background)
            .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Bottom))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.private_tutor),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.private_tutor_title),
                style = Theme.typography.title,
            )
            Spacer(Modifier.height(10.dep))
            Text(
                text = stringResource(R.string.private_tutor_description),
                style = Theme.typography.body,
                modifier = Modifier.padding(horizontal = 20.dep)
            )
            Spacer(Modifier.height(27.dep))
            AppGradientButton(
                text = stringResource(R.string.private_tutor_get_started),
                onClick = { sendEvent(PrivateTutorEvent.GetStartedClicked) },
                inlineEndContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = "Arrow Right",
                        tint = Theme.colors.background
                    )
                },
                modifier = Modifier
                    .padding(bottom = 20.dep)
            )
        }
    }
}