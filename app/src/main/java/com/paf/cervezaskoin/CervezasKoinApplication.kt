package com.paf.cervezaskoin

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CervezasKoinApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.ERROR) //esto esta asi pero deberia ser DEBUG, pero peta
            androidContext(this@CervezasKoinApplication)
            //modules(appModule)
        }
    }
}