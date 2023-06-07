package com.komissarov.tasktracker.login.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.komissarov.tasktracker.CoroutineDispatchers
import com.komissarov.tasktracker.data.network.SessionManager
import com.komissarov.tasktracker.launchWithExceptionHandle
import com.komissarov.tasktracker.login.LoginRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dispatchers: CoroutineDispatchers,
    private val sessionManager: SessionManager,
) : ViewModel() {
    val isLoginSuccessful = MutableLiveData(false)
    private val errorMessage = MutableLiveData<String>()
    private var job: Job? = null

    fun login(email: String, password: String) {
        job = viewModelScope.launch(context = dispatchers.io) {
            launchWithExceptionHandle {
                val tokenObtainPair = loginRepository.getTokenObtainPair(email, password)
                sessionManager.saveAuthToken(tokenObtainPair.refresh, tokenObtainPair.access)
                sessionManager.getRefreshToken()
            }.onSuccess {
                withContext(dispatchers.main) {
                    isLoginSuccessful.postValue(it != null && it.isNotEmpty())
                }
            }.onFailure {
                withContext(dispatchers.main) {
                    isLoginSuccessful.postValue(false)
                    onError(it)
                }
            }
        }
    }

    private fun onError(error: Throwable) {
        Timber.tag("LoginViewModel").e(error)
        errorMessage.value = error.message.toString()
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}