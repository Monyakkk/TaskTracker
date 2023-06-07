package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserCreate(
    /**
     * Образовательная программа
     */
    @SerializedName("educational_program")
    @Expose
    val educationalProgramId: Int?,
    /**
     * Направление подготовки
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
    val firstName: String,
    /**
     * Фамилия
     */
    @SerializedName("last_name")
    @Expose
    val lastName: String,
    /**
     * Отчество
     */
    @SerializedName("middle_name")
    @Expose
    val middleName: String?,
    /**
     * Адрес электронной почты
     * (Required)
     */
    @SerializedName("email")
    @Expose
    val email: String,
    /**
     *
     * (Required)
     *
     */
    @SerializedName("password")
    @Expose
    val password: String?,
)

