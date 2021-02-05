package com.show.kInject.lifecyleowner.observer

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.show.kInject.core.qualifier.Qualifier
import com.show.kInject.core.register.ModuleRegister

/**
* PackageName : com.show.kinit_lifecycle.observer
* Date: 2020/12/18
* Author: ShowMeThe
*/

class LifeModuleObserver(var qualifier: Qualifier<*>) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        ModuleRegister.instant.removeEntry(qualifier)
    }

}