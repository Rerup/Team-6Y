package com.example.tv2app.modules

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApp : Application() {
    override fun onCreate(){
        super.onCreate()
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@MyApp)
            // declare modules
            modules(userModule, taskModule)
        }
    }
}