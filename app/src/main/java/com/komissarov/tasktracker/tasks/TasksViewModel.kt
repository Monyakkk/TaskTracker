package com.komissarov.tasktracker.tasks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.komissarov.tasktracker.data.network.entities.SubjectTitle
import com.komissarov.tasktracker.data.network.entities.TaskList
import com.komissarov.tasktracker.CoroutineDispatchers
import com.komissarov.tasktracker.launchWithExceptionHandle
import com.komissarov.tasktracker.tasks.recycler.TasksListItem
import kotlinx.coroutines.*
import java.util.*
import timber.log.Timber
import javax.inject.Inject

class TasksViewModel @Inject constructor(
    private val tasksRepository: TasksRepository,
    private val networkToUiTasksMapper: NetworkToUiTasksMapper,
    private val altNetworkToUiTasksMapper: altNetworkToUiTasksMapper,
    private val dispatchers: CoroutineDispatchers,
) : ViewModel() {

    private val errorMessage = MutableLiveData<String>()
    val taskList = MutableLiveData<List<TasksListItem>>()
    var job: Job? = null
    private val loading = MutableLiveData<Boolean>()

    fun getAllTasks(tabStatus: String) {
        job = viewModelScope.launch(context = dispatchers.io) {
            launchWithExceptionHandle {
                if (tabStatus == "todo"){
                    networkToUiTasksMapper(tasksRepository.getTasks(tabStatus) ?: emptyList())
                }
                else{
                    altNetworkToUiTasksMapper(tasksRepository.getTasks(tabStatus) ?: emptyList())
                }

            }.onSuccess { result ->
                withContext(viewModelScope.coroutineContext) {
                    taskList.postValue(result)
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
        Timber.tag("TasksViewModel").e(error)
        errorMessage.value = error.message.toString()
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}