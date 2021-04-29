package com.example.tv2app.modules

import com.example.tv2app.services.QRServiceRepository
import com.example.tv2app.viewmodels.QRServiceViewModel
import com.example.tv2app.viewmodels.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val qrModule : Module = module {
    single { QRServiceRepository() }
    viewModel { QRServiceViewModel(get()) }
}