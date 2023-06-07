package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SubjectList (
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
     * ФИО преподавателя
     */
    @SerializedName("teacher_name")
    @Expose
    val teacherName: String?,
    /**
     * (Required)
     */
    @SerializedName("assessment_type")
    @Expose
    val assessmentType: String,
)