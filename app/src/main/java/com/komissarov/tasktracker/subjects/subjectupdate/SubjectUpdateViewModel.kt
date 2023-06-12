package com.komissarov.tasktracker.subjects.subjectupdate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.komissarov.tasktracker.CoroutineDispatchers
import com.komissarov.tasktracker.data.network.entities.Subject
import com.komissarov.tasktracker.data.network.entities.UpdateSubject
import com.komissarov.tasktracker.launchWithExceptionHandle
import com.komissarov.tasktracker.subjects.SubjectsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class SubjectUpdateViewModel @Inject constructor(
    private val subjectsRepository: SubjectsRepository,
    private val dispatchers: CoroutineDispatchers,
) : ViewModel() {

    private val errorMessage = MutableLiveData<String>()
    private val subjectLiveData = MutableLiveData<UpdateSubject>()
    private var jobUpdateSubject: Job? = null
    private val loading = MutableLiveData<Boolean>()

    fun updateSubject(id: Int, subject: UpdateSubject) {
        jobUpdateSubject = viewModelScope.launch(context = dispatchers.io) {
            launchWithExceptionHandle {
                subjectsRepository.updateSubject(id, subject)
            }.onSuccess { result ->
                withContext(viewModelScope.coroutineContext) {
                    subjectLiveData.postValue(result)
                    loading.value = false
                }
            }.onFailure {
                withContext(viewModelScope.coroutineContext) {
                    onError(it)
                }
            }
        }
    }

    private fun onError(error: Throwable) {
        Timber.tag("SubjectDetailViewModel").e(error)
        errorMessage.value = error.message.toString()
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        jobUpdateSubject?.cancel()
    }
}