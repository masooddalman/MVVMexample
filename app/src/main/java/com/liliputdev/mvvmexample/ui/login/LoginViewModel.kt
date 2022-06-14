package com.liliputdev.mvvmexample.ui.login

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.liliputdev.mvvmexample.storage.Prefererences
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Masood Dalman on 6/12/2022.
 */
class LoginViewModel constructor(val context: Context) : ViewModel() {

    val usernameError = MutableLiveData<Pair<Boolean, String>>()
    val passwordError = MutableLiveData<Pair<Boolean, String>>()
    private var isUserNameCorrect = false
    private var isPasswordCorrect = false
    var loginView: LoginView? = null
    val buttonLoginStatus = MutableLiveData<Boolean>()

    init {
        buttonLoginStatus.postValue((isUserNameCorrect && isPasswordCorrect))
    }

    fun setView(view: LoginView) {
        loginView = view
    }


    fun copyCredentialToClipboard() {
        val clipboard: ClipboardManager =
            context.getSystemService(AppCompatActivity.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("password", "9uQFF1Lh")
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
            username.length < 6 -> {
                usernameError.postValue(
                    Pair(
                        true,
                        "at least 6 character needed (${username.length}/6)"
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
            password.length < 8 -> {
                passwordError.postValue(
                    Pair(
                        true,
                        "at least 8 character needed (${password.length}/8)"
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
        loginView?.showLoading()
        GlobalScope.launch {
            delay(1000)
            if (username.equals("atuny0") && password.equals("9uQFF1Lh")) {
                loginView?.successfulLogin()
                Prefererences(context).setUserLoggedIn()
            }
            else
            {
                usernameError.postValue(Pair(true,"wrong username"))
                passwordError.postValue(Pair(true,"wrong password"))
            }
            loginView?.hideLoading()
        }
    }

}