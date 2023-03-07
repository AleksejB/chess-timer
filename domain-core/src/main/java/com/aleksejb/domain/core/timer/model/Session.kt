package com.aleksejb.domain.core.timer.model

data class Session(
    val blackTime: Long,
    val whiteTime: Long,
    val lastMoveBy: PlayerColor
)