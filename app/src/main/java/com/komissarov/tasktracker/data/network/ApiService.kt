package com.komissarov.tasktracker.data.network

import com.komissarov.tasktracker.data.network.entities.TokenObtainPair
import com.komissarov.tasktracker.data.network.entities.TokenObtainPairRequest
import com.komissarov.tasktracker.data.network.entities.TokenRefresh
import com.komissarov.tasktracker.data.network.entities.TokenVerify
import com.komissarov.tasktracker.data.network.entities.User
import com.komissarov.tasktracker.data.network.entities.UserCreate
import com.komissarov.tasktracker.data.network.entities.ActivationCode
import com.komissarov.tasktracker.data.network.entities.CurrentUser
import com.komissarov.tasktracker.data.network.entities.SendEmailReset
import com.komissarov.tasktracker.data.network.entities.UsernameResetConfirm
import com.komissarov.tasktracker.data.network.entities.PasswordResetConfirm
import com.komissarov.tasktracker.data.network.entities.SetUsername
import com.komissarov.tasktracker.data.network.entities.SetPassword
import com.komissarov.tasktracker.data.network.entities.SubjectListList
import com.komissarov.tasktracker.data.network.entities.Subject
import com.komissarov.tasktracker.data.network.entities.UpdateSubject
import com.komissarov.tasktracker.data.network.entities.PatchedUpdateSubject
import com.komissarov.tasktracker.data.network.entities.TaskListList
import com.komissarov.tasktracker.data.network.entities.PutTask
import com.komissarov.tasktracker.data.network.entities.TaskDetail
import com.komissarov.tasktracker.data.network.entities.PatchedPutTask
import com.komissarov.tasktracker.data.network.entities.TaskDateGroup
import com.komissarov.tasktracker.data.network.entities.StudyGroupCodeList
import com.komissarov.tasktracker.data.network.entities.UserList
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.PUT
import retrofit2.http.PATCH
import retrofit2.http.DELETE


interface ApiService {

    //Получение JWT для авторизации запросов.
    @POST("/api/auth/jwt/create/")
    suspend fun postTokenObtainPair(@Body tokenObtainPairRequest: TokenObtainPairRequest): TokenObtainPair

    //Takes a refresh type JSON web token and returns an access type JSON web token if the refresh token is valid.
    @POST("/api/auth/jwt/refresh/")
    suspend fun postTokenRefresh(@Body tokenRefresh: TokenRefresh): TokenRefresh

    //Takes a token and indicates if it is valid. This view provides no information about a token's fitness for a particular use.
    @POST("/api/auth/jwt/verify/")
    suspend fun postTokenVerify(@Body tokenOVerify: TokenVerify)

    //---------------------------------------------------------
    @Headers("NeedAuth: true")
    @GET("/api/auth/users/")
    suspend fun getUsers(@Query("limit") limit: Int? = null, @Query("offset") offset: Int? = null): UserList

    @Headers("NeedAuth: true")
    @POST("/api/auth/users/")
    suspend fun postUser(@Body userCreate: UserCreate): UserCreate

    @Headers("NeedAuth: true")
    @GET("/api/auth/users/")
    suspend fun getUser(@Query("id") id: Int): User

    @Headers("NeedAuth: true")
    @PUT("/api/auth/users/")
    suspend fun putUser(
        @Query("id") id: Int,
        @Body user: User
    ): User

    @Headers("NeedAuth: true")
    @PATCH("/api/auth/users/")
    suspend fun patchUser(
        @Query("id") id: Int,
        @Body user: User
    ): User

    @Headers("NeedAuth: true")
    @DELETE("/api/auth/users/")
    suspend fun deleteUser(@Query("id") id: Int)

    //---------------------------------------------------------
    //Активация аккаунта пользователя
    @POST("/api/auth/jwt/verify/")
    suspend fun postActivationCode(@Body activationCode: ActivationCode): ActivationCode

    //---------------------------------------------------------
    @Headers("NeedAuth: true")
    @GET("/api/auth/users/me")
    suspend fun getMe(): CurrentUser

