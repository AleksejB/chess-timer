package com.aleksejb.chesstimer.ui.timer

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TimerScreen(
//    viewModel: TimerViewModel = hiltViewModel()
) {
    Log.d("TAAAG", "TimerScreen")
//    val state by viewModel.state.collectAsState()
//    val systemUiController = rememberSystemUiController()
//    systemUiController.setStatusBarColor(Color.Transparent, darkIcons = true)

    TimerScreenContent(
//        state = state,
//        eventHandler = viewModel::postEvent
    )
}

@Composable
private fun TimerScreenContent(
//    state: TimerState,
//    eventHandler: (TimerEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(text = "WHITE", color = Color.White)
    }
}