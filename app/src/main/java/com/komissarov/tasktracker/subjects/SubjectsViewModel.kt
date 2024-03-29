package com.komissarov.tasktracker.subjects

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.komissarov.tasktracker.CoroutineDispatchers
import com.komissarov.tasktracker.data.network.entities.SubjectList
import com.komissarov.tasktracker.data.network.entities.SubjectTitle
import com.komissarov.tasktracker.data.network.entities.TaskList
import com.komissarov.tasktracker.launchWithExceptionHandle
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class SubjectsViewModel @Inject constructor(
    private val subjectsRepository: SubjectsRepository,
    private val dispatchers: CoroutineDispatchers,
) : ViewModel() {

    private val errorMessage = MutableLiveData<String>()
    val subjectList = MutableLiveData<List<SubjectList>>()
    private var job: Job? = null
    private val loading = MutableLiveData<Boolean>()

    fun getAllSubjects() {
        job = viewModelScope.launch(context = dispatchers.io) {
            launchWithExceptionHandle {
                subjectsRepository.getSubjects() ?: emptyList()
            }.onSuccess { result ->
                withContext(viewModelScope.coroutineContext) {
                    subjectList.postValue(result)
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
        Timber.tag("SubjectsViewModel").e(error)
        errorMessage.value = error.message.toString()
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}