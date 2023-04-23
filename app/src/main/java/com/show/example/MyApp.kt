package com.show.example

import android.app.Application
import com.show.kInject.core.Logger
import com.show.kInject.core.ext.currentScope
import com.show.kInject.core.initGlobalScope
import com.show.kInject.core.initScope
import com.show.kInject.core.module.moduleScope


/**
 *  com.show.kinit
 *  2020/6/20
 *  18:03
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.enableLog()
        initGlobalScope {
            module {
                factory<Main,String> { it ->
                    Main(it)
                }

                factory<Main,String,Int> { it,it1 ->
                    Main(it,it1)
                }

                factory<Main,String,Int,Double> { it,it1,it2 ->
                    Main(it,it1,it2)
                }
            }
        }
    }
}
