package com.komissarov.tasktracker.mainpage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.komissarov.tasktracker.R
import com.komissarov.tasktracker.subjects.SubjectsFragment
import com.komissarov.tasktracker.tasks.TasksFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        val tabTask = tabLayout.newTab()
            .setText("Задания")
            .setId(0)
            .setIcon(R.drawable.tasks)

        val tabSubjects = tabLayout.newTab()
            .setText("Предметы")
            .setId(1)
            .setIcon(R.drawable.note)

        val taskFragment = TasksFragment()
        val subjectFragment = SubjectsFragment()
        tabLayout.addTab(tabTask)
        tabLayout.addTab(tabSubjects)

        val fragmentManager = supportFragmentManager

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.id) {
                    0 -> {
                        fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, taskFragment, TAG_TASKS)
                            .commit()
                    }
                    1 -> {
                        fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, subjectFragment, TAG_SUBJECTS)
                            .commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        //todo костыль
        tabLayout.selectTab(tabTask, false)
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, taskFragment, TAG_TASKS)
            .commit()
    }

    companion object {
        private const val TAG_TASKS = "TAG_TASKS"
        private const val TAG_SUBJECTS = "TAG_SUBJECTS"
    }
}