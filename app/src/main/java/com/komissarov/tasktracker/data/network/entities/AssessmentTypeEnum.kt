package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AssessmentTypeEnum(
    @SerializedName("foo")
    @Expose
    val foo: String?,

    @SerializedName("bar")
    @Expose
    val bar: Int?,

    @SerializedName("baz")
    @Expose
    val baz: Boolean?,
)