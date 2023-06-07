package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PatchedUpdateSubject (
    @SerializedName("id")
    @Expose
    val id: Int?,
    /**
     * Название
     */
    @SerializedName("title")
    @Expose
    val title: String?,
    /**
     * ФИО преподавателя
     */
    @SerializedName("teacher_name")
    @Expose
    val teacherName: String?,
    /**
     * Вид контроля
     */
    @SerializedName("assessment_type")
    @Expose
    val assessmentType: AssessmentTypeEnum?,
    /**
     * Дополнительная информация
     */
    @SerializedName("additional_info")
    @Expose
    val additionalInfo: String?,
    /**
     * Контакты преподавателя
     * Несколько значений можно разделить запятой
     */
    @SerializedName("teacher_contacts")
    @Expose
    val teacherContacts: List<String>?,
)