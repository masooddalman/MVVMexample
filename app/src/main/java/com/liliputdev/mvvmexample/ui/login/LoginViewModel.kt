package com.liliputdev.mvvmexample.ui.login

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Masood Dalman on 6/12/2022.
 */
class LoginViewModel constructor(val activity: Activity) : ViewModel() {

    val usernameError = MutableLiveData<Pair<Boolean, String>>()
    val passwordError = MutableLiveData<Pair<Boolean, String>>()
    val buttonLoginStatus=MutableLiveData<Pair<Boolean,Boolean>>()

    init {
        buttonLoginStatus.postValue(Pair(first = false, second = false))
    }

    fun copyCredentialToClipboard() {
        val clipboard: ClipboardManager =
            activity.getSystemService(AppCompatActivity.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("password", "9uQFF1Lh")
        clipboard.setPrimaryClip(clip)
        Toast.makeText(activity, "Credential copied to clipboard", Toast.LENGTH_SHORT).show()
    }

    fun checkUserNameText(username: String) {
        buttonLoginStatus.postValue(Pair(false,buttonLoginStatus.value!!.second))
        when {
            username.isEmpty()//warn user to insert username
            -> {
                usernameError.postValue(Pair(true, "please insert username"))
            }
            username.length < 6 -> {
                usernameError.postValue(
                    Pair(
                        true,
                        "at least 6 character needed (${username.length}/6)"
                    )
                )
            }
            else -> {
                usernameError.postValue(Pair(false, ""))
                buttonLoginStatus.postValue(Pair(true,buttonLoginStatus.value!!.second))
            }
        }
    }

    fun checkPasswordText(password:String)
    {
        buttonLoginStatus.postValue(Pair(buttonLoginStatus.value!!.first,false))
        when {
            password.isEmpty()//warn user to insert username
            -> {
                passwordError.postValue(Pair(true, "please insert password"))
            }
            password.length < 8 -> {
                passwordError.postValue(
                    Pair(
                        true,
                        "at least 8 character needed (${password.length}/8)"
                    )
                )
            }
            else -> {
                passwordError.postValue(Pair(false, ""))
                buttonLoginStatus.postValue(Pair(buttonLoginStatus.value!!.first,true))
            }
        }
    }

    fun doLogin(username: String, password: String) {


    }

}