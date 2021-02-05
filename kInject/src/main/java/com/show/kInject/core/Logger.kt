package com.show.kInject.core

import android.util.Log

/**
* PackageName : com.show.kinit_core
* Date: 2020/12/18
* Author: ShowMeThe
*/

object Logger {

    private val defaultTag = "Kinit"
    private var enableLog = false
    fun enableLog(){
        enableLog = true
    }
    fun log(any: Any?){
        if(enableLog)
        Log.e(defaultTag,"${any}")
    }

}