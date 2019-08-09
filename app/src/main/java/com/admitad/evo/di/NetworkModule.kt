package com.admitad.evo.di

import com.admitad.evo.data.remote.RemoteAdvBlockDataSource
import com.admitad.evo.data.remote.RemoteAdvBlockService
import com.admitad.evo.data.remote.RemoteStatisticsService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideDefaultOkhttpClient() }
    single { provideRetrofit(get()) }
    single { provideAdbBlockService(get()) }
    single { provideRemoteAdbBlockDataSource() }
    single { provideRemoteStatisticsService(get()) }
}

fun provideDefaultOkhttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .hostnameVerifier { _, _ -> true }
        .build()
}

fun provideRemoteAdbBlockDataSource(): RemoteAdvBlockDataSource {
    return RemoteAdvBlockDataSource()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideRemoteStatisticsService(retrofit: Retrofit) : RemoteStatisticsService =
    retrofit.create(RemoteStatisticsService::class.java)

fun provideAdbBlockService(retrofit: Retrofit): RemoteAdvBlockService =
    retrofit.create(RemoteAdvBlockService::class.java)

const val BASE_URL = "https://pos.getpriz.ru/api/v1/"