package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PatchedUser (
    /**
     * Учебная группа
     *
     *
     *
     *
     */
    /**
     * Учебная группа
     *
     *
     *
     *
     */
    /**
     * Учебная группа
     *
     *
     *
     *
     */
    @SerializedName("study_group")
    @Expose
    val studyGroup: Int?,
    /**
     * Имя
     *
     *
     *
     *
     */
    /**
     * Имя
     *
     *
     *
     *
     */
    /**
     * Имя
     *
     *
     *
     *
     */
    @SerializedName("first_name")
    @Expose
    val firstName: String?,
    /**
     * Фамилия
     *
     *
     *
     *
     */
    /**
     * Фамилия
     *
     *
     *
     *
     */
    /**
     * Фамилия
     *
     *
     *
     *
     */
    @SerializedName("last_name")
    @Expose
    val lastName: String?,
    /**
     * Отчество
     *
     *
     *
     *
     */
    /**
     * Отчество
     *
     *
     *
     *
     */
    /**
     * Отчество
     *
     *
     *
     *
     */
    @SerializedName("middle_name")
    @Expose
    val middleName: String?,

    @SerializedName("id")
    @Expose
    val id: Int?,
    /**
     * Адрес электронной почты
     *
     *
     *
     *
     */
    /**
     * Адрес электронной почты
     *
     *
     *
     *
     */
    /**
     * Адрес электронной почты
     *
     *
     *
     *
     */
    @SerializedName("email")
    @Expose
    val email: String?,
)