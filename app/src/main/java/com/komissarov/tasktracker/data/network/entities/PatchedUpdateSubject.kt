package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PatchedUpdateSubject (
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
     * ФИО преподавателя
     *
     *
     *
     *
     */
    /**
     * ФИО преподавателя
     *
     *
     *
     *
     */
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
     * Вид контроля
     *
     *
     *
     *
     */
    /**
     * Вид контроля
     *
     *
     *
     *
     */
    /**
     * Вид контроля
     *
     *
     *
     *
     */
    @SerializedName("assessment_type")
    @Expose
    val assessmentType: com.komissarov.tasktracker.data.network.entities.AssessmentTypeEnum?,
    /**
     * Дополнительная информация
     *
     *
     *
     *
     */
    /**
     * Дополнительная информация
     *
     *
     *
     *
     */
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
    /**
     * Контакты преподавателя
     *
     *
     * Несколько значений можно разделить запятой
     *
     */
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