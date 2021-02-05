package com.show.kInject.core.register

import com.show.kInject.core.qualifier.Qualifier
import com.show.kInject.core.qualifier.StringQualifier
import java.util.concurrent.ConcurrentHashMap

/**
* PackageName : com.show.kinit_core.register
* Date: 2020/12/18
* Author: ShowMeThe
*/

class GlobalRegister private constructor(){

    companion object{
        val instant by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { GlobalRegister() }
    }

    private val entry = ConcurrentHashMap<Qualifier<*>,Any?>()


    fun addEntry(qualifier: StringQualifier, any: Any?){
        entry[qualifier] = any
    }

    fun getEntry(qualifier: StringQualifier) = entry[qualifier]



}