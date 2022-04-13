package com.teck.healthycare.data

import com.teck.healthycare.data.datasource.DataSource
import com.teck.healthycare.domain.model.Health
import com.teck.healthycare.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val cloudSource: DataSource
) : Repository {
    override fun getData(): Flow<List<Health>> = cloudSource.getData()

    override suspend fun setData(health: Health) = cloudSource.setData(health)
}