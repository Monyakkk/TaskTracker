package com.komissarov.tasktracker.data.network

import com.komissarov.tasktracker.data.network.entities.*
import com.komissarov.tasktracker.di.NetworkModule.AUTH_HEADER_KEY
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.PUT
import retrofit2.http.PATCH
import retrofit2.http.DELETE
import retrofit2.http.Path


interface ApiService {

    //Получение образовательных программ и направлений подготовки
    @GET("api/academic-plans/educational-programs/")
    suspend fun getEducationalPrograms(
        @Query("limit") limit: Int? = 1000,
        @Query("offset") offset: Int? = null,
        @Query("enrollment_year") enrollment_year: String? = null,
        @Query("title") title: String? = null
    ): EducationalProgramList

    @GET("api/academic-plans/fields-of-study/")
    suspend fun getFieldsOfStudy(
        @Query("limit") limit: Int? = 1000,
        @Query("offset") offset: Int? = null,
        @Query("search") search: String? = null
    ): FieldOfStudyList


    //Получение JWT для авторизации запросов.
    @POST("api/auth/jwt/create/")
    suspend fun postTokenObtainPair(@Body tokenObtainPairRequest: TokenObtainPairRequest): TokenObtainPair

    //Takes a refresh type JSON web token and returns an access type JSON web token if the refresh token is valid.
    @POST("api/auth/jwt/refresh/")
    suspend fun postTokenRefresh(@Body tokenRefresh: TokenRefresh): TokenRefresh

    //Takes a token and indicates if it is valid. This view provides no information about a token's fitness for a particular use.
    @POST("api/auth/jwt/verify/")
    suspend fun postTokenVerify(@Body tokenOVerify: TokenVerify)

    //---------------------------------------------------------
    @Headers(AUTH_HEADER_KEY)
    @GET("api/auth/users/")
    suspend fun getUsers(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): UserList

    @POST("api/auth/users/")
    suspend fun postUser(@Body userCreate: UserCreate): UserCreated

    @Headers(AUTH_HEADER_KEY)
    @GET("api/auth/users/")
    suspend fun getUser(@Query("id") id: Int): User

    @Headers(AUTH_HEADER_KEY)
    @PUT("api/auth/users/")
    suspend fun putUser(
        @Query("id") id: Int,
        @Body user: User
    ): User

    @Headers(AUTH_HEADER_KEY)
    @PATCH("api/auth/users/")
    suspend fun patchUser(
        @Query("id") id: Int,
        @Body user: User
    ): User

    @Headers(AUTH_HEADER_KEY)
    @DELETE("api/auth/users/")
    suspend fun deleteUser(@Query("id") id: Int)

    //---------------------------------------------------------
    //Активация аккаунта пользователя
    @POST("api/auth/users/activation/")
    suspend fun postActivationCode(@Body activationCode: ActivationCode): ActivationCode

    //---------------------------------------------------------
    @Headers(AUTH_HEADER_KEY)
    @GET("api/auth/users/me")
    suspend fun getMe(): CurrentUser

    @Headers(AUTH_HEADER_KEY)
    @PUT("api/auth/users/me")
    suspend fun putMe(@Body currentUser: CurrentUser): CurrentUser

    @Headers(AUTH_HEADER_KEY)
    @PATCH("api/auth/users/me")
    suspend fun patchMe(@Body currentUser: CurrentUser): CurrentUser

    @Headers(AUTH_HEADER_KEY)
    @DELETE("api/auth/users/me")
    suspend fun deleteMe()
    //---------------------------------------------------------

    @POST("api/auth/users/resend_activation/")
    suspend fun postResendActivation(@Body sendEmailReset: SendEmailReset)

    @POST("api/auth/users/reset_email/")
    suspend fun postSendEmailReset(@Body sendEmailReset: SendEmailReset)

    @POST("api/auth/users/reset_email_confirm/")
    suspend fun postSendEmailReset(@Body usernameResetConfirm: UsernameResetConfirm)

    @POST("api/auth/users/reset_password/")
    suspend fun postResetPassword(@Body sendEmailReset: SendEmailReset)

    @POST("api/auth/users/reset_password_confirm/")
    suspend fun postResetPasswordConfirm(@Body sendEmailReset: PasswordResetConfirm)

    @POST("api/auth/users/set_email/")
    suspend fun postSetUsername(@Body setUsername: SetUsername): SetUsername

    @POST("api/auth/users/set_email/")
    suspend fun postSetPassword(@Body setPassword: SetPassword): SetPassword
    //---------------------------------------------------------

    //CRUD для учебных дисциплин.
    @Headers(AUTH_HEADER_KEY)
    @GET("api/subjects/")
    suspend fun getSubjects(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("title") title: String? = null,
    ): SubjectListList

    @Headers(AUTH_HEADER_KEY)
    @GET("api/subjects/{id}/")
    suspend fun getSubject(@Path("id") id: Int): Subject

    @Headers(AUTH_HEADER_KEY)
    @PUT("api/subjects/{id}/")
    suspend fun putSubject(@Path("id") id: Int, @Body subject: UpdateSubject): UpdateSubject

    @Headers(AUTH_HEADER_KEY)
    @PATCH("api/subjects/{id}/")
    suspend fun patchSubject(
        @Path("id") id: Int,
        @Body subject: PatchedUpdateSubject
    ): PatchedUpdateSubject
    //---------------------------------------------------------

    @Headers(AUTH_HEADER_KEY)
    @GET("api/tasks/")
    suspend fun getTasks(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("title") title: String? = null,
        @Query("status") status: String? = null,
        @Query("subject") subjectId: Int? = null,
    ): TaskListList

    @Headers(AUTH_HEADER_KEY)
    @POST("api/tasks/")
    suspend fun postTask(@Body putTask: PostTask): PutTask

    @Headers(AUTH_HEADER_KEY)
    @GET("api/tasks/{id}/")
    suspend fun getTask(@Path("id") id: Int): TaskDetail

    @Headers(AUTH_HEADER_KEY)
    @PUT("api/tasks/{id}/")
    suspend fun putTask(
        @Path("id") id: Int,
        @Body putTask: PutTask
    ): PutTask

    @Headers(AUTH_HEADER_KEY)
    @PATCH("api/tasks/{id}/")
    suspend fun patchTask(
        @Path("id") id: Int,
        @Body patchedPutTask: PatchedPutTask
    ): PutTask

    @Headers(AUTH_HEADER_KEY)
    @DELETE("api/tasks/{id}/")
    suspend fun deleteTask(@Path("id") id: Int)

    //Пометить задание как выполненное
    @Headers(AUTH_HEADER_KEY)
    @PUT("api/tasks/{id}/completion/")
    suspend fun completeTask(@Path("id") id: Int)

    @Headers(AUTH_HEADER_KEY)
    @DELETE("api/tasks/{id}/completion/")
    suspend fun uncompleteTask(@Path("id") id: Int)

    @Headers(AUTH_HEADER_KEY)
    @GET("api/tasks/date_groups/")
    suspend fun getTasksByDate(@Query("status") status: String? = null): TaskDateGroup
    //---------------------------------------------------------

    @GET("api/users/study-groups/codes/")
    suspend fun getStudyGroups(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("q") groupCode: String? = null,
    ): EducationalProgramList
}