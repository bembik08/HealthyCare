package com.teck.healthycare.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teck.healthycare.domain.AppState
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {
    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )
    protected val liveData = MutableLiveData<AppState>()
    fun getLiveData(): LiveData<AppState> = liveData
    abstract fun getData()
    abstract fun handleError(throwable: Throwable)
    override fun onCleared() {
        viewModelCoroutineScope.cancel()
        super.onCleared()
    }
}