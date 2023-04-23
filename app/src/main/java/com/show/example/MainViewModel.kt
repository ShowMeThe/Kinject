package com.show.example

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.show.kInject.core.ext.currentScope
import com.show.kInject.core.ext.getScope
import com.show.kInject.core.ext.inject


/**
 *  com.show.kinit
 *  2020/6/20
 *  20:25
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {


    val repository by lazy { MainRepository(inject(this.currentScope,this.currentScope)) }


    fun get(){
        Log.e("222222","MainViewModel ${this}")
        repository
    }

}