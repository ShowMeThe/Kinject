package com.show.kInject.core

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.show.kInject.core.module.Module
import com.show.kInject.core.qualifier.Qualifier
import com.show.kInject.core.qualifier.StringQualifier
import java.io.Closeable
import java.util.concurrent.ConcurrentHashMap


open class Scope : Closeable {

    private val singleModule by lazy { Module() }

    fun module(block: Module.() -> Unit) {
        block.invoke(singleModule)
    }

    fun getModule() = singleModule

    override fun close() {
        singleModule.clear()
    }
}


inline fun initScope(name: String, block: Scope.() -> Unit): Scope {
    val qualifier = StringQualifier(name)
    val scope = Scope()
    ScopeComponents.get().addScope(qualifier, scope)
    block.invoke(scope)
    Logger.log("initScope name:[${name}],scope:[${scope}]")
    return scope
}

inline fun initGlobalScope(block: GlobalScope.() -> Unit): Scope {
    val qualifier = StringQualifier(ScopeComponents.ANDROID_APPLICATION_SCOPE)
    val scope = GlobalScope.instant
    ScopeComponents.get().addScope(qualifier, scope)
    block.invoke(scope)
    Logger.log("initGlobalScope:${scope}")
    return scope
}


fun Scope.bind(lifecycle: Lifecycle) {
    Logger.log("Scope bind life lifecycle = [${lifecycle}]")
    lifecycle.addObserver(object : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (event == Lifecycle.Event.ON_DESTROY) {
                lifecycle.removeObserver(this)
                ScopeComponents.get().removeScope(this@bind)
                Logger.log("Scope:${this@bind} remove from lifecycle = [${lifecycle}]")
            }
        }
    })
}

class GlobalScope private constructor() : Scope() {

    companion object {
        val instant by lazy { GlobalScope() }
    }

    fun androidContext(context: Application) {
        getModule().singleOf(ScopeComponents.ANDROID_APPLICATION_KEY) {
            context
        }
    }

}


class ScopeComponents {

    companion object {

        const val ANDROID_APPLICATION_SCOPE =
            "com.show.kInject.ScopeComponents.ANDROID_APPLICATION_SCOPE"
        const val ANDROID_APPLICATION_KEY =
            "com.show.kInject.ScopeComponents.ANDROID_APPLICATION_KEY"

        private val instant by lazy { ScopeComponents() }
        fun get() = instant
    }

    fun enableLog() {
        Logger.enableLog()
    }

    private val scopes by lazy { ConcurrentHashMap<StringQualifier, Scope>() }

    fun getScope(name: String) = requireNotNull(scopes[StringQualifier(name)]) {
        "Can not find the scope by name = [$name]"
    }

    fun addScope(qualifier: StringQualifier, scope: Scope) {
        if (scopes[qualifier] == null) {
            scopes[qualifier] = scope
        }
    }

    fun removeScope(qualifier: StringQualifier) {
        scopes.remove(qualifier)
    }

    fun removeScope(scope: Scope) {
        val it = scopes.iterator()
        while (it.hasNext()) {
            val pair = it.next()
            if (pair.value == scope) {
                pair.value.close()
                it.remove()
                break
            }
        }
    }


//
//    inline fun <reified T> global(typeName: String = T::class.java.name, single: () -> T) {
//        GlobalRegister.instant.addEntry(StringQualifier().apply {
//            setKeyName(typeName)
//        }, single())
//    }
//
//    /**
//     * scopeClazz where your want to inject
//     */
//    fun <T : Any> module(scopeClazz: T, module: Module) {
//        ModuleRegister.instant.addEntry(StringQualifier().apply {
//            setKeyName(scopeClazz.toString())
//            Logger.log("inject into clazz $scopeClazz")
//        }, module)
//    }

}
