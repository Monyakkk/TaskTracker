package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.net.URI

class FieldOfStudyList (
    /**
     * Count
     */
    @SerializedName("count")
    @Expose
    val count: Int?,
    /**
     * Next
     */
    @SerializedName("next")
    @Expose
    val next: URI?,
    /**
     * Previous
     */
    @SerializedName("previous")
    @Expose
    val previous: URI?,
    /**
     * Results
     */
    @SerializedName("results")
    @Expose
    val results: List<FieldOfStudy>?,
)