package com.admitad.evo

import android.app.Application
import com.admitad.evo.di.localModule
import com.admitad.evo.di.networkModule
import com.admitad.evo.di.presenterModule
import com.admitad.evo.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AdmitadApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AdmitadApp)
            modules(listOf(networkModule, localModule, repositoryModule, presenterModule))
        }
    }
}