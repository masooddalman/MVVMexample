package com.liliputdev.mvvmexample.ui.login

/**
 * Created by Masood Dalman on 6/14/2022.
 */
interface LoginView {
    fun showLoading()
    fun hideLoading()
    fun successfulLogin()
}