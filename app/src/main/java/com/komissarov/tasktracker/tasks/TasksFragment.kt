package com.komissarov.tasktracker.tasks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.komissarov.tasktracker.App
import com.komissarov.tasktracker.R
import com.komissarov.tasktracker.databinding.FragmentTaskDetailsBinding
import com.komissarov.tasktracker.databinding.FragmentTasksBinding
import com.komissarov.tasktracker.tasks.recycler.TasksAdapter
import com.komissarov.tasktracker.tasks.recycler.TasksListItem
import com.komissarov.tasktracker.tasks.taskdetails.TaskDetailsFragment.Companion.ARG_TASK_ID
import java.time.Month
import java.time.format.TextStyle
import java.util.*
import javax.inject.Inject

class TasksFragment : Fragment(R.layout.fragment_tasks) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<TasksViewModel> { viewModelFactory }
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout: TabLayout = view.findViewById(R.id.tasksTabLayout)
        val tasksRecycler: RecyclerView = view.findViewById(R.id.tasksRecycler)
        val todayTitle: TextView = view.findViewById(R.id.todayTitle)
        val tasksAdapter = TasksAdapter { task -> openTaskDetailFragment(task) }

        tasksRecycler.adapter = tasksAdapter
        tasksRecycler.layoutManager = LinearLayoutManager(requireContext())

        val rawDate = Date(System.currentTimeMillis())
        todayTitle.text = "Сегодня - " + rawDate.date.toString() + " " + Month.of(rawDate.month + 1)
            .getDisplayName(TextStyle.FULL, Locale("ru"))

        val todoTab = tabLayout.newTab()
            .setId(0)
            .setCustomView(R.layout.item_tab_todo)

        val completedTab = tabLayout.newTab()
            .setId(1)
            .setCustomView(R.layout.item_tab_completed)

        tabLayout.addTab(todoTab)
        tabLayout.addTab(completedTab)

        viewModel.taskList.observe(viewLifecycleOwner) {
            tasksAdapter.setTasks(it)
        }

        binding.addTaskIcon.setOnClickListener {
            findNavController().navigate(R.id.action_tasksFragment_to_taskCreationFragment)
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.id) {
                    0 -> {
                        viewModel.getAllTasks("todo")
                    }
                    1 -> {
                        viewModel.getAllTasks("completed")
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        tabLayout.selectTab(tabLayout.getTabAt(1))
    }

    private fun openTaskDetailFragment(task: TasksListItem.Task) {
        val bundle = bundleOf(ARG_TASK_ID to task.id)
        findNavController().navigate(R.id.action_tasksFragment_to_taskDetailFragment, bundle)
    }

}