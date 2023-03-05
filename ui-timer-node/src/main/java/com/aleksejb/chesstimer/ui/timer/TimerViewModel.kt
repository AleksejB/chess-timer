package com.aleksejb.chesstimer.ui.timer

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.aleksejb.chesstimer.ui.timer.TimerEffect
import com.aleksejb.chesstimer.ui.timer.TimerEvent
import com.aleksejb.chesstimer.ui.timer.TimerState
import com.aleksejb.domain.core.timer.model.GameType
import com.aleksejb.domain.core.timer.model.gameformat.RapidFormat
import com.aleksejb.domain.core.timer.timerutil.getInitialTime
import com.aleksejb.domain.core.timer.timerutil.toTimerTime
import com.aleksejb.ui.core.viewmodel.MVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject
import kotlin.concurrent.timer

@HiltViewModel
class TimerViewModel @Inject constructor(

): MVIViewModel<TimerState, TimerEvent, TimerEffect>() {

    override val _state = MutableStateFlow<TimerState>(TimerState.Initial)

    private var whiteTimeLong = RapidFormat.TEN.getInitialTime()
    private var blackTimeLong = RapidFormat.TEN.getInitialTime()

    val whiteTimer = (0..Int.MAX_VALUE)
        .asSequence()
        .asFlow()
        .onEach { delay(1000) }

    val blackTimer = (0..Int.MAX_VALUE)
        .asSequence()
        .asFlow()
        .onEach { delay(1000) }

    init {
        viewModelScope.launch {
            whiteTimer.collect {
                if (state.value.isWhiteTimeTicking) {
                    whiteTimeLong -= 1000L
                    updateState { copy(whiteTime = whiteTimeLong.toTimerTime()) }
                }
            }
        }

        viewModelScope.launch {
            blackTimer.collect {
                if (state.value.isBlackTimeTicking) {
                    blackTimeLong -= 1000L
                    updateState { copy(blackTime = blackTimeLong.toTimerTime()) }
                }
            }
        }
    }

    override fun handleEvent(event: TimerEvent) {
        when (event) {
            is TimerEvent.OnWhiteTimerClicked -> {
                Log.d("TAAAG", "before state update")
                Log.d("TAAAG", "isWhiteTicking: ${state.value.isWhiteTimeTicking}")
                Log.d("TAAAG", "isBlackTicking: ${state.value.isBlackTimeTicking}")
                updateState { copy(
                    isWhiteTimeTicking = !state.value.isWhiteTimeTicking,
                    isBlackTimeTicking = !state.value.isBlackTimeTicking
                ) }
                Log.d("TAAAG", "after state update")
                Log.d("TAAAG", "isWhiteTicking: ${state.value.isWhiteTimeTicking}")
                Log.d("TAAAG", "isBlackTicking: ${state.value.isBlackTimeTicking}")
            }
            is TimerEvent.OnBlackTimerClicked -> {
                Log.d("TAAAG", "before state update")
                Log.d("TAAAG", "isWhiteTicking: ${state.value.isWhiteTimeTicking}")
                Log.d("TAAAG", "isBlackTicking: ${state.value.isBlackTimeTicking}")
                updateState { copy(
                    isWhiteTimeTicking = !state.value.isWhiteTimeTicking,
                    isBlackTimeTicking = !state.value.isBlackTimeTicking
                ) }
                Log.d("TAAAG", "after state update")
                Log.d("TAAAG", "isWhiteTicking: ${state.value.isWhiteTimeTicking}")
                Log.d("TAAAG", "isBlackTicking: ${state.value.isBlackTimeTicking}")
            }
            is TimerEvent.OnResetClicked -> {
                whiteTimeLong = RapidFormat.TEN.getInitialTime()
                blackTimeLong = RapidFormat.TEN.getInitialTime()
                updateState { copy(
                    gameStarted = false,
                    isWhiteTimeTicking = false,
                    isBlackTimeTicking = false,
                    whiteTime = whiteTimeLong.toTimerTime(),
                    blackTime = blackTimeLong.toTimerTime()
                ) }
            }
            is TimerEvent.OnGameStarted -> updateState { copy(
                gameStarted = true,
                isWhiteTimeTicking = true
            ) }
        }
    }

}