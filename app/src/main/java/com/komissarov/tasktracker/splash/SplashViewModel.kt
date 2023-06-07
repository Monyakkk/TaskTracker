package com.komissarov.tasktracker.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.komissarov.tasktracker.CoroutineDispatchers
import com.komissarov.tasktracker.data.network.SessionManager
import com.komissarov.tasktracker.launchWithExceptionHandle
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val dispatchers: CoroutineDispatchers,
) :
    ViewModel() {
    private val errorMessage = MutableLiveData<String>()
    val isLogged = MutableLiveData<Boolean>()
    var job: Job? = null
    private val loading = MutableLiveData<Boolean>()

    fun getLogged() {
        job = viewModelScope.launch(context = dispatchers.io) {
            launchWithExceptionHandle {
                sessionManager.getRefreshToken()
            }.onSuccess {
                withContext(viewModelScope.coroutineContext) {
                    isLogged.postValue(it != null)
                    loading.value = false
                }
            }.onFailure {
                withContext(viewModelScope.coroutineContext) { onError(it) }
            }
        }
    }


    private fun onError(error: Throwable) {
        Timber.e("SplashViewModel", error)
        errorMessage.value = error.message.toString()
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}