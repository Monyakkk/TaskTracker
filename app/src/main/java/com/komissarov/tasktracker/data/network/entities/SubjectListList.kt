package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.net.URI

class SubjectListList (
    @SerializedName("count")
    @Expose
    val count: Int?,

    @SerializedName("next")
    @Expose
    val next: URI?,

    @SerializedName("previous")
    @Expose
    val previous: URI?,

    @SerializedName("results")
    @Expose
    val results: List<SubjectList>?,
)