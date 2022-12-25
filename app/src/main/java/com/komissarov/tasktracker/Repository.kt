package com.komissarov.tasktracker

import com.komissarov.tasktracker.data.network.ApiService
import com.komissarov.tasktracker.data.network.entities.TokenObtainPair
import com.komissarov.tasktracker.data.network.entities.TokenObtainPairRequest
import com.komissarov.tasktracker.data.network.entities.TokenRefresh
import com.komissarov.tasktracker.data.network.entities.TokenVerify
import com.komissarov.tasktracker.data.network.entities.UserCreate
import com.komissarov.tasktracker.data.network.entities.ActivationCode
import com.komissarov.tasktracker.data.network.entities.SubjectListList
import com.komissarov.tasktracker.data.network.entities.TaskListList
import com.komissarov.tasktracker.data.network.entities.StudyGroupCodeList
import com.komissarov.tasktracker.data.network.entities.UserList
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService)
{

    suspend fun getTokenObtainPair(loginRequest: TokenObtainPairRequest): TokenObtainPair {
        return apiService.postTokenObtainPair(loginRequest)
    }

    suspend fun verifyToken(tokenVerify: TokenVerify) {
        return apiService.postTokenVerify(tokenVerify)
    }

    suspend fun refreshToken(tokenRefresh: TokenRefresh): TokenRefresh {
        return apiService.postTokenRefresh(tokenRefresh)
    }

    suspend fun getUsers(): UserList {
        return apiService.getUsers()
    }

    suspend fun postUser(user: UserCreate): UserCreate {
        return apiService.postUser(user)
    }

    suspend fun postActivationCode(activationCode: ActivationCode): ActivationCode {
        return apiService.postActivationCode(activationCode)
    }

    suspend fun getSubjects(): SubjectListList {
        return apiService.getSubjects()
    }

    suspend fun getTasks(): TaskListList {
        return apiService.getTasks()
    }

    suspend fun getStudyGroups(): StudyGroupCodeList {
        return apiService.getStudyGroups()
    }
}