package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SubjectTitle (
    /**
     *
     * (Required)
     *
     */
    @SerializedName("id")
    @Expose
    val id: Int,
    /**
     * Название
     *
     *
     *
     * (Required)
     *
     */
    @SerializedName("title")
    @Expose
    val title: String,
)