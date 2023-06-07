package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EducationalProgram (
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
     * Год поступления
     * (Required)
     */
    @SerializedName("enrollment_year")
    @Expose
    val enrollment_year: Int?,
)