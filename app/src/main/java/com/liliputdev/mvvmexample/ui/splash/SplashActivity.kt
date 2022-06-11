package com.liliputdev.mvvmexample.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //init viewModel
        viewModel = SplashViewModel(this)
        //set textview
        findViewById<TextView>(R.id.textViewInfoSplash).text =
            "${viewModel.welcomeText}\n${viewModel.counterText}"
        // increase counter
        viewModel.increaseCounter()


        val intentMainActivity= Intent(this, MainActivity::class.java)
        val intentLoginActivity= Intent(this, LoginActivity::class.java)
        GlobalScope.launch{
            delay(5000)
            if (viewModel.isUserLoggedIn) {
                //go to main activity
                startActivity(intentMainActivity)
                finish()
            } else {
                //go to login activity
                startActivity(intentLoginActivity)
                finish()
            }
        }

    }
}