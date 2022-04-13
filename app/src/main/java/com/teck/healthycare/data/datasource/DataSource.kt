package com.teck.healthycare.data.datasource

import com.teck.healthycare.domain.model.Health
import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun getData(): Flow<List<Health>>
    suspend fun setData(health: Health)
}