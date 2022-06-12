package com.liliputdev.mvvmexample.ui.splash

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.liliputdev.mvvmexample.storage.Prefererences
import com.liliputdev.mvvmexample.ui.login.LoginActivity
import com.liliputdev.mvvmexample.ui.mainActivity.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Masood Dalman on 6/11/2022.
 */
class SplashViewModel constructor(private val activity: Activity) : ViewModel() {

    val welcomeText: String
        get() {
            if (Prefererences(activity).isUserLoggedIn())
                return "user name"
            return "welcome dear"
        }

    val counterText: String
        get() {
            Prefererences(activity).getCounter().let {
                if (it == 0)
                    return "This is your first time"
                return "App counter is $it"
            }
        }



    fun increaseCounter()
    {
        Prefererences(activity).let {
            var counter=it.getCounter()
            counter++
            it.setCounter(counter)
        }
    }

    private val isUserLoggedIn:Boolean
        get() =Prefererences(activity).isUserLoggedIn()
    
    fun completeSplash()
    {
        val intentMainActivity= Intent(activity, MainActivity::class.java)
        val intentLoginActivity= Intent(activity, LoginActivity::class.java)
        GlobalScope.launch{
            delay(5000)
            if (isUserLoggedIn) {
                //go to main activity
                activity.startActivity(intentMainActivity)
                activity.finish()
            } else {
                //go to login activity
                activity.startActivity(intentLoginActivity)
                activity.finish()
            }
        }
    }
}