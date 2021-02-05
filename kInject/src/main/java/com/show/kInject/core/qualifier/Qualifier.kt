package com.show.kInject.core.qualifier


/**
* PackageName : com.show.kinit_core.qualifier
* Date: 2020/12/18
* Author: ShowMeThe
*/

open class Qualifier<D> {

    private var key : D? = null

    private var typeName:String? = ""

    fun setTypeName(typeName: String?){
        this.typeName = typeName
    }

    fun getTypeName() = typeName
    fun getKey() = key

    fun setKeyName(key:D){
        this.key = key
    }

    inline fun<reified T> Qualifier<*>.makeTypeName(){
        setTypeName(T::class.java.name)
    }

    override fun hashCode(): Int {
        return getKey().hashCode() + getTypeName().hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return if(other is Qualifier<*>){
            other.key == this.key && other.typeName == this.typeName
        }else{
            false
        }
    }

    override fun toString(): String {
        return "Qualifier[Key:${key},TypeName:${typeName}]"
    }

}