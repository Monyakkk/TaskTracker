package com.komissarov.tasktracker.tasks


import com.komissarov.tasktracker.data.network.entities.TaskList
import com.komissarov.tasktracker.tasks.recycler.TaskDeadlineType
import com.komissarov.tasktracker.tasks.recycler.TasksListItem
import java.time.Month
import java.time.format.TextStyle
import java.util.*
import java.lang.System.currentTimeMillis
import java.time.Instant
import java.time.ZoneId

import javax.inject.Inject

class NetworkToUiTasksMapper @Inject constructor() {
    //todo map
    operator fun invoke(list: List<TaskList>): List<TasksListItem> {

        fun headerNameParser(rawDate: Date?): String {
            return if (rawDate != null) {
                rawDate.date.toString() + " " + Month.of(rawDate.month + 1)
                    .getDisplayName(TextStyle.FULL, Locale("ru"))
            } else {
                "Без дедлайна"
            }
        }

        fun deadlineTypeGenerator(rawDate: Date?): TaskDeadlineType {
            val threeDaysInMills: Long = 259200000
            return if (rawDate != null) {
                val currentDate = currentTimeMillis()
                val dateInMillis = rawDate.time
                if (dateInMillis < currentDate) {
                    TaskDeadlineType.OVERDUE
                } else {
                    if ((dateInMillis - currentDate) <= threeDaysInMills) {
                        TaskDeadlineType.URGENT
                    } else {
                        TaskDeadlineType.NORMAL
                    }
                }
            } else {
                TaskDeadlineType.NORMAL
            }
        }

        val withDeadlinesList: MutableList<TaskList> = mutableListOf()
        val noDeadlineList: MutableList<TaskList> = mutableListOf()
        val resultList: MutableList<TasksListItem> = mutableListOf()

        if (list.isEmpty()) {
            return emptyList()
        }

        // сортировка сущностей с дедлайном м без
        for (networkItem in list) {
            if (networkItem.deadlineAt != null) {
                withDeadlinesList.add(networkItem)
            } else {
                noDeadlineList.add(networkItem)
            }
        }

        if (withDeadlinesList.isNotEmpty()) {
            var previousDateHeader: String = headerNameParser(withDeadlinesList[0].deadlineAt)
            resultList.add(TasksListItem.Header(previousDateHeader)) // добавляем первый заголовок
            for (task in withDeadlinesList) {
                if (headerNameParser(task.deadlineAt) != previousDateHeader) {
                    previousDateHeader = headerNameParser(task.deadlineAt)
                    resultList.add(TasksListItem.Header(previousDateHeader))
                }
                // получаем время из timestamp-а
                val time = Instant.ofEpochMilli(task.deadlineAt!!.time)
                    .atZone(ZoneId.systemDefault())
                    .toLocalTime().toString()
                resultList.add(
                    TasksListItem.Task(
                        id = task.id,
                        taskTitle = task.title,
                        deadlineType = deadlineTypeGenerator(task.deadlineAt),
                        deadline = time,
                        subjectTitle = task.subject.title
                    )
                )
            }
        }

        if (noDeadlineList.isNotEmpty()) {
            resultList.add(TasksListItem.Header("Без дедлайна"))
            for (task in noDeadlineList) {
                resultList.add(
                    TasksListItem.Task(
                        id = task.id,
                        taskTitle = task.title,
                        deadlineType = deadlineTypeGenerator(task.deadlineAt),
                        subjectTitle = task.subject.title,
                        deadline = null
                    )
                )
            }
        }

        return resultList
    }
}
