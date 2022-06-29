package com.liliputdev.mvvmexample.repository.retrofit

import com.liliputdev.mvvmexample.repository.retrofit.apiModel.ApiLoginModel
import com.liliputdev.mvvmexample.repository.retrofit.sendModel.SendModelLogin
import org.json.JSONObject
import retrofit2.Call

/**
 * Created by Masood Dalman on 6/26/2022.
 */
class WebRepository constructor(private val service: RetrofitService) {

    fun login(username: String, password: String)=service.login(SendModelLogin(password,username))

    fun getAllProduct()=service.getAllProduct()

}