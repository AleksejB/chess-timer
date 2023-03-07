package com.aleksejb.chesstimer.ui.timer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aleksejb.ui.core.components.Timer
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TimerScreen(
    viewModel: TimerViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.Transparent, darkIcons = true)

    TimerScreenContent(
        state = state,
        eventHandler = viewModel::postEvent
    )
}

@Composable
private fun TimerScreenContent(
    state: TimerState,
    eventHandler: (TimerEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Game type: Rapid 10 min",
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(80.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { eventHandler(TimerEvent.OnResetClicked) },
            text = "RESET",
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { eventHandler(TimerEvent.OnPausePlayClicked) },
            text = if (state.gameInProgress) "Pause" else "Play",
            textAlign = TextAlign.Center
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Timer(
                hours = state.whiteTime.hours,
                minutes = state.whiteTime.minutes,
                seconds = state.whiteTime.seconds,
                isWhite = true,
                isTicking = state.isWhiteTimeTicking
            ) { if (state.gameInProgress) eventHandler(TimerEvent.OnWhiteTimerClicked) }

            Timer(
                hours = state.blackTime.hours,
                minutes = state.blackTime.minutes,
                seconds = state.blackTime.seconds,
                isWhite = false,
                isTicking = state.isBlackTimeTicking
            ) { if (state.gameInProgress) eventHandler(TimerEvent.OnBlackTimerClicked) }
        }
    }
}