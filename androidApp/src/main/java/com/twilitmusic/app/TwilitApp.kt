package com.twilitmusic.app

import android.app.Application
import com.twilitmusic.app.di.appModule
import com.twilitmusic.app.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class TwilitApp : Application() {
    override fun onCreate() {
        super.onCreate()
        
        initKoin {
            androidLogger()
            androidContext(this@TwilitApp)
            modules(appModule)
        }
    }
}
