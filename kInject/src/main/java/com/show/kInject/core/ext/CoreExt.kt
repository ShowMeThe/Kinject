package com.show.kInject.core.ext

import android.app.Application
import com.show.kInject.core.Components
import com.show.kInject.core.Logger
import com.show.kInject.core.qualifier.StringQualifier
import com.show.kInject.core.register.GlobalRegister
import com.show.kInject.core.register.ModuleRegister

/**
 *  com.show.kinit_core.ext
 *  2020/6/20
 *  18:00
 */

/**
 *
 */
fun androidContext(): Application? = getSingle(Components.ANDROID_APPLICATION_KEY)

fun androidContextNotNull(): Application {
    return getSingle(Components.ANDROID_APPLICATION_KEY)
        ?: error("Android application context is null")
}

inline fun <reified T> single(typeName: String = T::class.java.name): Lazy<T> =
    lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        GlobalRegister.instant.getEntry(StringQualifier().apply {
            setKeyName(typeName)
        }) as T
    }

inline fun <reified T> getSingle(typeName: String = T::class.java.name): T =
    GlobalRegister.instant.getEntry(StringQualifier().apply {
        setKeyName(typeName)
    }) as T


inline fun <reified T> inject(scopeClazz: Any, groupName: String = T::class.java.name): Lazy<T> {
    return lazy {
        ModuleRegister.instant.getEntry(StringQualifier().apply {
            setKeyName("$scopeClazz")
            Logger.log("inject Qualifier ${this}")
        })?.get(groupName) as T
    }
}

inline fun <reified T> getInject(scopeClazz: Any, groupName: String = T::class.java.name): T =
    ModuleRegister.instant.getEntry(
        StringQualifier().apply {
            setKeyName("$scopeClazz")
            Logger.log("inject Qualifier ${this}")
        })?.get(groupName) as T