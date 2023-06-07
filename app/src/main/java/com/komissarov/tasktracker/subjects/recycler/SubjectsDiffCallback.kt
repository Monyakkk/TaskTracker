package com.komissarov.tasktracker.subjects.recycler

import androidx.recyclerview.widget.DiffUtil
import com.komissarov.tasktracker.data.network.entities.SubjectList

class SubjectsDiffCallback : DiffUtil.ItemCallback<SubjectList>() {

    override fun areItemsTheSame(oldItem: SubjectList, newItem: SubjectList) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SubjectList, newItem: SubjectList) =
        oldItem == newItem
}