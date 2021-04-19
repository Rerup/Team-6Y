package com.example.tv2app.modules

import com.example.tv2app.services.QRService
import org.koin.core.module.Module
import org.koin.dsl.module

val qrModule : Module = module {
    single { QRService() }
}