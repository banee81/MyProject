package com.example.myapplication

import android.app.Application
import com.example.myapplication.koin.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        try {
            startKoin {
                androidContext(this@MyApplication)
                modules(koinModule)

            }
        } catch (e: Exception) {

        }

    }
}