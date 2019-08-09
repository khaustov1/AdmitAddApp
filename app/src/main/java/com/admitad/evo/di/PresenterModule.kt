package com.admitad.evo.di

import com.admitad.evo.domain.MainPresenter
import com.admitad.evo.domain.MainPresenterImp
import org.koin.dsl.module

val presenterModule = module {
    single { provideMainPresenter() }
}

fun provideMainPresenter(): MainPresenter {
    return MainPresenterImp()
}