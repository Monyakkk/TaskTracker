package com.komissarov.tasktracker.subjects.subjectdetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.komissarov.tasktracker.App
import com.komissarov.tasktracker.R
import com.komissarov.tasktracker.databinding.FragmentSubjectDetailsBinding
import javax.inject.Inject

class SubjectDetailsFragment : Fragment(R.layout.fragment_subject_details) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SubjectDetailsViewModel> { viewModelFactory }

    private var _binding: FragmentSubjectDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubjectDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val subjectId = requireArguments().getInt(ARG_SUBJ_ID, -1)
        if (subjectId == -1) return

        viewModel.subject.observe(viewLifecycleOwner) { detail ->
            detail.title?.run {
                binding.subjectTitle.text = this
            }
            detail.assessmentType?.run {
                binding.assessmentTypeContainer.visibility = View.VISIBLE
                binding.assessmentType.text = this
            }
            detail.teacherContacts?.run {
                val res = this.all { isNotEmpty() }.toString()
                if (res.isNotEmpty())
                    binding.teacherContactContainer.visibility = View.VISIBLE
                binding.teacherContacts.text = res
            }
            detail.teacherName?.run {
                if (this.isNotEmpty())
                    binding.teacherNameContainer.visibility = View.VISIBLE
                binding.teacherName.text = this
            }
            detail.additionalInfo?.run {
                if (this.isNotEmpty())
                    binding.additionalDescriptionContainer.visibility = View.VISIBLE
                binding.additionalDescription.text = this
            }
        }
        binding.subjectDetailsBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }
        viewModel.getSubjectDetails(subjectId)
    }

    companion object {

        const val ARG_SUBJ_ID = "subjectId"
    }
}