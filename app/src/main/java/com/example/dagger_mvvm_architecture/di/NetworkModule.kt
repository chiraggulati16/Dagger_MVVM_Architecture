package com.example.dagger_mvvm_architecture.di

import com.example.dagger_mvvm_architecture.data.apis.WebService
import com.example.dagger_mvvm_architecture.util.PrefsManager
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    @JvmStatic
    fun okHttpClient(prefsManager: PrefsManager): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .cache(null)
            .addInterceptor(getHttpLoggingInterceptor())
            .addInterceptor(getNetworkInterceptor(prefsManager))
            .build()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun retrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.abc_567random.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    private fun getNetworkInterceptor(prefsManager: PrefsManager): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()

            val accessToken = prefsManager.getString("TOKEN", "")

            request = if (accessToken != null && !accessToken.isEmpty()) {
                val requestBuilder = request.newBuilder()
                requestBuilder.addHeader("Accept", "application/json")
                    .addHeader("Authorization", "Token $accessToken")

                requestBuilder.build()


            } else {
                request.newBuilder().addHeader("Accept", "application/json").build()
            }

            chain.proceed(request)
        }
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    @JvmStatic
    fun webService(retrofit: Retrofit): WebService = retrofit.create(WebService::class.java)


}