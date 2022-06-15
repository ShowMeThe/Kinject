package com.show.kInject.core.module

import com.show.kInject.core.Logger
import com.show.kInject.core.qualifier.Qualifier
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

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

    private val entryFactory by lazy { ConcurrentHashMap<FactoryInstant<*>, Definition<*>?>() }

    fun getEntry() = entrySingle

    fun getFactoryEntry() = entryFactory

    /**
     * If the class type are the same , cause the data lose , Use Method {@link #scopeByName}
     */
    fun scope(single: () -> Any) {
        val scopeData = single()
        addSingle(scopeData::class.java.name, scopeData)
    }

    inline fun <reified R> factory(noinline factory: () -> R?) {
        val factoryInstant = _createFactory<R>(resultType = R::class)
        getFactoryEntry()[factoryInstant] = Definition {
            factory.invoke()
        }
    }

    inline fun <reified R, reified T1> factory(noinline factory: (T1?) -> R?) {
        val factoryInstant = _createFactory<R>(arrayListOf(T1::class), R::class)
        getFactoryEntry()[factoryInstant] = Definition {
            factory.invoke(it.component1())
        }
    }

    inline fun <reified R, reified T1, reified T2> factory(noinline factory: (T1?, T2?) -> R?) {
        val factoryInstant = _createFactory<R>(arrayListOf(T1::class, T2::class), R::class)
        getFactoryEntry()[factoryInstant] = Definition {
            factory.invoke(it.component1(), it.component2())
        }
    }

    inline fun <reified R, reified T1, reified T2, reified T3> factory(noinline factory: (T1?, T2?, T3?) -> R?) {
        val factoryInstant =
            _createFactory<R>(arrayListOf(T1::class, T2::class, T3::class), R::class)
        getFactoryEntry()[factoryInstant] = Definition {
            factory.invoke(it.component1(), it.component2(), it.component3())
        }
    }

    inline fun <reified R, reified T1, reified T2, reified T3, reified T4> factory(noinline factory: (T1?, T2?, T3?, T4?) -> R?) {
        val factoryInstant =
            _createFactory<R>(arrayListOf(T1::class, T2::class, T3::class, T4::class), R::class)
        getFactoryEntry()[factoryInstant] = Definition {
            factory.invoke(it.component1(), it.component2(), it.component3(), it.component4())
        }
    }

    inline fun <reified R, reified T1, reified T2, reified T3, reified T4, reified T5> factory(
        noinline factory: (T1?, T2?, T3?, T4?, T5?) -> R?
    ) {
        val factoryInstant = _createFactory<R>(
            arrayListOf(T1::class, T2::class, T3::class, T4::class, T5::class),
            R::class
        )
        getFactoryEntry()[factoryInstant] = Definition {
            factory.invoke(
                it.component1(),
                it.component2(),
                it.component3(),
                it.component4(),
                it.component5()
            )
        }
    }

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

    inline fun <reified R> getFactory(resultType: KClass<*>, vararg parameters: Any?): Any? {
        val parameterList = parameters.toMutableList()
        val secondaryTypes = parameterList.toClazzList
        return getFactoryEntry()[_createFactory<R>(secondaryTypes, resultType)]?.parameter?.invoke(
            ParametersHolder(parameterList)
        )
    }

    open fun setParentKey(qualifier: Qualifier<*>?) {

    }

    /**
     * Be careful to use it, it will clean all value
     */
    fun clear() {
        entryFactory.clear()
        entrySingle.clear()
    }
}


inline fun <reified R> _createFactory(
    secondaryTypes: List<KClass<*>?> = emptyList(),
    resultType: KClass<*>
): FactoryInstant<R> {
    return FactoryInstant(secondaryTypes, resultType)
}

inline val MutableList<Any?>.toClazzList
    get() = this.map {
        if (it != null) {
            it::class
        } else {
            null
        }
    }
