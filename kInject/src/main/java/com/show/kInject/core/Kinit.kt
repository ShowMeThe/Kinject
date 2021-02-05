package com.show.kInject.core

import com.show.kInject.core.module.Module
import com.show.kInject.core.qualifier.StringQualifier
import com.show.kInject.core.register.GlobalRegister
import com.show.kInject.core.register.ModuleRegister

/**
* PackageName : com.show.kinit_core
* Date: 2020/12/18
* Author: ShowMeThe
*/

fun initScope(component: Components.()->Unit){
    component.invoke(Components.get())
}

class Components {

    companion object{


        private val instant by lazy { Components() }
        fun get() = instant

    }

    fun enableLog(){
        Logger.enableLog()
    }


    inline fun <reified T>single(typeName:String = T::class.java.name,single: ()->T){
        GlobalRegister.instant.addEntry(StringQualifier().apply {
            setTypeName(typeName)
            setKeyName(T::class.java.name)
        },single())
    }

    /**
     * scopeClazz where your want to inject
     */
    fun <T :Any>module(scopeClazz:T,module: Module){
        ModuleRegister.instant.addEntry(StringQualifier().apply {
            setTypeName(scopeClazz::class.java.name)
            setKeyName(scopeClazz.toString())
            Logger.log("inject into clazz ${scopeClazz::class.java}")
        },module)
    }

}
