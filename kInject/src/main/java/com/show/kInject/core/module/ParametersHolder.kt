package com.show.kInject.core.module

import kotlin.reflect.KClass

class NoParameterFoundException(msg: String) : Exception(msg)

open class ParametersHolder(private val _values: MutableList<Any?> = mutableListOf()) {


    open fun <T> elementAt(i: Int, clazz: KClass<*>): T =
        if (_values.size > i) _values[i] as T else throw NoParameterFoundException(
            "Can't get injected parameter #$i from $this for type '${clazz::class.java.name}'"
        )

    inline operator fun <reified T> component1(): T? = elementAt(0, T::class)
    inline operator fun <reified T> component2(): T? = elementAt(1, T::class)
    inline operator fun <reified T> component3(): T? = elementAt(2, T::class)
    inline operator fun <reified T> component4(): T? = elementAt(3, T::class)
    inline operator fun <reified T> component5(): T? = elementAt(4, T::class)


}