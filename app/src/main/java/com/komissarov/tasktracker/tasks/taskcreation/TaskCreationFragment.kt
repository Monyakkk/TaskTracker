package com.komissarov.tasktracker.tasks.taskcreation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.komissarov.tasktracker.data.network.entities.PostTask
import com.komissarov.tasktracker.databinding.FragmentTaskCreationBinding
import com.komissarov.tasktracker.login.check
import timber.log.Timber
import java.text.SimpleDateFormat
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

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.subjectList.observe(viewLifecycleOwner) { subjectsList ->
            for (item in subjectsList) {
                subjectsMap[item.id] = item.title
                subjectsArray.add(item.title)
            }
        }
        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, subjectsArray)
        binding.taskCreationSubject.setAdapter(arrayAdapter)
        binding.taskCreationBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.taskCreationSaveButton.setOnClickListener {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            format.timeZone = TimeZone.getTimeZone("GMT")
            val subjectId = subjectsMap.entries.find { it.value == binding.taskCreationSubject.text.toString() }?.key
            if (!binding.taskCreationTime.text.matches(Regex("([01]?[0-9]|2[0-3]):[0-5][0-9]"))) {
                Toast.makeText(requireContext(), "Неверно введено время", Toast.LENGTH_SHORT).show()
            } else if (!binding.taskCreationDate.text.matches(Regex("([0-2][0-9]||3[0-1])[.](0[0-9]||1[0-2])[.][0-9][0-9][0-9][0-9]"))) {
                Toast.makeText(requireContext(), "Неверно введена дата", Toast.LENGTH_SHORT).show()
            } else if (binding.taskCreationTaskName.text.check() &&
                subjectId != null
            ) {
                val simpledatetime = SimpleDateFormat("dd.MM.yyyy-hh:mm").parse(
                    binding.taskCreationDate.text.toString() +
                            "-" + binding.taskCreationTime.text.toString()
                )
                viewModel.postTask(
                    PostTask(
                        subject = subjectId,
                        title = binding.taskCreationTaskName.text.toString(),
                        deadlineAt = format.format(simpledatetime!!),
                        description = binding.taskCreationDescription.text.toString(),
                        links = binding.taskCreationLinks.text.toString()
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
    }
}