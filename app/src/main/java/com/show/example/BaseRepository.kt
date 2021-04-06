package com.show.example

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.show.kInject.core.Logger


/**
 *  com.show.kinit
 *  2020/6/20
 *  20:40
 */
open class BaseRepository(var owner: LifecycleOwner?) : LifecycleObserver {

    init {
        init(owner)
        Logger.log("Inject $owner")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        onClear()
    }

    private fun init(owner: LifecycleOwner?) {
        owner?.lifecycle?.addObserver(this)
    }


    /**
     * 适当使用避免造成内存泄漏
     */
    private fun onClear() {
        owner?.lifecycle?.removeObserver(this)
        owner = null
    }


    fun androidScope(scope:LifecycleOwner?.()->Unit){
        scope.invoke(owner)

    }

}