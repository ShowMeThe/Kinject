package com.show.kInject.core.module

import com.show.kInject.core.Logger
import com.show.kInject.core.qualifier.Qualifier
import java.util.concurrent.ConcurrentHashMap

/**
 * PackageName : com.show.kinit_core.module
 * Date: 2020/12/18
 * Author: ShowMeThe
 */

fun moduleScope(scope: Module.() -> Unit): Module {
    val moduleBean = Module()
    scope.invoke(moduleBean)
    return moduleBean
}


open class Module {

    var qualifier: Qualifier<*>? = null
        set(value) {
            field = value
            setParentKey(field)
        }
    private val entrySingle by lazy { ConcurrentHashMap<String, Any>() }

    private val entryFactory by lazy { ConcurrentHashMap<String, Definition<*>?>() }

    fun getEntry() = entrySingle

    fun getFactoryEntry() = entryFactory

    /**
     * If the class type are the same , cause the data lose , Use Method {@link #scopeByName}
     */
    fun scope(single: () -> Any) {
        val scopeData = single()
        addSingle(scopeData::class.java.name, scopeData)
    }

//    inline fun <reified T> factory(noinline factory: () -> T?) {
//        addFactory(T::class.java.name, factory)
//    }
//
//    fun addFactory(groupName: String, any: () -> Any?) {
//        if (getFactoryEntry()[groupName] == null) {
//            getFactoryEntry()[groupName] = any
//        }
//    }

    inline fun <reified R,reified T1> factory(noinline factory: (T1) -> R?){
        val groupName = R::class.java.name
        if (getFactoryEntry()[groupName] == null) {
            getFactoryEntry()[groupName] = Definition {
                it._values.forEach {
                    Logger.log("factory parameter ${it}")
                }
                it.component1<T1?>()?.let { it1 ->
                    Logger.log("factory parameter ${it1.hashCode()}")
                    factory.invoke(it1)
                }
            }
        }
    }

    inline fun <reified R> new(
        constructor: () -> R,
    ): R = constructor()

    /**
     * When class type are the same , distinguish them by name
     */
    fun scopeByName(groupName: String, single: () -> Any) {
        addSingle(groupName, single())
    }

    private fun addSingle(groupName: String, any: Any) {
        if (getEntry()[groupName] == null) {
            getEntry()[groupName] = any
        }
    }

    fun get(name: String): Any? {
        return getEntry()[name]
    }

    fun getFactory(name: String,vararg any: Any): Any? {
        return getFactoryEntry()[name]?.definition?.invoke(ParametersHolder(any.toMutableList()))
    }

    open fun setParentKey(qualifier: Qualifier<*>?) {

    }

    /**
     * Be careful to use it, it will clean all value
     */
    fun clear(){
        entryFactory.clear()
        entrySingle.clear()
    }
}