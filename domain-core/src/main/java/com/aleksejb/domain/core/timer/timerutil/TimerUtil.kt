package com.aleksejb.domain.core.timer.timerutil

import com.aleksejb.domain.core.timer.constants.Constants.FIFTEEN_MIN_IN_MILLIS
import com.aleksejb.domain.core.timer.constants.Constants.FIVE_MIN_IN_MILLIS
import com.aleksejb.domain.core.timer.constants.Constants.ONE_MIN_IN_MILLIS
import com.aleksejb.domain.core.timer.constants.Constants.SIXTY_MIN_IN_MILLIS
import com.aleksejb.domain.core.timer.constants.Constants.TEN_MIN_IN_MILLIS
import com.aleksejb.domain.core.timer.constants.Constants.THIRTY_MIN_IN_MILLIS
import com.aleksejb.domain.core.timer.constants.Constants.THREE_MIN_IN_MILLIS
import com.aleksejb.domain.core.timer.constants.Constants.TWENTY_MIN_IN_MILLIS
import com.aleksejb.domain.core.timer.constants.Constants.TWO_MIN_IN_MILLIS
import com.aleksejb.domain.core.timer.model.GameType
import com.aleksejb.domain.core.timer.model.gameformat.BlitzFormat
import com.aleksejb.domain.core.timer.model.gameformat.BulletFormat
import com.aleksejb.domain.core.timer.model.gameformat.RapidFormat

fun GameType.getInitialTime(): Long {
    return when (this) {
        is GameType.Bullet -> format.getInitialTime()
        is GameType.Blitz -> format.getInitialTime()
        is GameType.Rapid -> format.getInitialTime()
    }
}

fun RapidFormat.getInitialTime(): Long {
    return when (this) {
        RapidFormat.SIXTY -> SIXTY_MIN_IN_MILLIS
        RapidFormat.THIRTY -> THIRTY_MIN_IN_MILLIS
        RapidFormat.TWENTY -> TWENTY_MIN_IN_MILLIS
        RapidFormat.FIFTEEN_PLUS_FIVE -> FIFTEEN_MIN_IN_MILLIS
        RapidFormat.TEN_PLUS_FIVE -> TEN_MIN_IN_MILLIS
        RapidFormat.TEN -> TEN_MIN_IN_MILLIS
    }
}

fun BlitzFormat.getInitialTime(): Long {
    return when (this) {
        BlitzFormat.FIVE_PLUS_FIVE -> FIVE_MIN_IN_MILLIS
        BlitzFormat.FIVE_PLUS_TWO -> FIVE_MIN_IN_MILLIS
        BlitzFormat.FIVE -> FIVE_MIN_IN_MILLIS
        BlitzFormat.THREE_PLUS_TWO -> THREE_MIN_IN_MILLIS
        BlitzFormat.THREE -> THREE_MIN_IN_MILLIS
    }
}

fun BulletFormat.getInitialTime(): Long {
    return when (this) {
        BulletFormat.TWO_PLUS_ONE -> TWO_MIN_IN_MILLIS
        BulletFormat.ONE_PLUS_ONE -> TWENTY_MIN_IN_MILLIS
        BulletFormat.ONE -> ONE_MIN_IN_MILLIS
    }
}