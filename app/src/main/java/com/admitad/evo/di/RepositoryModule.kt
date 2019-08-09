package com.admitad.evo.di

import com.admitad.evo.data.repository.Repository
import com.admitad.evo.data.repository.RepositoryImp
import org.koin.dsl.module

val repositoryModule = module {
    single { provideRepository() }

}

fun provideRepository(): Repository {
    return RepositoryImp()
}