package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class TaskList(
    /**
     * (Required)
     */
    @SerializedName("id")
    @Expose
    val id: Int,
    /**
     * Название
     * (Required)
     */
    @SerializedName("title")
    @Expose
    val title: String,
    /**
     * (Required)
     */
    @SerializedName("subject")
    @Expose
    val subject: SubjectTitle,
    /**
     * Срок выполнения
     */
    @SerializedName("deadline_at")
    @Expose
    val deadlineAt: Date?,
)