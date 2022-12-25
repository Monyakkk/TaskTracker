package com.komissarov.tasktracker.data.network

import com.komissarov.tasktracker.data.network.entities.*
import retrofit2.http.*


interface ApiService {

    //Получение JWT для авторизации запросов.
    @POST("/api/auth/jwt/create/")
    suspend fun postTokenObtainPair(@Body tokenObtainPairRequest: com.komissarov.tasktracker.data.network.entities.TokenObtainPairRequest): com.komissarov.tasktracker.data.network.entities.TokenObtainPair

    //Takes a refresh type JSON web token and returns an access type JSON web token if the refresh token is valid.
    @POST("/api/auth/jwt/refresh/")
    suspend fun postTokenRefresh(@Body tokenRefresh: com.komissarov.tasktracker.data.network.entities.TokenRefresh): com.komissarov.tasktracker.data.network.entities.TokenRefresh

    //Takes a token and indicates if it is valid. This view provides no information about a token's fitness for a particular use.
    @POST("/api/auth/jwt/verify/")
    suspend fun postTokenVerify(@Body tokenOVerify: com.komissarov.tasktracker.data.network.entities.TokenVerify)

    //---------------------------------------------------------
    //Takes a token and indicates if it is valid. This view provides no information about a token's fitness for a particular use.
    @Headers("NeedAuth: true")
    @GET("/api/auth/users/")
    suspend fun getUsers(@Query("limit") limit: Int? = null, @Query("offset") offset: Int? = null)

    //Takes a token and indicates if it is valid. This view provides no information about a token's fitness for a particular use.
    @Headers("NeedAuth: true")
    @POST("/api/auth/users/")
    suspend fun postUser(@Body userCreate: com.komissarov.tasktracker.data.network.entities.UserCreate): com.komissarov.tasktracker.data.network.entities.UserCreate

    @Headers("NeedAuth: true")
    @GET("/api/auth/users/")
    suspend fun getUser(@Query("id") id: Int): com.komissarov.tasktracker.data.network.entities.User

    @Headers("NeedAuth: true")
    @PUT("/api/auth/users/")
    suspend fun putUser(
        @Query("id") id: Int,
        @Body user: com.komissarov.tasktracker.data.network.entities.User
    ): com.komissarov.tasktracker.data.network.entities.User

    @Headers("NeedAuth: true")
    @PATCH("/api/auth/users/")
    suspend fun patchUser(
        @Query("id") id: Int,
        @Body user: com.komissarov.tasktracker.data.network.entities.User
    ): com.komissarov.tasktracker.data.network.entities.User

    @Headers("NeedAuth: true")
    @DELETE("/api/auth/users/")
    suspend fun deleteUser(@Query("id") id: Int)

    //---------------------------------------------------------
    //Активация аккаунта пользователя
    @POST("/api/auth/jwt/verify/")
    suspend fun postActivationCode(@Body activationCode: com.komissarov.tasktracker.data.network.entities.ActivationCode): com.komissarov.tasktracker.data.network.entities.ActivationCode

    //---------------------------------------------------------
    @Headers("NeedAuth: true")
    @GET("/api/auth/users/me")
    suspend fun getMe(): com.komissarov.tasktracker.data.network.entities.CurrentUser

    @Headers("NeedAuth: true")
    @PUT("/api/auth/users/me")
    suspend fun putMe(@Body currentUser: com.komissarov.tasktracker.data.network.entities.CurrentUser): com.komissarov.tasktracker.data.network.entities.CurrentUser

    @Headers("NeedAuth: true")
    @PATCH("/api/auth/users/me")
    suspend fun patchMe(@Body currentUser: com.komissarov.tasktracker.data.network.entities.CurrentUser): com.komissarov.tasktracker.data.network.entities.CurrentUser

    @Headers("NeedAuth: true")
    @DELETE("/api/auth/users/me")
    suspend fun deleteMe()
    //---------------------------------------------------------

    @POST("/api/auth/users/resend_activation/")
    suspend fun postResendActivation(@Body sendEmailReset: com.komissarov.tasktracker.data.network.entities.SendEmailReset)

