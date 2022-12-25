package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StudyGroupCode (
    /**
     *
     * (Required)
     *
     */
    @SerializedName("id")
    @Expose
    val id: Int?,
    /**
     * Код группы
     *
     *
     *
     * (Required)
     *
     */
    @SerializedName("code")
    @Expose
    val code: String?,
)