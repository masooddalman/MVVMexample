package com.liliputdev.mvvmexample.repository.retrofit

import com.liliputdev.mvvmexample.repository.retrofit.sendModel.SendModelLogin

/**
 * Created by Masood Dalman on 6/26/2022.
 */
class WebRepository constructor(private val service: RetrofitService) {

    fun login(username: String, password: String)=service.login(SendModelLogin(password,username))

    fun getAllProduct()=service.getAllProduct()

}