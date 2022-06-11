package com.liliputdev.mvvmexample.storage

import android.content.Context

/**
 * Created by Masood Dalman on 6/11/2022.
 */
class Prefererences(context: Context) : localPreferences {
    private val sharedPreferences = context.getSharedPreferences(
        "Masood", Context.MODE_PRIVATE
    )

    private val _keyUserLogin = "key_user_login"
    private val _keyAppTheme = "key_app_theme"
    private val _keyCounter = "key_counter"

    fun setUserLoggedIn() {
        setBool(_keyUserLogin, true)
    }

    fun setUserLoggedOut() {
        setBool(_keyUserLogin, false)
    }

    fun isUserLoggedIn(): Boolean {
        return getBool(_keyUserLogin)
    }

    fun setApplicationTheme(isLight: Boolean) {
        setBool(_keyAppTheme, isLight)
    }

    fun getApplicationTheme(): Boolean {
        return getBool(_keyAppTheme)
    }

    fun setCounter(value: Int) {
        setInt(_keyCounter, value)
    }

    fun getCounter(): Int {
        return getInt(_keyCounter)
    }


    override fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, "")!!
    }

    override fun setInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    override fun setBool(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun getBool(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }
}