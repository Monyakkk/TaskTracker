package com.komissarov.tasktracker.login

import com.komissarov.tasktracker.data.network.ApiService
import com.komissarov.tasktracker.data.network.entities.EducationalProgram
import com.komissarov.tasktracker.data.network.entities.TokenObtainPairRequest
import com.komissarov.tasktracker.data.network.entities.UserCreate
import com.komissarov.tasktracker.data.network.entities.UserCreated
import com.komissarov.tasktracker.data.network.entities.FieldOfStudy
import com.komissarov.tasktracker.data.network.entities.ActivationCode
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getTokenObtainPair(email: String, password: String) =
        apiService.postTokenObtainPair(TokenObtainPairRequest(email, password))

    suspend fun postUser(
        educationalProgramId: Int,
        fieldOfStudyId: Int,
        firstName: String,
        lastName: String,
        middleName: String?,
        email: String,
        password: String,
    ): UserCreated = apiService.postUser(
        UserCreate(
            educationalProgramId = educationalProgramId,
            fieldOfStudyId = fieldOfStudyId,
            firstName = firstName,
            lastName = lastName,
            middleName = middleName,
            email = email,
            password = password,
        )
    )

    suspend fun getEducationalProgramsList(): List<EducationalProgram>? {
        return apiService.getEducationalPrograms().results

    }

    suspend fun getFieldsOfStudyList(): List<FieldOfStudy>? {
        return apiService.getFieldsOfStudy().results
    }

    suspend fun postActivationCode(code: String, email: String): ActivationCode =
        apiService.postActivationCode(ActivationCode(code, email))
}