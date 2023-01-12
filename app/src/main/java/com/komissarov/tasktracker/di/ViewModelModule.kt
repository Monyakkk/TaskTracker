package com.komissarov.tasktracker.di

import androidx.lifecycle.ViewModel
import com.komissarov.tasktracker.mainpage.MainViewModel
import com.komissarov.tasktracker.subjects.SubjectsViewModel
import com.komissarov.tasktracker.tasks.TasksViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SubjectsViewModel::class)
    abstract fun bindSubjectsViewModel(viewModel: SubjectsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TasksViewModel::class)
    abstract fun bindTasksViewModel(viewModel: TasksViewModel): ViewModel
}