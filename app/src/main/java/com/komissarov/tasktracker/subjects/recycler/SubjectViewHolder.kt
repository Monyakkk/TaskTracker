package com.komissarov.tasktracker.subjects.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.komissarov.tasktracker.data.network.entities.SubjectList
import com.komissarov.tasktracker.databinding.ItemSubjectslistSubjectBinding


class SubjectViewHolder(
    private val binding: ItemSubjectslistSubjectBinding,
    private val taskClickListener: ((SubjectList) -> Unit)
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: SubjectList) {
        binding.taskHeader.text = data.title
        binding.taskDescription.text = data.assessmentType + "\n" + data.teacherName
        binding.taskContainer.setOnClickListener { taskClickListener(data) }
    }

    companion object {
        fun from(parent: ViewGroup, subjectClickListener: ((SubjectList) -> Unit)): SubjectViewHolder {
            return SubjectViewHolder(
                ItemSubjectslistSubjectBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), subjectClickListener
            )
        }
    }
}