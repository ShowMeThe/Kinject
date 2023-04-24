package com.show.example

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.show.kInject.core.ext.currentScope
import com.show.kInject.core.ext.getScope
import com.show.kInject.core.ext.inject
import com.show.kInject.core.ext.singleFactory


/**
 *  com.show.kinit
 *  2020/6/20
 *  20:25
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {


    val repository : MainRepository by lazy { singleFactory(this) }


    fun get(){
        Log.e("222222","MainViewModel ${this}")
        repository.go()
    }

}