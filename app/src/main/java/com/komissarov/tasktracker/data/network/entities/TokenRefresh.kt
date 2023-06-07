package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TokenRefresh (
    /**
     *
     * (Required)
     *
     */
    @SerializedName("access")
    @Expose
    val access: String?,
    /**
     *
     * (Required)
     *
     */
    @SerializedName("refresh")
    @Expose
    val refresh: String?,
)