package com.aleksejb.domain.core.timer.model

import com.aleksejb.domain.core.timer.model.gameformat.BlitzFormat
import com.aleksejb.domain.core.timer.model.gameformat.BulletFormat
import com.aleksejb.domain.core.timer.model.gameformat.RapidFormat

sealed interface GameType {
    data class Blitz(val format: BlitzFormat): GameType
    data class Bullet(val format: BulletFormat): GameType
    data class Rapid(val format: RapidFormat): GameType
}