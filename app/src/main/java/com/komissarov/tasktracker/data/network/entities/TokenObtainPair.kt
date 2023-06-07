package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TokenObtainPairRequest (
    /**
     *
     * (Required)
     *
     */
    @SerializedName("email")
    @Expose
    val email: String?,
    /**
     *
     * (Required)
     *
     */
    @SerializedName("password")
    @Expose
    val password: String?,
)

class TokenObtainPair(
    /**
     *
     * (Required)
     *
     */
    @SerializedName("access")
    @Expose
    val access: String,
    /**
     *
     * (Required)
     *
     */
    @SerializedName("refresh")
    @Expose
    val refresh: String,
)