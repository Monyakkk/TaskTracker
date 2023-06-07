package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User (
    /**
     * Образовательная программа
     * (Required)
     */
    @SerializedName("educational_program")
    @Expose
    val educationalProgramId: Int?,
    /**
     * Направление подготовки
     * (Required)
     */
    @SerializedName("field_of_study")
    @Expose
    val fieldOfStudyId: Int?,
    /**
     * Имя
     * (Required)
     */
    @SerializedName("first_name")
    @Expose
    val firstName: String?,
    /**
     * Фамилия
     */
    @SerializedName("last_name")
    @Expose
    val lastName: String?,
    /**
     * Отчество
     */
    @SerializedName("middle_name")
    @Expose
    val middleName: String?,
    /**
     * (Required)
     */
    @SerializedName("id")
    @Expose
    val id: Int?,
    /**
     * Адрес электронной почты
     * (Required)
     */
    @SerializedName("email")
    @Expose
    val email: String?,
)