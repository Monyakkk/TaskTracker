package com.komissarov.tasktracker.data.network

import android.content.Context
import android.content.SharedPreferences
import com.komissarov.tasktracker.R
import com.komissarov.tasktracker.data.network.entities.TokenRefresh
import javax.inject.Inject

class SessionManager @Inject constructor(private val applicationContext: Context) {

    fun saveAuthToken(refresh: String, access: String) {
        saveString(REFRESH_TOKEN, refresh)
        saveString(ACCESS_TOKEN, access)
    }

    fun saveAccessToken(token: String) {
        saveString(ACCESS_TOKEN, token)
    }

    fun getRefreshToken(): String? {
        return getString(REFRESH_TOKEN)
    }

    fun getAccessToken(): String? {
        return getString(ACCESS_TOKEN)
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

    private fun getString(key: String): String? {
        val prefs: SharedPreferences =
            applicationContext.getSharedPreferences(
                applicationContext.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
        return prefs.getString(key, null)
    }

    companion object {
        const val REFRESH_TOKEN = "refresh_token"
        const val ACCESS_TOKEN = "access_token"
    }
}