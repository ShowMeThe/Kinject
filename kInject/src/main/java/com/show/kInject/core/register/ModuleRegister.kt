package com.show.kInject.core.register

import com.show.kInject.core.Logger
import com.show.kInject.core.module.Module
import com.show.kInject.core.qualifier.Qualifier
import com.show.kInject.core.qualifier.StringQualifier
import java.util.concurrent.ConcurrentHashMap

/**
* PackageName : com.show.kinit_core.register
* Date: 2020/12/18
* Author: ShowMeThe
*/

class ModuleRegister private constructor(){

    companion object{
        val instant by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { ModuleRegister() }
    }

    private val entry = ConcurrentHashMap<Qualifier<*>, Module>()


    fun addEntry(qualifier: StringQualifier, any: Module){
        any.qualifier = qualifier
        entry[qualifier] = any
        Logger.log("ModuleRegister : ${entry}")
    }

    fun getEntry(qualifier: StringQualifier) : Module? = entry[qualifier]

    fun removeEntry(qualifier: Qualifier<*>) = entry.remove(qualifier)

}