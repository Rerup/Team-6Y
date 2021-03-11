package com.example.tv2app.modules

import com.example.tv2app.repos.UserRepository
import com.example.tv2app.viewmodels.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val userModule: Module = module{
        single { UserRepository() }
        viewModel { UserViewModel(get()) }
}