package com.aleksejb.domain.core.timer.model

data class TimerTime(
    val hours: String,
    val minutes: String,
    val seconds: String
) {
    companion object {
        val Default = TimerTime(
            hours = "00",
            minutes = "00",
            seconds = "00"
        )
    }
}