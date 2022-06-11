package com.liliputdev.mvvmexample.storage

/**
 * Created by Masood Dalman on 6/11/2022.
 */
interface localPreferences {

    fun setString(key:String,value:String)
    fun getString(key:String):String
    fun setInt(key:String,value:Int)
    fun getInt(key:String):Int
    fun setBool(key: String,value:Boolean)
    fun getBool(key: String):Boolean
}