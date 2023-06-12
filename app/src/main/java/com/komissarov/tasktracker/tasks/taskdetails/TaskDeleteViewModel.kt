package com.komissarov.tasktracker.tasks.taskdetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.komissarov.tasktracker.CoroutineDispatchers
import com.komissarov.tasktracker.launchWithExceptionHandle
import com.komissarov.tasktracker.tasks.TasksRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class TaskDeleteViewModel @Inject constructor(
    private val tasksRepository: TasksRepository,
    private val dispatchers: CoroutineDispatchers,
) : ViewModel() {

    private val errorMessage = MutableLiveData<String>()
    private var jobTaskDelete: Job? = null
    private val loading = MutableLiveData<Boolean>()

    fun deleteTask(id: Int) {
        jobTaskDelete = viewModelScope.launch(context = dispatchers.io) {
            launchWithExceptionHandle {
                tasksRepository.deleteTask(id)
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
        jobTaskDelete?.cancel()
    }
}