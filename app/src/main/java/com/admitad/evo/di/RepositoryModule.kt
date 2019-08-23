package com.admitad.evo.di

import com.admitad.evo.data.repository.ReceiptStateRepository
import com.admitad.evo.data.repository.ReceiptStateRepositoryImp
import com.admitad.evo.data.repository.Repository
import com.admitad.evo.data.repository.RepositoryImp
import org.koin.dsl.module

val repositoryModule = module {
    single { provideRepository() }
    single { provideReceiptStateRepository() }
}

fun provideRepository(): Repository {
    return RepositoryImp()
}

fun provideReceiptStateRepository(): ReceiptStateRepository {
    return ReceiptStateRepositoryImp()
}