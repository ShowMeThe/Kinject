package com.show.kInject.core.ext

import android.app.Application
import com.show.kInject.core.GlobalScope
import com.show.kInject.core.ScopeComponents

/**
 *  com.show.kinit_core.ext
 *  2020/6/20
 *  18:00
 */

fun getGlobalScope() = ScopeComponents
    .get().getScope(ScopeComponents.ANDROID_APPLICATION_SCOPE) as GlobalScope

fun androidContext(): Application? = getGlobalScope().getModule()
    .get(ScopeComponents.ANDROID_APPLICATION_KEY) as Application?

fun androidContextNotNull(): Application {
    return requireNotNull(androidContext())
}

inline fun <reified T> single(): T =
    getGlobalScope().getModule().get(T::class.java.name) as T

inline fun <reified T> singleFactory(vararg parameter: Any?): T {
    return getGlobalScope().getModule().getFactory<T>(T::class,*parameter) as T
}


fun getScope(scopeName: String) = ScopeComponents.get().getScope(scopeName)

inline fun <reified T> inject(scopeName: String,name:String = T::class.java.name): T {
    return getScope(scopeName).getModule().get(name) as T
}

inline fun <reified T> factory(scopeName: String,vararg parameter: Any?): T {
    return getScope(scopeName).getModule().getFactory<T>(T::class,*parameter) as T
}

val Any.currentScope get() = this.toString()
