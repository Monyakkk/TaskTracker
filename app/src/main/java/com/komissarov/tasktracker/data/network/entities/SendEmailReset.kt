package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SendEmailReset (
    /**
     *
     * (Required)
     *
     */
    @SerializedName("email")
    @Expose
    val email: String?,
)