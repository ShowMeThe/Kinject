package com.show.example

import android.util.Log
import androidx.lifecycle.*
import com.show.kInject.core.Logger
import com.show.kInject.core.ext.currentScope
import com.show.kInject.core.ext.inject
import kotlinx.coroutines.CoroutineScope


/**
 *  com.show.kinit
 *  2020/6/20
 *  20:40
 */

data class Coroutines(
    val viewModelScope: CoroutineScope? = null,
    val owner: LifecycleOwner? = null
)

open class BaseRepository(var viewModel: ViewModel) {


    private val owner: LifecycleOwner by lazy { inject(viewModel.currentScope,viewModel.currentScope) }

    init {
        init()
        Log.e(BaseRepository::class.java.name, "Inject $viewModel")
    }

    private fun init() {
        owner.lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    owner.lifecycle.removeObserver(this)
                }
            }
        })
    }


    fun androidScope(scope: Coroutines.() -> Unit) {
        scope.invoke(Coroutines(viewModel.viewModelScope, owner))
    }


}