package com.show.kInject.core.module

import com.show.kInject.core.qualifier.Qualifier
import java.util.concurrent.ConcurrentHashMap

/**
* PackageName : com.show.kinit_core.module
* Date: 2020/12/18
* Author: ShowMeThe
*/

fun module(scope: Module.()->Unit): Module {
    val moduleBean = Module()
    scope.invoke(moduleBean)
    return moduleBean
}


open class Module {

    var qualifier : Qualifier<*>? = null
        set(value) {
            field = value
            setParentKey(field)
        }
    private val entry = ConcurrentHashMap<String,Any>()

    private fun getEntry() = entry


    /**
     * If the class type are the same , cause the data lose , Use Method {@link #scopeByName}
     */
    fun scope(single: ()-> Any){
        val scopeData = single()
        addSingle(scopeData::class.java.name,scopeData)
    }

    /**
     * When class type are the same , distinguish them by name
     */
    fun scopeByName(groupName:String,single: ()-> Any){
        addSingle(groupName,single())
    }

    private fun addSingle(groupName: String,any: Any){
        if(getEntry()[groupName] == null){
            getEntry()[groupName] = any
        }
    }

    fun get(name: String):Any?{
        return getEntry()[name]
    }

    open fun setParentKey(qualifier: Qualifier<*>?){

    }

    fun getKeys() = entry.keys
}