package com.komissarov.tasktracker.subjects

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

    companion object {
        private val mockList = listOf(
            TaskList(0, "Таск1", SubjectTitle(0, "Сабж1"), Date(1697062198)),
            TaskList(1, "1", SubjectTitle(0, "Сабж1"), Date(1697062198)),
            TaskList(
                2,
                "Такая длинная таска что ужас просто интересно влезет или нет",
                SubjectTitle(0, "Сабж1"),
                Date(1697062198)
            ),
            TaskList(
                3,
                "Такая длинная таска что ужас просто интересно влезет или нет",
                SubjectTitle(0, "Сабж1"),
                Date(16971485)
            ),
            TaskList(
                4,
                "Таск1",
                SubjectTitle(
                    0,
                    "Вы только посмотрите, еще и предмет длинный, бедные студенты (ВТПЕиПДБС))"
                ),
                Date(7062198)
            ),
            TaskList(5, "Таск1", SubjectTitle(0, "Сабж1"), Date(1686607798)),
        )
    }
}