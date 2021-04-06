package com.show.kInject.core.qualifier


/**
* PackageName : com.show.kinit_core.qualifier
* Date: 2020/12/18
* Author: ShowMeThe
*/

open class Qualifier<D> {

    private var key : D? = null

    fun getKey() = key

    fun setKeyName(key:D){
        this.key = key
    }


    override fun hashCode(): Int {
        return getKey().hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return if(other is Qualifier<*>){
            other.key == this.key
        }else{
            false
        }
    }

    override fun toString(): String {
        return "Qualifier[Key:${key}]"
    }

}