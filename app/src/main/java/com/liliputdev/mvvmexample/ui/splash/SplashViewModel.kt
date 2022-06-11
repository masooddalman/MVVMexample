package com.liliputdev.mvvmexample.ui.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import com.liliputdev.mvvmexample.storage.Prefererences

/**
 * Created by Masood Dalman on 6/11/2022.
 */
class SplashViewModel constructor(private val context: Context) : ViewModel() {

    val welcomeText: String
        get() {
            if (Prefererences(context).isUserLoggedIn())
                return "user name"
            return "welcome dear"
        }

    val counterText: String
        get() {
            Prefererences(context).getCounter().let {
                if (it == 0)
                    return "This is your first time"
                return "App counter is $it"
            }
        }
}