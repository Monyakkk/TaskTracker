package com.komissarov.tasktracker.tasks.taskcreation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.komissarov.tasktracker.CoroutineDispatchers
import com.komissarov.tasktracker.data.network.entities.PostTask
import com.komissarov.tasktracker.data.network.entities.SubjectList
import com.komissarov.tasktracker.data.network.entities.TaskDetail
import com.komissarov.tasktracker.launchWithExceptionHandle
import com.komissarov.tasktracker.subjects.SubjectsRepository
import com.komissarov.tasktracker.tasks.TasksRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class TaskCreationViewModel @Inject constructor(
    private val tasksRepository: TasksRepository,
    private val subjectsRepository: SubjectsRepository,
    private val dispatchers: CoroutineDispatchers,
) : ViewModel() {

    private val errorMessage = MutableLiveData<String>()
    val subjectList = MutableLiveData<List<SubjectList>>()
    private var jobTaskCreation: Job? = null
    private var jobGettingSubjects: Job? = null
    private val loading = MutableLiveData<Boolean>()

    fun postTask(task: PostTask) {
        jobTaskCreation = viewModelScope.launch(context = dispatchers.io) {
            launchWithExceptionHandle {
                tasksRepository.postTask(task)
            }.onSuccess {
                withContext(viewModelScope.coroutineContext) {
                    loading.value = false
                }
            }.onFailure {
                withContext(viewModelScope.coroutineContext) {
                    onError(it)
                }
            }
        }
    }

    fun getSubjects(){
        jobGettingSubjects = viewModelScope.launch(context = dispatchers.io) {
            launchWithExceptionHandle {
                subjectsRepository.getSubjects()
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
        Timber.tag("TaskDetailViewModel").e(error)
        errorMessage.value = error.message.toString()
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        jobTaskCreation?.cancel()
    }
}