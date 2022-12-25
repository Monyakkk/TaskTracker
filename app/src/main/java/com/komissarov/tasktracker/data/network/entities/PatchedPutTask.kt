package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class PatchedPutTask (
    @SerializedName("id")
    @Expose
    val id: Int?,
    /**
     * Название
     *
     *
     *
     *
     */
    /**
     * Название
     *
     *
     *
     *
     */
    /**
     * Название
     *
     *
     *
     *
     */
    @SerializedName("title")
    @Expose
    val title: String?,
    /**
     * Предмет
     *
     *
     *
     *
     */
    /**
     * Предмет
     *
     *
     *
     *
     */
    /**
     * Предмет
     *
     *
     *
     *
     */
    @SerializedName("subject")
    @Expose
    val subject: Int?,
    /**
     * Срок выполнения
     *
     *
     *
     *
     */
    /**
     * Срок выполнения
     *
     *
     *
     *
     */
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
     * Описание
     *
     *
     *
     *
     */
    /**
     * Описание
     *
     *
     *
     *
     */
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
    /**
     * Ссылки
     *
     *
     *
     *
     */
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
)