    @POST("/api/auth/users/reset_email/")
    suspend fun postSendEmailReset(@Body sendEmailReset: com.komissarov.tasktracker.data.network.entities.SendEmailReset)

    @POST("/api/auth/users/reset_email_confirm/")
    suspend fun postSendEmailReset(@Body usernameResetConfirm: com.komissarov.tasktracker.data.network.entities.UsernameResetConfirm)

    @POST("/api/auth/users/reset_password/")
    suspend fun postResetPassword(@Body sendEmailReset: com.komissarov.tasktracker.data.network.entities.SendEmailReset)

    @POST("/api/auth/users/reset_password_confirm/")
    suspend fun postResetPasswordConfirm(@Body sendEmailReset: com.komissarov.tasktracker.data.network.entities.PasswordResetConfirm)

    @POST("/api/auth/users/set_email/")
    suspend fun postSetUsername(@Body setUsername: com.komissarov.tasktracker.data.network.entities.SetUsername): com.komissarov.tasktracker.data.network.entities.SetUsername

    @POST("/api/auth/users/set_email/")
    suspend fun postSetPassword(@Body setPassword: com.komissarov.tasktracker.data.network.entities.SetPassword): com.komissarov.tasktracker.data.network.entities.SetPassword
    //---------------------------------------------------------

    //CRUD для учебных дисциплин.
    @Headers("NeedAuth: true")
    @GET("/api/subjects/")
    suspend fun getSubjects(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("title") title: String? = null,
    ): com.komissarov.tasktracker.data.network.entities.SubjectListList

    @Headers("NeedAuth: true")
    @GET("/api/subjects/")
    suspend fun getSubject(@Query("id") id: Int): com.komissarov.tasktracker.data.network.entities.Subject

    @Headers("NeedAuth: true")
    @PUT("/api/subjects/")
    suspend fun putSubject(@Body subject: com.komissarov.tasktracker.data.network.entities.UpdateSubject): com.komissarov.tasktracker.data.network.entities.UpdateSubject

    @Headers("NeedAuth: true")
    @PATCH("/api/subjects/")
    suspend fun patchSubject(@Body subject: com.komissarov.tasktracker.data.network.entities.PatchedUpdateSubject): com.komissarov.tasktracker.data.network.entities.PatchedUpdateSubject
    //---------------------------------------------------------

    @Headers("NeedAuth: true")
    @GET("/api/tasks/")
    suspend fun getTasks(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("title") title: String? = null,
        @Query("status") status: String? = null,
        @Query("subject") subjectId: Int? = null,
    ): com.komissarov.tasktracker.data.network.entities.TaskListList

    @Headers("NeedAuth: true")
    @POST("/api/tasks/")
    suspend fun postTask(@Body putTask: com.komissarov.tasktracker.data.network.entities.PutTask): com.komissarov.tasktracker.data.network.entities.PutTask

    @Headers("NeedAuth: true")
    @GET("/api/tasks/")
    suspend fun getTask(@Query("id") id: Int): com.komissarov.tasktracker.data.network.entities.TaskDetail

    @Headers("NeedAuth: true")
    @PUT("/api/tasks/")
    suspend fun putTask(
        @Query("id") id: Int,
        @Body putTask: com.komissarov.tasktracker.data.network.entities.PutTask
    ): com.komissarov.tasktracker.data.network.entities.PutTask

    @Headers("NeedAuth: true")
    @PATCH("/api/tasks/")
    suspend fun patchTask(
        @Query("id") id: Int,
        @Body patchedPutTask: com.komissarov.tasktracker.data.network.entities.PatchedPutTask
    ): com.komissarov.tasktracker.data.network.entities.PutTask

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
    suspend fun getTasksByDate(@Query("status") status: String? = null): com.komissarov.tasktracker.data.network.entities.TaskDateGroup
    //---------------------------------------------------------

    @Headers("NeedAuth: true")
    @GET("/api/users/study-groups/codes/")
    suspend fun getStudyGroupes(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("q") groupCode: String? = null,
    ): com.komissarov.tasktracker.data.network.entities.StudyGroupCodeList
}