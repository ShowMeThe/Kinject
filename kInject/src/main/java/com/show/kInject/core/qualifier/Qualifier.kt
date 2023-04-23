package com.show.kInject.core.qualifier


/**
* PackageName : com.show.kinit_core.qualifier
* Date: 2020/12/18
* Author: ShowMeThe
*/

open class Qualifier<D>(val key : D) {


    override fun hashCode(): Int {
        return 31 * key.hashCode()
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