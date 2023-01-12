package com.komissarov.tasktracker.mainpage

import androidx.lifecycle.ViewModel
import com.komissarov.tasktracker.data.network.SessionManager
import javax.inject.Inject

class MainViewModel @Inject constructor(val sessionManager: SessionManager) : ViewModel() {

    fun getLogged() = true
}