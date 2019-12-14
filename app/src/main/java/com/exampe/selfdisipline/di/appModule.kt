package com.exampe.selfdisipline.di

import com.exampe.selfdisipline.repositories.ExercizeRepository
import com.exampe.selfdisipline.ui.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    single { ExercizeRepository() }

    viewModel { MainViewModel(get()) }
}