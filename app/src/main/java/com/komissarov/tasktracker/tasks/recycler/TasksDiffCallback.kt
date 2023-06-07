package com.komissarov.tasktracker.tasks.recycler

import androidx.recyclerview.widget.DiffUtil

class TasksDiffCallback : DiffUtil.ItemCallback<TasksListItem>() {

    override fun areItemsTheSame(oldItem: TasksListItem, newItem: TasksListItem) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: TasksListItem, newItem: TasksListItem) =
        oldItem == newItem
}
