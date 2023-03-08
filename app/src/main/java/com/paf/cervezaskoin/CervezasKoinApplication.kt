package com.paf.cervezaskoin

import android.app.Application
import android.content.Context
import com.paf.cervezaskoin.di.initDI

class CervezasKoinApplication: Application() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: CervezasKoinApplication

        val appContext: Context
            get() = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}