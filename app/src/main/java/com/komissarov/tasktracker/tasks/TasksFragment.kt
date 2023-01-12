package com.komissarov.tasktracker.tasks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.komissarov.tasktracker.R
import javax.inject.Inject

class TasksFragment : Fragment(R.layout.fragment_tasks) {

    @Inject
    lateinit var viewModel: TasksViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout: TabLayout = view.findViewById(R.id.tasksTabLayout)
        val tasksRecycler: RecyclerView = view.findViewById(R.id.tasksRecycler)
        val tasksAdapter = TasksAdapter()
        tasksRecycler.adapter = tasksAdapter
        tasksRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        val todoTab = tabLayout.newTab()
            .setId(0)
            .setCustomView(R.layout.item_tab_todo)

        val completedTab = tabLayout.newTab()
            .setId(1)
            .setCustomView(R.layout.item_tab_completed)

        tabLayout.addTab(todoTab)
        tabLayout.addTab(completedTab)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.id) {
                    0 -> {
                        //фильтр
                    }
                    1 -> {
                        //фильтр
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        viewModel.tasks.observe(this.viewLifecycleOwner) {
            tasksAdapter.setTasks(it)//TODO
        }
    }

}