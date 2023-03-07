package com.aleksejb.chesstimer.ui.timer

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.aleksejb.chesstimer.domain.services.usecase.GetSessionUseCase
import com.aleksejb.chesstimer.domain.services.usecase.SaveSessionUseCase
import com.aleksejb.domain.core.timer.model.PlayerColor
import com.aleksejb.domain.core.timer.model.TimerTime
import com.aleksejb.domain.core.timer.model.gameformat.RapidFormat
import com.aleksejb.domain.core.timer.util.getInitialTime
import com.aleksejb.domain.core.timer.util.toTimerTime
import com.aleksejb.ui.core.viewmodel.MVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val saveSessionUseCase: SaveSessionUseCase,
    private val getSessionUseCase: GetSessionUseCase
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
                    if (whiteTimeLong == 0L) return@collect
                    whiteTimeLong -= 1000L
                    updateState { copy(whiteTime = whiteTimeLong.toTimerTime()) }
                }
            }
        }

        viewModelScope.launch {
            blackTimer.collect {
                if (state.value.isBlackTimeTicking) {
                    if (blackTimeLong == 0L) return@collect
                    blackTimeLong -= 1000L
                    updateState { copy(blackTime = blackTimeLong.toTimerTime()) }
                }
            }
        }

        viewModelScope.launch {
            val session = getSessionUseCase.invoke()
            blackTimeLong = session?.blackTime ?: RapidFormat.TEN.getInitialTime()
            whiteTimeLong = session?.whiteTime ?: RapidFormat.TEN.getInitialTime()

            updateState { copy(
                blackTime = blackTimeLong.toTimerTime(),
                whiteTime = whiteTimeLong.toTimerTime(),
                lastMovedBy = session?.lastMoveBy ?: PlayerColor.NONE,
                gameInProgress = false
            ) }
        }
    }

    override fun handleEvent(event: TimerEvent) {
        when (event) {
            is TimerEvent.OnWhiteTimerClicked -> {
                updateState { copy(
                    isWhiteTimeTicking = !state.value.isWhiteTimeTicking,
                    isBlackTimeTicking = !state.value.isBlackTimeTicking,
                    lastMovedBy = PlayerColor.WHITE
                ) }
            }
            is TimerEvent.OnBlackTimerClicked -> {
                updateState { copy(
                    isWhiteTimeTicking = !state.value.isWhiteTimeTicking,
                    isBlackTimeTicking = !state.value.isBlackTimeTicking,
                    lastMovedBy = PlayerColor.BLACK
                ) }
            }
            is TimerEvent.OnResetClicked -> {
                whiteTimeLong = RapidFormat.TEN.getInitialTime()
                blackTimeLong = RapidFormat.TEN.getInitialTime()
                updateState { copy(
                    gameInProgress = false,
                    isWhiteTimeTicking = false,
                    isBlackTimeTicking = false,
                    whiteTime = whiteTimeLong.toTimerTime(),
                    blackTime = blackTimeLong.toTimerTime(),
                    lastMovedBy = PlayerColor.NONE
                ) }
            }
            is TimerEvent.OnGameStarted -> updateState { copy(
                gameInProgress = true,
                isWhiteTimeTicking = true
            ) }
            is TimerEvent.OnPausePlayClicked -> {
                updateState {
                    if (state.value.gameInProgress) {
                        copy(
                            gameInProgress = false,
                            isWhiteTimeTicking = false,
                            isBlackTimeTicking = false
                        )
                    } else {
                        when (state.value.lastMovedBy) {
                            PlayerColor.NONE -> {
                                copy(
                                    gameInProgress = true,
                                    isWhiteTimeTicking = true,
                                    isBlackTimeTicking = false
                                )
                            }
                            PlayerColor.WHITE -> {
                                copy(
                                    gameInProgress = true,
                                    isWhiteTimeTicking = true,
                                    isBlackTimeTicking = false
                                )
                            }
                            PlayerColor.BLACK -> {
                                copy(
                                    gameInProgress = true,
                                    isWhiteTimeTicking = false,
                                    isBlackTimeTicking = true
                                )
                            }
                        }
                    }
                }
                saveSession()
            }
        }
    }

    private fun saveSession() {
        viewModelScope.launch {
            saveSessionUseCase.invoke(
                newBlackTime = blackTimeLong,
                newWhiteTime = whiteTimeLong,
                newLastMoveBy = state.value.lastMovedBy
            )
        }
    }
}