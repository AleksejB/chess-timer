package com.aleksejb.domain.core.timer.model

enum class PlayerColor {
    WHITE,
    BLACK,
    NONE
}

fun PlayerColor.toInt() =
    when (this) {
        PlayerColor.BLACK -> 0
        PlayerColor.NONE -> -1
        PlayerColor.WHITE ->1
    }