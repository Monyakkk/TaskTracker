package com.komissarov.tasktracker.tasks

import com.komissarov.tasktracker.data.network.ApiService
import com.komissarov.tasktracker.data.network.entities.TaskDetail
import com.komissarov.tasktracker.data.network.entities.TaskList
import com.komissarov.tasktracker.data.network.entities.PostTask
import com.komissarov.tasktracker.data.network.entities.PutTask
import javax.inject.Inject

class TasksRepository @Inject constructor(private val service: ApiService) {

    suspend fun getTasks(status: String? = null): List<TaskList>? {
        return service.getTasks(status = status).results
    }

    suspend fun getTaskDetails(id: Int): TaskDetail {
        return service.getTask(id)
    }

    suspend fun setCompleted(id: Int) {
        return service.completeTask(id)
    }

    suspend fun setUncomplete(id: Int) {
        return service.uncompleteTask(id)
    }

    suspend fun postTask(task: PostTask): PutTask {
        return service.postTask(task)
    }

    suspend fun deleteTask(id: Int) {
        return service.deleteTask(id)
    }

    suspend fun updateTask(id: Int, putTask: PutTask): PutTask {
        return service.putTask(id, putTask)
    }

}