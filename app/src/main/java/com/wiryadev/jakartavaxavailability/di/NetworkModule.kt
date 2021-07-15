package com.wiryadev.jakartavaxavailability.di

import com.wiryadev.jakartavaxavailability.data.remote.network.ApiService
import com.wiryadev.jakartavaxavailability.data.remote.network.Endpoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .readTimeout(120, TimeUnit.SECONDS)
        .connectTimeout(120, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideService(httpClient: OkHttpClient): Endpoint {
        return Retrofit
            .Builder()
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiService.BASE_URL)
            .build()
            .create(Endpoint::class.java)
    }

}