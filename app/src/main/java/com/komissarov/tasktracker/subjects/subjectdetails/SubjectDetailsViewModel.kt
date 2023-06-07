package com.komissarov.tasktracker.subjects.subjectdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.komissarov.tasktracker.CoroutineDispatchers
import com.komissarov.tasktracker.data.network.entities.Subject
import com.komissarov.tasktracker.launchWithExceptionHandle
import com.komissarov.tasktracker.subjects.SubjectsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class SubjectDetailsViewModel @Inject constructor(
    private val subjectsRepository: SubjectsRepository,
    private val dispatchers: CoroutineDispatchers,
) : ViewModel() {

    private val errorMessage = MutableLiveData<String>()
    val subject = MutableLiveData<Subject>()
    private var jobSubjectDetails: Job? = null
    private val loading = MutableLiveData<Boolean>()

    fun getSubjectDetails(id: Int) {
        jobSubjectDetails = viewModelScope.launch(context = dispatchers.io) {
            launchWithExceptionHandle {
                subjectsRepository.getSubject(id)
            }.onSuccess { result ->
                withContext(viewModelScope.coroutineContext) {
                    subject.postValue(result)
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
        jobSubjectDetails?.cancel()
    }
}