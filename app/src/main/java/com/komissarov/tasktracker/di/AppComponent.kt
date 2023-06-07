package com.komissarov.tasktracker.di

import android.content.Context
import com.komissarov.tasktracker.splash.SplashActivity
import com.komissarov.tasktracker.login.LoginActivity
import com.komissarov.tasktracker.login.login.LoginFragment
import com.komissarov.tasktracker.login.register.RegisterFragment
import com.komissarov.tasktracker.login.WelcomeFragment
import com.komissarov.tasktracker.login.confirm.ConfirmationFragment
import com.komissarov.tasktracker.mainpage.MainActivity
import com.komissarov.tasktracker.subjects.SubjectsFragment
import com.komissarov.tasktracker.subjects.subjectdetails.SubjectDetailsFragment
import com.komissarov.tasktracker.tasks.TasksFragment
import com.komissarov.tasktracker.tasks.taskcreation.TaskCreationFragment
import com.komissarov.tasktracker.tasks.taskdetails.TaskDetailsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelBuilderModule::class,
        NetworkModule::class,
        ViewModelModule::class,
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(activity: SplashActivity)

    fun inject(activity: MainActivity)

    fun inject(activity: LoginActivity)

    fun inject(fragment: TasksFragment)

    fun inject(fragment: TaskDetailsFragment)

    fun inject(fragment: WelcomeFragment)

    fun inject(fragment: RegisterFragment)

    fun inject(fragment: ConfirmationFragment)

    fun inject(fragment: LoginFragment)

    fun inject(fragment: SubjectsFragment)

    fun inject(fragment: SubjectDetailsFragment)

    fun inject(fragment: TaskCreationFragment)
}