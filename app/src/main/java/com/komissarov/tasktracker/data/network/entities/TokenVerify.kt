package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TokenVerify (
    /**
     *
     * (Required)
     *
     */
    @SerializedName("token")
    @Expose
    val token: String?,
)