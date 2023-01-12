package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class PasswordResetConfirm(
    /**
     *
     * (Required)
     *
     */
    @SerializedName("uid")
    @Expose
    val uid: String?,
    /**
     *
     * (Required)
     *
     */
    @SerializedName("token")
    @Expose
    val token: String?,
    /**
     *
     * (Required)
     *
     */
    @SerializedName("new_password")
    @Expose
    val newPassword: String?,
)