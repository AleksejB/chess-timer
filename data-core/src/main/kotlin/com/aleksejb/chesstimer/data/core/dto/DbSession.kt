package com.aleksejb.chesstimer.data.core.dto

import com.aleksejb.domain.core.timer.model.PlayerColor
import com.aleksejb.domain.core.timer.model.Session
import com.aleksejb.domain.core.timer.util.toPlayerColor
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class DbSession: RealmObject() {
    @PrimaryKey
    var id: Int = 1 // can only be one
    var blackTime: Long = 0L
    var whiteTime: Long = 0L
    var lastMoveBy: Int = -1
}

fun DbSession.toModel() =
    Session(
        blackTime = blackTime,
        whiteTime = whiteTime,
        lastMoveBy = lastMoveBy.toPlayerColor()
    )
