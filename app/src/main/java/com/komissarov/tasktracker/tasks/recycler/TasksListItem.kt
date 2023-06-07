package com.komissarov.tasktracker.tasks.recycler

sealed class TasksListItem {

    abstract val id: Int

    data class Task(
        override val id: Int,
        val subjectTitle: String,
        val taskTitle: String,
        val deadline: String?,
        val deadlineType: TaskDeadlineType,
    ) : TasksListItem()

    data class Header(val date: String) : TasksListItem() {
        override val id: Int
            get() = date.hashCode()
    }
}