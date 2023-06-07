package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SetUsername (
    /**
     *
     * (Required)
     *
     */
    @SerializedName("current_password")
    @Expose
    val currentPassword: String?,
    /**
     * Адрес электронной почты
     *
     *
     *
     * (Required)
     *
     */
    @SerializedName("new_email")
    @Expose
    val newEmail: String?,
)