    @Headers("NeedAuth: true")
    @PUT("/api/auth/users/me")
    suspend fun putMe(@Body currentUser: CurrentUser): CurrentUser

    @Headers("NeedAuth: true")
    @PATCH("/api/auth/users/me")
    suspend fun patchMe(@Body currentUser: CurrentUser): CurrentUser

    @Headers("NeedAuth: true")
    @DELETE("/api/auth/users/me")
    suspend fun deleteMe()
    //---------------------------------------------------------

    @POST("/api/auth/users/resend_activation/")
    suspend fun postResendActivation(@Body sendEmailReset: SendEmailReset)

    @POST("/api/auth/users/reset_email/")
    suspend fun postSendEmailReset(@Body sendEmailReset: SendEmailReset)

    @POST("/api/auth/users/reset_email_confirm/")
    suspend fun postSendEmailReset(@Body usernameResetConfirm: UsernameResetConfirm)

    @POST("/api/auth/users/reset_password/")
    suspend fun postResetPassword(@Body sendEmailReset: SendEmailReset)

    @POST("/api/auth/users/reset_password_confirm/")
    suspend fun postResetPasswordConfirm(@Body sendEmailReset: PasswordResetConfirm)

    @POST("/api/auth/users/set_email/")
    suspend fun postSetUsername(@Body setUsername: SetUsername): SetUsername

    @POST("/api/auth/users/set_email/")
    suspend fun postSetPassword(@Body setPassword: SetPassword): SetPassword
    //---------------------------------------------------------

    //CRUD для учебных дисциплин.
    @Headers("NeedAuth: true")
    @GET("/api/subjects/")
    suspend fun getSubjects(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("title") title: String? = null,
    ): SubjectListList

    @Headers("NeedAuth: true")
    @GET("/api/subjects/")
    suspend fun getSubject(@Query("id") id: Int): Subject

    @Headers("NeedAuth: true")
    @PUT("/api/subjects/")
    suspend fun putSubject(@Body subject: UpdateSubject): UpdateSubject

    @Headers("NeedAuth: true")
    @PATCH("/api/subjects/")
    suspend fun patchSubject(@Body subject: PatchedUpdateSubject): PatchedUpdateSubject
    //---------------------------------------------------------

    @Headers("NeedAuth: true")
    @GET("/api/tasks/")
    suspend fun getTasks(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("title") title: String? = null,
        @Query("status") status: String? = null,
        @Query("subject") subjectId: Int? = null,
    ): TaskListList

    @Headers("NeedAuth: true")
    @POST("/api/tasks/")
    suspend fun postTask(@Body putTask: PutTask): PutTask

    @Headers("NeedAuth: true")
    @GET("/api/tasks/")
    suspend fun getTask(@Query("id") id: Int): TaskDetail

    @Headers("NeedAuth: true")
    @PUT("/api/tasks/")
    suspend fun putTask(
        @Query("id") id: Int,
        @Body putTask: PutTask
    ): PutTask

    @Headers("NeedAuth: true")
    @PATCH("/api/tasks/")
    suspend fun patchTask(
        @Query("id") id: Int,
        @Body patchedPutTask: PatchedPutTask
    ): PutTask

    @Headers("NeedAuth: true")
    @DELETE("/api/tasks/")
    suspend fun deleteTask(@Query("id") id: Int)

    //Пометить задание как выполненное
    @Headers("NeedAuth: true")
    @PUT("/api/tasks/{id}/completion/")
    suspend fun completeTask(@Query("id") id: Int)

    @Headers("NeedAuth: true")
    @DELETE("/api/tasks/{id}/completion/")
    suspend fun uncompleteTask(@Query("id") id: Int)

    @Headers("NeedAuth: true")
    @GET("/api/tasks/date_groups/")
    suspend fun getTasksByDate(@Query("status") status: String? = null): TaskDateGroup
    //---------------------------------------------------------

    @Headers("NeedAuth: true")
    @GET("/api/users/study-groups/codes/")
    suspend fun getStudyGroups(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("q") groupCode: String? = null,
    ): StudyGroupCodeList
}