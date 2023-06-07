package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SetPassword(
    /**
     *
     * (Required)
     *
     */
    @SerializedName("new_password")
    @Expose
    val newPassword: String?,
    /**
     *
     * (Required)
     *
     */
    @SerializedName("current_password")
    @Expose
    val currentPassword: String?,
)