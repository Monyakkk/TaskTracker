package com.komissarov.tasktracker.subjects.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.komissarov.tasktracker.data.network.entities.SubjectList

class SubjectsAdapter(private val subjectClickListener: ((SubjectList) -> Unit)) :
    ListAdapter<SubjectList, SubjectViewHolder>(SubjectsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        return SubjectViewHolder.from(parent, subjectClickListener)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {

        holder.bind(getItem(position) as SubjectList)
    }

    fun setSubjects(list: List<SubjectList>) {
        submitList(list)
    }
}