package com.aleksejb.chesstimer.domain.services.usecase

import com.aleksejb.chesstimer.data.core.dto.DbSession
import com.aleksejb.domain.core.timer.model.PlayerColor
import com.aleksejb.domain.core.timer.model.toInt
import io.realm.Realm
import javax.inject.Inject

class SaveSessionUseCase @Inject constructor(
    private val db: Realm
) {

    suspend operator fun invoke(
        newBlackTime: Long,
        newWhiteTime: Long,
        newLastMoveBy: PlayerColor
    ) {
        db.executeTransactionAsync {
            val dbSession = DbSession().apply {
                blackTime = newBlackTime
                whiteTime = newWhiteTime
                lastMoveBy = newLastMoveBy.toInt()
            }
            it.insertOrUpdate(dbSession)
        }
    }
}