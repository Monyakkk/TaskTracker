package com.komissarov.tasktracker.tasks.taskdetails

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.komissarov.tasktracker.App
import com.komissarov.tasktracker.R
import com.komissarov.tasktracker.databinding.FragmentRegisterBinding
import com.komissarov.tasktracker.databinding.FragmentTaskDetailsBinding
import timber.log.Timber
import java.text.SimpleDateFormat
import javax.inject.Inject

class TaskDetailsFragment : Fragment(R.layout.fragment_task_details) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<TaskDetailViewModel> { viewModelFactory }
    private var _binding: FragmentTaskDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskId = requireArguments().getInt(ARG_TASK_ID, -1)

        if (taskId == -1)
            return

        viewModel.task.observe(viewLifecycleOwner) { detail ->
            detail.title?.run {
                binding.taskTitle.text = this
            }
            detail.subject?.run {
                binding.taskSubjectContainer.visibility = View.VISIBLE
                binding.taskSubjectTitle.text = this.title
            }
            detail.deadlineAt?.run {
                val timeFormat = SimpleDateFormat("HH:mm")
                val dateFormat = SimpleDateFormat("dd.MM.yyyy")
                binding.taskDeadlineDateContainer.visibility = View.VISIBLE
                binding.taskDeadlineTimeContainer.visibility = View.VISIBLE

                binding.taskDeadlineDateTitle.text = dateFormat.format(this)
                binding.taskDeadlineTimeTitle.text = timeFormat.format(this)
            }
            detail.description.takeIf { !it.isNullOrEmpty() }.run {
                binding.taskDescriptionContainer.visibility = View.VISIBLE
                binding.taskDescriptionTitle.text = this
            }
            detail.links.takeIf { !it.isNullOrEmpty() }.run {
                binding.taskLinksContainer.visibility = View.VISIBLE
                binding.taskLinksTitle.text = this
            }
            detail.status?.run {
                binding.taskTypeContainer.visibility = View.VISIBLE
                binding.taskTypeSwitch.isChecked = this == "completed"
                binding.taskTypeSwitch.setOnCheckedChangeListener { _, isChecked ->
                    viewModel.setStatus(detail.id, isChecked)
                }
            }
        }
        binding.taskDetailsBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.taskDetailsDelete.setOnClickListener {
            val showPopUp = TaskDeleteDialogFragment()
            showPopUp.arguments = bundleOf(ARG_TASK_ID to taskId)
            showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
        }

        binding.taskDetailsUpdate.setOnClickListener {
            val bundle = bundleOf(
                ARG_TASK_ID to taskId,
                ARG_TASK_TITLE to binding.taskTitle.text,
                ARG_SUBJECT_NAME to binding.taskSubjectTitle.text,
                ARG_TASK_DEADLINE_DATE to binding.taskDeadlineDateTitle.text,
                ARG_TASK_DEADLINE_TIME to binding.taskDeadlineTimeTitle.text,
                ARG_TASK_DESCRIPTION to binding.taskDescriptionTitle.text,
                ARG_TASK_LINKS to binding.taskLinksTitle.text
            )
            findNavController().navigate(
                R.id.action_taskDetailFragment_to_taskUpdateFragment,
                bundle
            )
        }
        viewModel.getTaskDetails(taskId)
    }

    companion object {
        const val ARG_TASK_ID = "taskId"
        const val ARG_TASK_TITLE = "title"
        const val ARG_SUBJECT_NAME = "subj"
        const val ARG_TASK_DEADLINE_DATE = "date"
        const val ARG_TASK_DEADLINE_TIME = "time"
        const val ARG_TASK_DESCRIPTION = "description"
        const val ARG_TASK_LINKS = "links"
    }
}