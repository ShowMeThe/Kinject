package com.show.example

import android.util.Log
import androidx.lifecycle.LifecycleOwner

/**
 *  com.show.kinit
 *  2020/6/20
 *  20:40
 */
class MainRepository(owner: LifecycleOwner?) : BaseRepository(owner) {


    init {
        Log.e("222222","LifecycleOwner $owner")
    }

}