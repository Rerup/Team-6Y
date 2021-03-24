package com.example.tv2app.modules

import com.example.tv2app.repos.TaskRepository
import com.example.tv2app.viewmodels.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val taskModule : Module = module {
    single { TaskRepository() }
    viewModel { TaskViewModel(get()) }
}
