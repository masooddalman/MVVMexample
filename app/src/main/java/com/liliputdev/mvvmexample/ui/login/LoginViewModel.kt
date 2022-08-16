package com.liliputdev.mvvmexample.ui.login

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.liliputdev.mvvmexample.repository.retrofit.RetrofitService
import com.liliputdev.mvvmexample.repository.retrofit.WebRepository
import com.liliputdev.mvvmexample.repository.retrofit.apiModel.ApiLoginModel
import com.liliputdev.mvvmexample.storage.Prefererences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Masood Dalman on 6/12/2022.
 */
class LoginViewModel constructor(val context: Context) : ViewModel() {

    val _retrofit=RetrofitService.getInstance()
    lateinit var  repository:WebRepository

    val usernameError = MutableLiveData<Pair<Boolean, String>>()
    val passwordError = MutableLiveData<Pair<Boolean, String>>()
    private var isUserNameCorrect = false
    private var isPasswordCorrect = false
    val buttonLoginStatus = MutableLiveData<Boolean>()
    val showLoading=MutableLiveData<Boolean>(false)
    val loginState=MutableLiveData<Boolean>(false)

    init {
        buttonLoginStatus.postValue((isUserNameCorrect && isPasswordCorrect))
        repository= WebRepository(_retrofit)
    }



    fun copyCredentialToClipboard() {
        val clipboard: ClipboardManager =
            context.getSystemService(AppCompatActivity.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("password", "m38rmF\$")
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Credential copied to clipboard", Toast.LENGTH_SHORT).show()
    }

    fun checkUserNameText(username: String) {
        when {
            username.isEmpty()//warn user to insert username
            -> {
                usernameError.postValue(Pair(true, "please insert username"))
                isUserNameCorrect = false
            }
            username.length < 5 -> {
                usernameError.postValue(
                    Pair(
                        true,
                        "at least 6 character needed (${username.length}/5)"
                    )
                )
                isUserNameCorrect = false
            }
            else -> {
                usernameError.postValue(Pair(false, ""))
                isUserNameCorrect = true
            }
        }
        buttonLoginStatus.postValue((isUserNameCorrect && isPasswordCorrect))
    }

    fun checkPasswordText(password: String) {
        when {
            password.isEmpty()//warn user to insert username
            -> {
                passwordError.postValue(Pair(true, "please insert password"))
                isPasswordCorrect = false
            }
            password.length < 7 -> {
                passwordError.postValue(
                    Pair(
                        true,
                        "at least 8 character needed (${password.length}/7)"
                    )
                )
                isPasswordCorrect = false
            }
            else -> {
                passwordError.postValue(Pair(false, ""))
                isPasswordCorrect = true
            }
        }

        buttonLoginStatus.postValue((isUserNameCorrect && isPasswordCorrect))

    }

    fun doLogin(username: String, password: String) {
        showLoading.postValue(true)
       val response= repository.login(username,password)
        response.enqueue(object :Callback<ApiLoginModel>{
            override fun onResponse(call: Call<ApiLoginModel>, response: Response<ApiLoginModel>) {
                when (response.code())
                {
                    200->{
                        Log.v("masood","login response ${response.body()}")
                        loginState.postValue(true)
                        Prefererences(context).setUserLoggedIn()
                    }
                    else->{
                        Log.v("masood","login response ${response.code()} -> ${response.body()}")
                        loginState.postValue(false)
                        usernameError.postValue(Pair(true,"wrong username"))
                        passwordError.postValue(Pair(true,"wrong password"))
                    }
                }
                showLoading.postValue(false)
            }

            override fun onFailure(call: Call<ApiLoginModel>, t: Throwable) {
                Log.v("masood","login fail ${t.message}")
                loginState.postValue(false)
                usernameError.postValue(Pair(true,"wrong username"))
                passwordError.postValue(Pair(true,"wrong password"))
                showLoading.postValue(false)
            }

        })
    }

}