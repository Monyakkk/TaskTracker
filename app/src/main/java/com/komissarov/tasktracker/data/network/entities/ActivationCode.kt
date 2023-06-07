package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ActivationCode(
    @SerializedName("code")
    @Expose
    val code: String,
    @SerializedName("email")
    @Expose
    val email: String,
)