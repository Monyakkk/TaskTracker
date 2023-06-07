package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Subject (
    /**
     *
     * (Required)
     *
     */
    @SerializedName("id")
    @Expose
    val id: Int?,
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
     * ФИО преподавателя
     *
     *
     *
     *
     */
    @SerializedName("teacher_name")
    @Expose
    val teacherName: String?,
    /**
     *
     * (Required)
     *
     */
    @SerializedName("assessment_type")
    @Expose
    val assessmentType: String?,
    /**
     * Дополнительная информация
     *
     *
     *
     *
     */
    @SerializedName("additional_info")
    @Expose
    val additionalInfo: String?,
    /**
     * Контакты преподавателя
     *
     *
     * Несколько значений можно разделить запятой
     *
     */
    @SerializedName("teacher_contacts")
    @Expose
    val teacherContacts: List<String>?,
)