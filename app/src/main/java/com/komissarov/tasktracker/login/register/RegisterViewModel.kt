package com.komissarov.tasktracker.login.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.komissarov.tasktracker.CoroutineDispatchers
import com.komissarov.tasktracker.data.network.entities.EducationalProgram
import com.komissarov.tasktracker.data.network.entities.FieldOfStudy
import com.komissarov.tasktracker.launchWithExceptionHandle
import com.komissarov.tasktracker.login.LoginRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dispatchers: CoroutineDispatchers,
) : ViewModel() {

    val educationalProgramsList = MutableLiveData<List<EducationalProgram>>()
    val fieldsList = MutableLiveData<List<FieldOfStudy>>()
    val isRegisterSuccessful = MutableLiveData(false)
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private var jobFields: Job? = null
    private var jobPlans: Job? = null
    private var jobRegister: Job? = null

    fun getFieldsOfStudy() {
        jobFields = viewModelScope.launch(context = dispatchers.io) {
            launchWithExceptionHandle {
                loginRepository.getFieldsOfStudyList()
            }.onSuccess {
                withContext(viewModelScope.coroutineContext) {
                    fieldsList.postValue(it)
                    loading.value = false
                }
            }.onFailure {
                withContext(viewModelScope.coroutineContext) {
                    onError(it)
                }
            }
        }
    }

    fun getEducationalPrograms() {
        jobPlans = viewModelScope.launch(context = dispatchers.io) {
            launchWithExceptionHandle {
                loginRepository.getEducationalProgramsList()
            }.onSuccess {
                withContext(viewModelScope.coroutineContext) {
                    educationalProgramsList.postValue(it)
                    loading.value = false
                }
            }.onFailure {
                withContext(viewModelScope.coroutineContext) {
                    onError(it)
                }
            }
        }
    }

    fun register(
        lastName: String,
        firstName: String,
        thirdName: String?,
        email: String,
        password: String,
        educationalProgramId: Int,
        fieldOfStudyId: Int
    ) {
        jobRegister = viewModelScope.launch(context = dispatchers.io) {
            launchWithExceptionHandle {
                loginRepository.postUser(
                    lastName = lastName,
                    firstName = firstName,
                    middleName = thirdName,
                    email = email,
                    password = password,
                    educationalProgramId = educationalProgramId,
                    fieldOfStudyId = fieldOfStudyId
                )
            }.onSuccess {
                withContext(viewModelScope.coroutineContext) {
                    isRegisterSuccessful.postValue(true)
                }
            }.onFailure {
                withContext(viewModelScope.coroutineContext) {
                    onError(it)
                }
            }
        }
    }

    private fun onError(error: Throwable) {
        Timber.e("RegisterViewModel " + error.message, error)
        errorMessage.value = error.message.toString()
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        jobRegister?.cancel()
        jobFields?.cancel()
        jobPlans?.cancel()
    }
}