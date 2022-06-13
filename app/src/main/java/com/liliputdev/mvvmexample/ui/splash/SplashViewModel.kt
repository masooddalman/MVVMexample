package com.liliputdev.mvvmexample.ui.splash

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.liliputdev.mvvmexample.storage.Prefererences
import com.liliputdev.mvvmexample.ui.login.LoginActivity
import com.liliputdev.mvvmexample.ui.mainActivity.MainActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Masood Dalman on 6/11/2022.
 */
class SplashViewModel constructor(private val context: Context) : ViewModel() {
    var splashView: SplashView? =null
    fun setView(view: SplashView)
    {
        splashView=view
    }

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



    fun increaseCounter()
    {
        Prefererences(context).let {
            var counter=it.getCounter()
            counter++
            it.setCounter(counter)
        }
    }

    private val isUserLoggedIn:Boolean
        get() =Prefererences(context).isUserLoggedIn()

    
    fun completeSplash()
    {
        val intentMainActivity= Intent(context, MainActivity::class.java)
        val intentLoginActivity= Intent(context, LoginActivity::class.java)
        GlobalScope.launch{
            delay(5000)
            if (isUserLoggedIn) {
                //go to main activity
                context.startActivity(intentMainActivity)
            } else {
                //go to login activity
                context.startActivity(intentLoginActivity)
            }
            splashView?.finishActivity()
        }
    }
}