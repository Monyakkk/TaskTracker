package com.komissarov.tasktracker.tasks.taskcreation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.komissarov.tasktracker.App
import com.komissarov.tasktracker.R
import com.komissarov.tasktracker.data.network.entities.PostTask
import com.komissarov.tasktracker.databinding.FragmentRegisterBinding
import com.komissarov.tasktracker.databinding.FragmentTaskCreationBinding
import com.komissarov.tasktracker.tasks.TasksRepository
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class TaskCreationFragment : Fragment(R.layout.fragment_task_creation) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var subjectsArray: ArrayList<String> = ArrayList()
    private var subjectsMap: MutableMap<Int, String> = mutableMapOf()
    private val viewModel by viewModels<TaskCreationViewModel> { viewModelFactory }
    private var _binding: FragmentTaskCreationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskCreationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.subjectList.observe(viewLifecycleOwner) { subjectsList ->
            for (item in subjectsList) {
                subjectsMap[item.id] = item.title
                subjectsArray.add(item.title)
            }
        }

        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, subjectsArray)
        binding.taskCreationSubject.setAdapter(arrayAdapter)
        binding.taskCreationBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.taskCreationSaveButton.setOnClickListener {

            val simpledatetime = SimpleDateFormat("dd.MM.yyyy-hh:mm").parse(
                binding.taskCreationDate.text.toString() +
                        "-" + binding.taskCreationTime.text.toString())
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

            val task = PostTask(
            subject = subjectsMap.entries.find { it.value == binding.taskCreationSubject.text.toString() }?.key,
            title = binding.taskCreationTaskName.text.toString(),
            deadlineAt = format.format(simpledatetime!!) ,
            description = binding.taskCreationDescription.text.toString(),
            links = binding.taskCreationLinks.text.toString()
            )
            viewModel.postTask(task)
        }

        viewModel.getSubjects()
    }

    companion object {
        const val ARG_TASK_ID = "taskId"
    }
}