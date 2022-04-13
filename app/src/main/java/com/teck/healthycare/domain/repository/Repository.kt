package com.teck.healthycare.domain.repository

import com.teck.healthycare.domain.model.Health
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getData() : Flow<List<Health>>
    suspend fun setData(health: Health)
}