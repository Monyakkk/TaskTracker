package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class TaskDetail(
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
    val title: String?,
    /**
     *
     * (Required)
     *
     */
    @SerializedName("subject")
    @Expose
    val subject: SubjectTitle?,
    /**
     * Срок выполнения
     *
     *
     *
     *
     */
    @SerializedName("deadline_at")
    @Expose
    val deadlineAt: Date?,
    /**
     * Время создания
     *
     *
     *
     * (Required)
     *
     */
    @SerializedName("created_at")
    @Expose
    val createdAt: Date?,
    /**
     * Время обновления
     *
     *
     *
     * (Required)
     *
     */
    @SerializedName("updated_at")
    @Expose
    val updatedAt: Date?,
    /**
     * Описание
     *
     *
     *
     *
     */
    @SerializedName("description")
    @Expose
    val description: String?,
    /**
     * Ссылки
     *
     *
     *
     *
     */
    @SerializedName("links")
    @Expose
    val links: String?,
    /**
     *
     * (Required)
     *
     */
    @SerializedName("files")
    @Expose
    val files: List<TaskFile>?,
    /**
     *
     * (Required)
     *
     */
    @SerializedName("status")
    @Expose
    val status: String?,
)

enum class TaskStatus(val value: String) {
    TODO("todo"),
    COMPLETED("completed")
}