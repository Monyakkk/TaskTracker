package com.komissarov.tasktracker.tasks.recycler.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.komissarov.tasktracker.databinding.ItemTasklistHeaderBinding
import com.komissarov.tasktracker.tasks.recycler.TasksListItem

class HeaderViewHolder(private val binding: ItemTasklistHeaderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: TasksListItem.Header) {
        binding.headerTextView.text = data.date
    }

    companion object {
        fun from(parent: ViewGroup): HeaderViewHolder {
            return HeaderViewHolder(
                ItemTasklistHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}