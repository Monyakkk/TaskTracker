package com.komissarov.tasktracker.tasks.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.komissarov.tasktracker.tasks.recycler.viewholders.HeaderViewHolder
import com.komissarov.tasktracker.tasks.recycler.viewholders.TaskViewHolder

class TasksAdapter(private val taskClickListener: ((TasksListItem.Task) -> Unit)) :
    ListAdapter<TasksListItem, RecyclerView.ViewHolder>(TasksDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> TaskViewHolder.from(parent, taskClickListener)
            2 -> HeaderViewHolder.from(parent)
            else -> throw ClassCastException("Unknown type number")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                holder.bind(getItem(position) as TasksListItem.Header)
            }
            is TaskViewHolder -> {
                holder.bind(getItem(position) as TasksListItem.Task)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is TasksListItem.Task -> 1
            is TasksListItem.Header -> 2
        }
    }

    fun setTasks(list: List<TasksListItem>) {
        submitList(list)
    }

}