package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FieldOfStudy (
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
    var title: String?,
    /**
     * Код программы
     * (Required)
     */
    @SerializedName("number")
    @Expose
    val number: String?,
)