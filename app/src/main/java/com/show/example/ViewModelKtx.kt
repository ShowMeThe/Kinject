package com.show.example

import android.util.Log
import androidx.lifecycle.*
import com.show.kInject.lifecyleowner.ext.getLifeOwner
import com.show.kInject.lifecyleowner.ext.getLifeOwnerOrNull
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object ViewModelKtx {


}


class RepositoryRef<T : BaseRepository>: ReadOnlyProperty<Any, String> {



    private var repository : BaseRepository? = null
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        repository = BaseRepository(null)
        Log.e("222222","$repository ${thisRef::class.java.name} ${getLifeOwnerOrNull(thisRef)}")

        return "1231"
    }

}