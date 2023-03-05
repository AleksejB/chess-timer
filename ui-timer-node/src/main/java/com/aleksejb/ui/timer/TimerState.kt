package com.aleksejb.ui.timer

import com.aleksejb.domain.core.timer.model.GameType
import com.aleksejb.domain.core.timer.model.gameformat.RapidFormat
import com.aleksejb.domain.core.timer.timerutil.getInitialTime

data class TimerState(
    val gameType: GameType,
    val whiteTime: Long,
    val blackTime: Long
) {
    companion object {
        val Initial = TimerState(
            gameType = GameType.Rapid(RapidFormat.THIRTY),
            whiteTime = GameType.Rapid(RapidFormat.THIRTY).getInitialTime(),
            blackTime = GameType.Rapid(RapidFormat.THIRTY).getInitialTime()
        )
    }
}