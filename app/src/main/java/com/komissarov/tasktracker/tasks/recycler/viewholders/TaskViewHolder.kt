package com.komissarov.tasktracker.tasks.recycler.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.komissarov.tasktracker.R
import com.komissarov.tasktracker.databinding.ItemTaskslistTaskBinding
import com.komissarov.tasktracker.tasks.recycler.TaskDeadlineType
import com.komissarov.tasktracker.tasks.recycler.TasksListItem

class TaskViewHolder(
    private val binding: ItemTaskslistTaskBinding,
    private val taskClickListener: ((TasksListItem.Task) -> Unit)
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: TasksListItem.Task) {
        binding.taskHeader.text = data.subjectTitle
        binding.taskDescription.text = data.taskTitle
        binding.taskContainer.setOnClickListener { taskClickListener(data) }
        when (data.deadlineType) {
            TaskDeadlineType.OVERDUE -> {
                binding.taskType.text = "просроченное"
                binding.taskContainer.setBackgroundResource(R.drawable.shape_list_item_overdue)
            }
            TaskDeadlineType.URGENT -> {
                binding.taskType.text = "срочное"
                binding.taskContainer.setBackgroundResource(R.drawable.shape_list_item_urgent)
            }
            TaskDeadlineType.NORMAL -> {
                binding.taskType.text = ""
                binding.taskContainer.setBackgroundResource(R.drawable.shape_list_item_rectangle)
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup, taskClickListener: ((TasksListItem.Task) -> Unit)): TaskViewHolder {
            return TaskViewHolder(
                ItemTaskslistTaskBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), taskClickListener
            )
        }
    }
}