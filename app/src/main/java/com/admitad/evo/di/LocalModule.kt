package com.admitad.evo.di

import android.content.Context
import com.admitad.evo.data.local.LocalStorage
import com.admitad.evo.data.local.LocalStorageImp
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single { provideLocalStorage(androidContext()) }
}

fun provideLocalStorage(context: Context): LocalStorage {
    return LocalStorageImp(context)
}
