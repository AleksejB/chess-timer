package com.aleksejb.domain.core.timer.timerutil

import com.aleksejb.domain.core.timer.constants.Constants
import com.aleksejb.domain.core.timer.model.TimerTime
import kotlin.math.floor

fun Long.toTimerTime(): TimerTime {

    val hrs = this / Constants.ONE_HOUR_IN_MILLIS
    val hrsFloored = floor(hrs.toDouble()).toInt()
    val mins = (this - (hrsFloored * Constants.ONE_HOUR_IN_MILLIS)) / Constants.ONE_MIN_IN_MILLIS
    val minsFloored = floor(mins.toDouble()).toInt()
    val secs = (this - (hrsFloored * Constants.ONE_HOUR_IN_MILLIS) - (minsFloored * Constants.ONE_MIN_IN_MILLIS)) / Constants.ONE_SEC_IN_MILLIS
    val secsFloored = floor(secs.toDouble()).toInt()

    return TimerTime(
        hours = if (hrsFloored < 10) {
            "0$hrsFloored"
        } else hrsFloored.toString(),
        minutes = if (minsFloored < 10) {
            "0$minsFloored"
        } else minsFloored.toString(),
        seconds = if (secsFloored < 10) {
            "0$secsFloored"
        } else secsFloored.toString()
    )
}