package com.aleksejb.chesstimer.ui.timer

import com.aleksejb.domain.core.timer.model.GameType
import com.aleksejb.domain.core.timer.model.PlayerColor
import com.aleksejb.domain.core.timer.model.TimerTime
import com.aleksejb.domain.core.timer.model.gameformat.RapidFormat
import com.aleksejb.domain.core.timer.util.getInitialTime
import com.aleksejb.domain.core.timer.util.toTimerTime

data class TimerState(
    val gameInProgress: Boolean,
    val gameType: GameType,
    val whiteTime: TimerTime,
    val isWhiteTimeTicking: Boolean,
    val blackTime: TimerTime,
    val isBlackTimeTicking: Boolean,
    val lastMovedBy: PlayerColor
) {
    companion object {
        val Initial = TimerState(
            gameInProgress = false,
            gameType = GameType.Rapid(RapidFormat.THIRTY),
            whiteTime = RapidFormat.TEN.getInitialTime().toTimerTime(),
            isWhiteTimeTicking = false,
            blackTime = RapidFormat.TEN.getInitialTime().toTimerTime(),
            isBlackTimeTicking = false,
            lastMovedBy = PlayerColor.NONE
        )
    }
}
