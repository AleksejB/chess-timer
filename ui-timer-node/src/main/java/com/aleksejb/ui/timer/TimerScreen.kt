package com.aleksejb.ui.timer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TimerScreen(
    viewModel: TimerViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    TimerScreenContent(state = state, eventHandler = viewModel::postEvent)
}

@Composable
private fun TimerScreenContent(
    state: TimerState,
    eventHandler: (TimerEvent) -> Unit
) {
    
}