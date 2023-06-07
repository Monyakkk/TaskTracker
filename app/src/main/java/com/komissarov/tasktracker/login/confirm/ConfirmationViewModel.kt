package com.komissarov.tasktracker.login.confirm

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

class ConfirmationViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dispatchers: CoroutineDispatchers,
    private val sessionManager: SessionManager,
) : ViewModel() {

    val isRegisterSuccessful = MutableLiveData(false)
    val isConfirmSuccessful = MutableLiveData(false)
    private val errorMessage = MutableLiveData<String>()
    private var jobPostCode: Job? = null
    private var jobVerifyUser: Job? = null

    fun postCode(code: String, email: String) {
        jobPostCode = viewModelScope.launch(context = dispatchers.io) {
            launchWithExceptionHandle {
                loginRepository.postActivationCode(code, email)
            }.onSuccess {
                withContext(viewModelScope.coroutineContext) {
                    isConfirmSuccessful.postValue(true)
                }
            }.onFailure {
                withContext(viewModelScope.coroutineContext) {
                    onError(it)
                }
            }
        }
    }

    fun verifyUser(email: String, password: String) {
        jobVerifyUser = viewModelScope.launch(context = dispatchers.io) {
            launchWithExceptionHandle {
                val tokenObtainPair = loginRepository.getTokenObtainPair(email, password)
                sessionManager.saveAuthToken(tokenObtainPair.refresh, tokenObtainPair.access)
                sessionManager.getRefreshToken()
            }.onSuccess {
                withContext(viewModelScope.coroutineContext) {
                    isRegisterSuccessful.postValue(it != null)
                }
            }.onFailure {
                withContext(viewModelScope.coroutineContext) {
                    onError(it)
                }
            }
        }
    }

    private fun onError(error: Throwable) {
        Timber.tag("ConfirmationViewModel").e(error)
        errorMessage.value = error.message.toString()
    }

    override fun onCleared() {
        super.onCleared()
        jobPostCode?.cancel()
        jobVerifyUser?.cancel()
    }
}