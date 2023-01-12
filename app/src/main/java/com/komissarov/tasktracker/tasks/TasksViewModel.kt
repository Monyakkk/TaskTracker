package com.komissarov.tasktracker.tasks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.komissarov.tasktracker.data.network.entities.SubjectTitle
import com.komissarov.tasktracker.data.network.entities.TaskList
import java.util.*

class TasksViewModel : ViewModel() {

    val tasks: MutableLiveData<List<TaskList>> = MutableLiveData(mockList)

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
                Date(1697148598)
            ),
            TaskList(
                4,
                "Таск1",
                SubjectTitle(
                    0,
                    "Вы только посмотрите, еще и предмет длинный, бедные студенты (ВТПЕиПДБС))"
                ),
                Date(1697062198)
            ),
            TaskList(5, "Таск1", SubjectTitle(0, "Сабж1"), Date(1686607798)),
        )
    }
}