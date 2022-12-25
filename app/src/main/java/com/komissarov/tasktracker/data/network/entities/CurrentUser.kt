package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class CurrentUser (
    /**
     *
     * (Required)
     *
     */
    /**
     *
     * (Required)
     *
     */
    /**
     *
     * (Required)
     *
     */
    @SerializedName("study_group")
    @Expose
    val studyGroup: Any?,
    /**
     * Имя
     *
     *
     *
     * (Required)
     *
     */
    /**
     * Имя
     *
     *
     *
     * (Required)
     *
     */
    /**
     * Имя
     *
     *
     *
     * (Required)
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
    /**
     *
     * (Required)
     *
     */
    /**
     *
     * (Required)
     *
     */
    /**
     *
     * (Required)
     *
     */
    @SerializedName("id")
    @Expose
    val id: Int?,
    /**
     * Адрес электронной почты
     *
     *
     *
     * (Required)
     *
     */
    /**
     * Адрес электронной почты
     *
     *
     *
     * (Required)
     *
     */
    /**
     * Адрес электронной почты
     *
     *
     *
     * (Required)
     *
     */
    @SerializedName("email")
    @Expose
    val email: String?,
)