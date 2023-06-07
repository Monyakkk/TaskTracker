package com.komissarov.tasktracker.tasks.taskdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.komissarov.tasktracker.CoroutineDispatchers
import com.komissarov.tasktracker.data.network.entities.TaskDetail
import com.komissarov.tasktracker.launchWithExceptionHandle
import com.komissarov.tasktracker.tasks.TasksRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class TaskDetailViewModel @Inject constructor(
    private val tasksRepository: TasksRepository,
    private val dispatchers: CoroutineDispatchers,
) : ViewModel() {

    private val errorMessage = MutableLiveData<String>()
    val task = MutableLiveData<TaskDetail>()
    private var jobTaskDetails: Job? = null
    private var jobStatus: Job? = null
    private val loading = MutableLiveData<Boolean>()

    fun getTaskDetails(id: Int) {
        jobTaskDetails = viewModelScope.launch(context = dispatchers.io) {
            launchWithExceptionHandle {
                tasksRepository.getTaskDetails(id)
            }.onSuccess { result ->
                withContext(viewModelScope.coroutineContext) {
                    task.postValue(result)
                    loading.value = false
                }
            }.onFailure {
                withContext(viewModelScope.coroutineContext) {
                    onError(it)
                }
            }
        }
    }

    fun setStatus(id: Int, isCompleted: Boolean) {
        jobStatus = viewModelScope.launch(context = dispatchers.io) {
            launchWithExceptionHandle {
                if (isCompleted) {
                    tasksRepository.setCompleted(id)
                } else {
                    tasksRepository.setUncomplete(id)
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
        jobTaskDetails?.cancel()
        jobStatus?.cancel()
    }
}