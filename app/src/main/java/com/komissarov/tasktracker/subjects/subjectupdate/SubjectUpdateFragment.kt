package com.komissarov.tasktracker.subjects.subjectupdate

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.komissarov.tasktracker.App
import com.komissarov.tasktracker.R
import com.komissarov.tasktracker.data.network.entities.PutTask
import com.komissarov.tasktracker.data.network.entities.UpdateSubject
import com.komissarov.tasktracker.databinding.FragmentSubjectUpdateBinding
import com.komissarov.tasktracker.tasks.taskupdate.TaskUpdateFragment
import timber.log.Timber
import javax.inject.Inject

class SubjectUpdateFragment : Fragment(R.layout.fragment_subject_update) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<SubjectUpdateViewModel> { viewModelFactory }
    private var _binding: FragmentSubjectUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubjectUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val subjectId = requireArguments().getInt(ARG_SUBJ_ID, -1)
        val subjectTitle = requireArguments().getString(ARG_TITLE, "")
        val assessmentType = requireArguments().getString(ARG_ASSESMENT, "")
        with(binding){
            subjectUpdateTeacher.setText(requireArguments().getString(ARG_TEACHER, ""))
            subjectUpdateContacts.setText(requireArguments().getString(ARG_CONTACTS, ""))
            subjectUpdateDescription.setText(requireArguments().getString(ARG_DESCRIPTION, ""))
        }

        binding.subjectUpdateBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.subjectUpdateSaveButton.setOnClickListener {
            if (!subjectTitle.isNullOrEmpty() &&
                subjectId != -1 &&
                !assessmentType.isNullOrEmpty()
            ) {
                viewModel.updateSubject(
                    id = subjectId,
                    UpdateSubject(
                        id = subjectId,
                        title = subjectTitle,
                        teacherName = binding.subjectUpdateTeacher.text.toString(),
                        assessmentType = assessmentType,
                        additionalInfo = binding.subjectUpdateDescription.text.toString(),
                        teacherContacts = binding.subjectUpdateContacts.text.toString().split(",").toList()
                    )
                )
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Ошибка в введенных данных", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    companion object {
        const val ARG_SUBJ_ID = "subjId"
        const val ARG_TEACHER = "name"
        const val ARG_TITLE = "title"
        const val ARG_ASSESMENT = "type"
        const val ARG_DESCRIPTION = "desc"
        const val ARG_CONTACTS = "contact"
    }
}