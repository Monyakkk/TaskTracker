package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class PutTask (
    /**
     * (Required)
     */
    @SerializedName("id")
    @Expose
    val id: Int?,
    /**
     * Название
     * (Required)
     */
    @SerializedName("title")
    @Expose
    val title: String?,
    /**
     * Предмет
     */
    @SerializedName("subject")
    @Expose
    val subject: Int?,
    /**
     * Срок выполнения
     */
    @SerializedName("deadline_at")
    @Expose
    val deadlineAt: String?,
    /**
     * Описание
     */
    @SerializedName("description")
    @Expose
    val description: String?,
    /**
     * Ссылки
     */
    @SerializedName("links")
    @Expose
    val links: String?,
)