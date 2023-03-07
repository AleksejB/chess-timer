package com.aleksejb.domain.core.timer.datasource

interface SessionDataSource {
    suspend fun saveSession()
    suspend fun getSession()
}