package com.show.kInject.lifecyleowner.ext

import androidx.lifecycle.LifecycleOwner
import com.show.kInject.core.Logger
import com.show.kInject.core.qualifier.StringQualifier
import com.show.kInject.core.register.ModuleRegister

/**
* PackageName : com.show.kinit_lifecycle.ext
* Date: 2020/12/18
* Author: ShowMeThe
*/

inline fun <reified T> getLifeOwner(scopeClazz: T) : LifecycleOwner {
    return ModuleRegister.instant.getEntry(StringQualifier().apply {
        setKeyName(scopeClazz.toString())
        Logger.log("inject Qualifier ${this}")
    })?.get(scopeClazz.toString()) as LifecycleOwner
}

inline fun <reified T> getLifeOwnerOrNull(scopeClazz: T) : LifecycleOwner? {
    return ModuleRegister.instant.getEntry(StringQualifier().apply {
        setKeyName(scopeClazz.toString())
        Logger.log("inject Qualifier ${this}")
    })?.get(scopeClazz.toString()) as LifecycleOwner?
}


inline fun <reified T> injectLifeOwner(scopeClazz: T) : Lazy<LifecycleOwner> {
    return lazy {
        ModuleRegister.instant.getEntry(StringQualifier().apply {
            setKeyName(scopeClazz.toString())
            Logger.log("inject Qualifier ${this}")
        })?.get(scopeClazz.toString()) as LifecycleOwner
    }
}
