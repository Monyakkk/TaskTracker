package com.komissarov.tasktracker.di

import android.util.Log
import com.komissarov.tasktracker.data.network.ApiService
import com.komissarov.tasktracker.data.network.SessionManager
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(interceptor)
        client.addInterceptor(authInterceptor)
        client.connectTimeout(20, TimeUnit.SECONDS)
        client.readTimeout(20, TimeUnit.SECONDS)
        return client.build()
    }

    @Singleton
    class AuthInterceptor @Inject constructor(private val sessionManager: SessionManager) :
        Interceptor {
        private val sessionToken
            get() = sessionManager.getAccessToken()

        override fun intercept(chain: Interceptor.Chain): Response {
            val request: Request = chain.request()
            val requestBuilder: Request.Builder = request.newBuilder()
            if (request.header(AUTH_HEADER) == "true") {
                if (sessionToken == null) {
                    throw RuntimeException("Session token should be defined for auth apis")
                } else {
                    requestBuilder.addHeader("Authorization", "Bearer $sessionToken")
                }
            }
            return chain.proceed(requestBuilder.build())
        }
    }

    private const val BASE_URL = "http://78.140.241.174:8100/"
    private const val AUTH_HEADER = "NeedAuth"
    const val AUTH_HEADER_KEY = "$AUTH_HEADER: true"

}