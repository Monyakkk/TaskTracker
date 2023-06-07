package com.komissarov.tasktracker.subjects

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.komissarov.tasktracker.App
import com.komissarov.tasktracker.R
import com.komissarov.tasktracker.data.network.entities.SubjectList
import com.komissarov.tasktracker.subjects.recycler.SubjectsAdapter
import com.komissarov.tasktracker.subjects.subjectdetails.SubjectDetailsFragment.Companion.ARG_SUBJ_ID
import com.komissarov.tasktracker.tasks.taskdetails.TaskDetailsFragment.Companion.ARG_TASK_ID
import javax.inject.Inject

class SubjectsFragment : Fragment(R.layout.fragment_subjects) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SubjectsViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subjectsRecycler: RecyclerView = view.findViewById(R.id.subjectsRecycler)
        val subjectsAdapter = SubjectsAdapter { subject -> openSubjectDetailFragment(subject) }
        subjectsRecycler.adapter = subjectsAdapter
        subjectsRecycler.layoutManager = LinearLayoutManager(requireContext())

        viewModel.subjectList.observe(viewLifecycleOwner) {
            subjectsAdapter.setSubjects(it)
        }

        viewModel.getAllSubjects()
    }

    private fun openSubjectDetailFragment(subject: SubjectList) {
        val bundle = bundleOf(ARG_SUBJ_ID to subject.id)
        findNavController().navigate(R.id.action_subjectsFragment_to_subjectDetailsFragment, bundle)
    }

}