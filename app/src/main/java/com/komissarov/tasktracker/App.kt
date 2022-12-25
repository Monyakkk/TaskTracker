package com.komissarov.tasktracker

import android.app.Application
import com.komissarov.tasktracker.di.AppComponent
import com.komissarov.tasktracker.di.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

open class App : Application() {

    val appComponent: AppComponent by lazy { initializeComponent() }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
    }
}