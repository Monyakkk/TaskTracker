package com.komissarov.tasktracker.di

import androidx.lifecycle.ViewModel
import com.komissarov.tasktracker.login.confirm.ConfirmationViewModel
import com.komissarov.tasktracker.login.login.LoginViewModel
import com.komissarov.tasktracker.login.register.RegisterViewModel
import com.komissarov.tasktracker.splash.SplashViewModel
import com.komissarov.tasktracker.subjects.subjectdetails.SubjectDetailsViewModel
import com.komissarov.tasktracker.subjects.SubjectsViewModel
import com.komissarov.tasktracker.tasks.TasksViewModel
import com.komissarov.tasktracker.tasks.taskcreation.TaskCreationViewModel
import com.komissarov.tasktracker.tasks.taskdetails.TaskDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(viewModel: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ConfirmationViewModel::class)
    abstract fun bindConfirmationViewModel(viewModel: ConfirmationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SubjectsViewModel::class)
    abstract fun bindSubjectsViewModel(viewModel: SubjectsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SubjectDetailsViewModel::class)
    abstract fun bindSubjectDetailsViewModel(viewModel: SubjectDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TasksViewModel::class)
    abstract fun bindTasksViewModel(viewModel: TasksViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TaskDetailViewModel::class)
    abstract fun bindTaskDetailViewModel(viewModel: TaskDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TaskCreationViewModel::class)
    abstract fun bindTaskCreationViewModel(viewModel: TaskCreationViewModel): ViewModel

}