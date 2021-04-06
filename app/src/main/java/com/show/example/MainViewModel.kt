package com.show.example

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import com.show.kInject.lifecyleowner.ext.getLifeOwner


/**
 *  com.show.kinit
 *  2020/6/20
 *  20:25
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {


    val repository by lazy { MainRepository(getLifeOwner(this)) }


}