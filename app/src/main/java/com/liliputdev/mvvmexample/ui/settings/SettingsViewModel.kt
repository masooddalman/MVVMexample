package com.liliputdev.mvvmexample.ui.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager

/**
 * Created by Masood Dalman on 6/16/2022.
 */
class SettingsViewModel constructor(val context: Context) : ViewModel(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    fun registerPreferenceChangeListener() {
        PreferenceManager.getDefaultSharedPreferences(context)
            .registerOnSharedPreferenceChangeListener(this)
    }

    fun unRegisterPreferenceChangeListener() {
        PreferenceManager.getDefaultSharedPreferences(context)
            .unregisterOnSharedPreferenceChangeListener(this)
    }

    private fun changeThemeToDark() {
        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_YES
        )
    }

    private fun changeThemeToLight() {
        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        key?.let {
            when (it) {
                "theme" -> {
                    if (sharedPreferences!!.getBoolean(key, false))
                        changeThemeToDark()
                    else
                        changeThemeToLight()
                }
            }
        }
    }
}