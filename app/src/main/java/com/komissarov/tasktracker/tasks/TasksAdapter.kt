package com.komissarov.tasktracker.tasks

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.komissarov.tasktracker.data.network.entities.TaskList

class TasksAdapter: RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    class ViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    fun setTasks(lists: List<TaskList>?) {
        TODO("Not yet implemented")
    }
}