package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class PostTask (
    @SerializedName("title")
    @Expose
    var title: String?,
    /**
     * Предмет
     */
    @SerializedName("subject")
    @Expose
    var subject: Int?,
    /**
     * Срок выполнения
     */
    @SerializedName("deadline_at")
    @Expose
    var deadlineAt: String?,
    /**
     * Описание
     */
    @SerializedName("description")
    @Expose
    var description: String?,
    /**
     * Ссылки
     */
    @SerializedName("links")
    @Expose
    var links: String?,
)