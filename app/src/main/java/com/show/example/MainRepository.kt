package com.show.example

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

/**
 *  com.show.kinit
 *  2020/6/20
 *  20:40
 */
class MainRepository(viewModel: ViewModel) : BaseRepository(viewModel) {

    fun go(){
        androidScope {
            Log.e("222222","MainRepository $viewModel")
        }
    }


}