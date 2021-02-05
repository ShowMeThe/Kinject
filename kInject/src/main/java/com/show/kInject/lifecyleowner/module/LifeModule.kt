package com.show.kInject.lifecyleowner.module

import androidx.lifecycle.LifecycleOwner
import com.show.kInject.core.module.Module
import com.show.kInject.core.qualifier.Qualifier
import com.show.kInject.lifecyleowner.observer.LifeModuleObserver

/**
* PackageName : com.show.kinit_lifecycle.module
* Date: 2020/12/18
* Author: ShowMeThe
*/

fun LifecycleOwner.lifeModule(scope:LifeModule.()->Unit):LifeModule{
    val moduleBean = LifeModule(this)
    scope.invoke(moduleBean)
    return moduleBean
}

class LifeModule(var lifecycleOwner: LifecycleOwner) : Module() {

    override fun setParentKey(qualifier: Qualifier<*>?) {
        qualifier?.apply {
            lifecycleOwner.lifecycle.addObserver(LifeModuleObserver(qualifier))
        }
    }

    fun scopeLifeOwner(scopeClazz:Any){
        scopeByName(scopeClazz.toString()) { lifecycleOwner }
    }

}