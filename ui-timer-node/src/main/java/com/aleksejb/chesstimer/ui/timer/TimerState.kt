package com.aleksejb.chesstimer.ui.timer

import com.aleksejb.domain.core.timer.model.GameType
import com.aleksejb.domain.core.timer.model.TimerTime
import com.aleksejb.domain.core.timer.model.gameformat.RapidFormat
import com.aleksejb.domain.core.timer.timerutil.getInitialTime
import com.aleksejb.domain.core.timer.timerutil.toTimerTime

data class TimerState(
    val gameStarted: Boolean,
    val gameType: GameType,
    val whiteTime: TimerTime,
    val isWhiteTimeTicking: Boolean,
    val blackTime: TimerTime,
    val isBlackTimeTicking: Boolean
) {
    companion object {
        val Initial = TimerState(
            gameStarted = false,
            gameType = GameType.Rapid(RapidFormat.THIRTY),
            whiteTime = RapidFormat.TEN.getInitialTime().toTimerTime(),
            isWhiteTimeTicking = false,
            blackTime = RapidFormat.TEN.getInitialTime().toTimerTime(),
            isBlackTimeTicking = false
        )
    }
}
