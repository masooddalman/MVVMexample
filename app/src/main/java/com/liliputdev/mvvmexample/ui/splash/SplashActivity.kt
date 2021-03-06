package com.liliputdev.mvvmexample.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.liliputdev.mvvmexample.R
import com.liliputdev.mvvmexample.ui.login.LoginActivity
import com.liliputdev.mvvmexample.ui.mainActivity.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    lateinit var viewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("theme",false))
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //init viewModel
        viewModel = SplashViewModel(this)
        //set textview
        findViewById<TextView>(R.id.textViewInfoSplash).text =
            "${viewModel.welcomeText}\n${viewModel.counterText}"
        // increase counter
        viewModel.increaseCounter()

        viewModel.completeSplash()


        viewModel.finishActivity.observe(this,{
            if(it)
                finish()
        })
    }

}