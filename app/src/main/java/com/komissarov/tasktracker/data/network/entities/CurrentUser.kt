package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class CurrentUser (
    /**
     * Образовательная программа
     * (Required)
     */
    @SerializedName("educational_program")
    @Expose
    val educationalProgram: EducationalProgram,
    /**
     * (Required)
     */
    @SerializedName("field_of_study")
    @Expose
    val fieldOfStudy: FieldOfStudy,
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