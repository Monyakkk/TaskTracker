package com.komissarov.tasktracker.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.net.URI

class TaskFile (
    /**
     * Файл
     *
     *
     *
     * (Required)
     *
     */
    @SerializedName("file")
    @Expose
    val file: URI?,
)