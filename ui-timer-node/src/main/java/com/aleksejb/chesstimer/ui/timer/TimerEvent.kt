package com.aleksejb.chesstimer.ui.timer

sealed interface TimerEvent {

    object OnGameStarted: TimerEvent
    object OnWhiteTimerClicked: TimerEvent
    object OnBlackTimerClicked: TimerEvent
    object OnResetClicked: TimerEvent
    object OnPausePlayClicked: TimerEvent
}