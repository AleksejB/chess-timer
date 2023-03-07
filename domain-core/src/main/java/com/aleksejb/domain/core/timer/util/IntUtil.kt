package com.aleksejb.domain.core.timer.util

import com.aleksejb.domain.core.timer.model.PlayerColor

fun Int.toPlayerColor() =
    when (this) {
        1 -> PlayerColor.WHITE
        0 -> PlayerColor.BLACK
        -1 -> PlayerColor.NONE
        else -> throw java.lang.IllegalStateException("Integer can not be mapped to PlayerColor as it is not in -1..1")
    }