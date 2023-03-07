package com.aleksejb.chesstimer

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm
import io.realm.RealmConfiguration

@HiltAndroidApp
class ChessTimerApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .name("chesstimer.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(1)
            .build()

        Realm.setDefaultConfiguration(config)
    }
}