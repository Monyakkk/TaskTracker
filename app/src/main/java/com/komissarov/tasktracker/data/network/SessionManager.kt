package com.komissarov.tasktracker.data.network

import android.content.Context
import android.content.SharedPreferences
import com.komissarov.tasktracker.R
import javax.inject.Inject

class SessionManager @Inject constructor(private val applicationContext: Context) {

    fun saveAuthToken(token: String) {
        saveString(USER_TOKEN, token)
    }

    fun getToken(): String? {
        return getString()
    }

    fun clearData() {
        val editor =
            applicationContext.getSharedPreferences(
                applicationContext.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
                .edit()
        editor.clear()
        editor.apply()
    }

    private fun saveString(key: String, value: String) {
        val prefs: SharedPreferences =
            applicationContext.getSharedPreferences(
                applicationContext.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()

    }

    private fun getString(): String? {
        val prefs: SharedPreferences =
            applicationContext.getSharedPreferences(
                applicationContext.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
        return prefs.getString(USER_TOKEN, null)
    }

    companion object {
        const val USER_TOKEN = "user_token"
    }
}