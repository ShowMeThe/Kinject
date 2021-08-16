package com.show.example

import android.app.Application
import com.show.kInject.core.initScope


/**
 *  com.show.kinit
 *  2020/6/20
 *  18:03
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initScope {
            androidContext(this@MyApp)
        }

    }
}