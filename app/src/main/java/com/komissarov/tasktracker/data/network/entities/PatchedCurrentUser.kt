package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PatchedCurrentUser(
    @SerializedName("study_group")
    @Expose
    val studyGroupCode: StudyGroupCode?,
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
    @SerializedName("email")
    @Expose
    val email: String?,
)