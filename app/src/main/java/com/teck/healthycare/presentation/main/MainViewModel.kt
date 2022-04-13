package com.teck.healthycare.presentation.main

import com.teck.healthycare.domain.AppState
import com.teck.healthycare.domain.model.Health
import com.teck.healthycare.domain.repository.Repository
import com.teck.healthycare.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository
) : BaseViewModel() {
    override fun getData() {
        viewModelCoroutineScope.launch {
            repository.getData().collect { healthList ->
                  liveData.postValue(healthList.let { healthList -> AppState.Success(healthList) })
              }
        }
    }

    fun saveData(health: Health) {
        viewModelCoroutineScope.launch {
            repository.setData(health)
            getData()
        }
    }

    override fun handleError(throwable: Throwable) {
        liveData.postValue(AppState.Error(throwable))
    }
}