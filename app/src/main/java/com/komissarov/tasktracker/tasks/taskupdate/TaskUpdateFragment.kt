package com.komissarov.tasktracker.tasks.taskupdate

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.komissarov.tasktracker.App
import com.komissarov.tasktracker.R
import com.komissarov.tasktracker.data.network.entities.PutTask
import com.komissarov.tasktracker.databinding.FragmentTaskUpdateBinding
import com.komissarov.tasktracker.login.check
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class TaskUpdateFragment : Fragment(R.layout.fragment_task_update) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var subjectsArray: ArrayList<String> = ArrayList()
    private var subjectsMap: MutableMap<Int, String> = mutableMapOf()
    private val viewModel by viewModels<TaskUpdateViewModel> { viewModelFactory }
    private var _binding: FragmentTaskUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val taskId = requireArguments().getInt(ARG_TASK_ID, -1)
        with(binding){
            taskUpdateSubject.setText(requireArguments().getString(ARG_SUBJECT_NAME, ""))
            taskUpdateTaskName.setText(requireArguments().getString(ARG_TASK_TITLE, ""))
            taskUpdateTime.setText(requireArguments().getString(ARG_TASK_DEADLINE_TIME, ""))
            taskUpdateDate.setText(requireArguments().getString(ARG_TASK_DEADLINE_DATE, ""))
            taskUpdateDescription.setText(requireArguments().getString(ARG_TASK_DESCRIPTION, ""))
            taskUpdateLinks.setText(requireArguments().getString(ARG_TASK_LINKS, ""))
        }

        viewModel.subjectList.observe(viewLifecycleOwner) { subjectsList ->
            for (item in subjectsList) {
                subjectsMap[item.id] = item.title
                subjectsArray.add(item.title)
            }
        }
        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, subjectsArray)
        binding.taskUpdateSubject.setAdapter(arrayAdapter)
        binding.taskUpdateBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.taskUpdateSaveButton.setOnClickListener {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            format.timeZone = TimeZone.getTimeZone("GMT")
            val subjectId = subjectsMap.entries.find { it.value == binding.taskUpdateSubject.text.toString() }?.key
            if (!binding.taskUpdateTime.text.matches(Regex("([01]?[0-9]|2[0-3]):[0-5][0-9]"))) {
                Toast.makeText(requireContext(), "Неверно введено время", Toast.LENGTH_SHORT).show()
            } else if (!binding.taskUpdateDate.text.matches(Regex("([0-2][0-9]||3[0-1])[.](0[0-9]||1[0-2])[.][0-9][0-9][0-9][0-9]"))) {
                Toast.makeText(requireContext(), "Неверно введена дата", Toast.LENGTH_SHORT).show()
            } else if (binding.taskUpdateTaskName.text.check() &&
                subjectId != null
            ) {
                val simpledatetime = SimpleDateFormat("dd.MM.yyyy-hh:mm").parse(
                    binding.taskUpdateDate.text.toString() +
                            "-" + binding.taskUpdateTime.text.toString()
                )
                viewModel.updateTask(
                    id = taskId,
                    PutTask(
                        id = taskId,
                        subject = subjectId,
                        title = binding.taskUpdateTaskName.text.toString(),
                        deadlineAt = format.format(simpledatetime!!),
                        description = binding.taskUpdateDescription.text.toString(),
                        links = binding.taskUpdateLinks.text.toString()
                    )
                )
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Ошибка в введенных данных", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        viewModel.getSubjects()
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