package com.liliputdev.mvvmexample.ui.splash

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.liliputdev.mvvmexample.R
import com.liliputdev.mvvmexample.storage.Prefererences

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    lateinit var viewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel = SplashViewModel(this)
        findViewById<TextView>(R.id.textViewInfoSplash).text =
            "${viewModel.welcomeText}\n${viewModel.counterText}"
    }
}