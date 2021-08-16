package com.show.kInject.core

import android.app.Application
import android.content.Context
import android.util.Log
import com.show.kInject.core.ext.getSingle
import com.show.kInject.core.module.Module
import com.show.kInject.core.qualifier.StringQualifier
import com.show.kInject.core.register.GlobalRegister
import com.show.kInject.core.register.ModuleRegister

/**
* PackageName : com.show.kinit_core
* Date: 2020/12/18
* Author: ShowMeThe
*/

fun initScope(component: Components.()->Unit){
    component.invoke(Components.get())
}

class Components {

    companion object{

        const val ANDROID_APPLICATION_KEY = "ANDROID_APPLICATION_KEY"

        private val instant by lazy { Components() }
        fun get() = instant
    }

    fun enableLog(){
        Logger.enableLog()
    }

    fun androidContext(context: Application){
        single(ANDROID_APPLICATION_KEY) { context }
    }

    inline fun <reified T> single(typeName:String = T::class.java.name,single: ()-> T){
        GlobalRegister.instant.addEntry(StringQualifier().apply {
            setKeyName(typeName)
        },single())
    }

    /**
     * scopeClazz where your want to inject
     */
    fun <T :Any>module(scopeClazz:T,module: Module){
        ModuleRegister.instant.addEntry(StringQualifier().apply {
            setKeyName(scopeClazz.toString())
            Logger.log("inject into clazz ${scopeClazz::class.java}")
        },module)
    }

}
