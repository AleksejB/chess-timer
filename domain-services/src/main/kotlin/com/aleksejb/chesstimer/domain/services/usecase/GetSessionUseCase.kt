package com.aleksejb.chesstimer.domain.services.usecase

import com.aleksejb.chesstimer.data.core.dto.DbSession
import com.aleksejb.chesstimer.data.core.dto.toModel
import com.aleksejb.domain.core.timer.model.Session
import io.realm.Realm
import javax.inject.Inject


class GetSessionUseCase @Inject constructor(
   private val db: Realm
) {

    operator fun invoke(): Session? {
        return db.where(DbSession::class.java).findFirst()?.toModel()
    }
}