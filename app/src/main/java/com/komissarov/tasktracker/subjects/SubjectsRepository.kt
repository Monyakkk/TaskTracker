package com.komissarov.tasktracker.subjects

import com.komissarov.tasktracker.data.network.ApiService
import com.komissarov.tasktracker.data.network.entities.Subject
import com.komissarov.tasktracker.data.network.entities.SubjectList
import javax.inject.Inject

class SubjectsRepository @Inject constructor(private val service: ApiService) {

    suspend fun getSubjects(): List<SubjectList>? {
        return service.getSubjects().results
    }

    suspend fun getSubject(id: Int): Subject {
        return service.getSubject(id)
    }
}