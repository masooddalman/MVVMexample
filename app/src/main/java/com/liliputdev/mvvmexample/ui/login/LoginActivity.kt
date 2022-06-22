package com.liliputdev.mvvmexample.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import com.liliputdev.mvvmexample.R
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.view.View
import android.widget.ProgressBar
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.liliputdev.mvvmexample.extention.getOrWaitForValue
import com.liliputdev.mvvmexample.ui.mainActivity.MainActivity


class LoginActivity : AppCompatActivity() {

    lateinit var textInputLayoutUsername: TextInputLayout
    lateinit var textInputLayoutPassword: TextInputLayout
    lateinit var edittextUsername: EditText
    lateinit var edittextPassword: EditText
    lateinit var buttonLogin: Button
    lateinit var fabCopy: FloatingActionButton
    lateinit var progressBarLogin:ProgressBar

    lateinit var viewModel: LoginViewModel
    var isLoginButtonEnable=false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel= LoginViewModel(this)

        setViews()
        setOnClickListener()
        addTextWatcher()
        observeOnLiveData()

    }

    private fun setViews()
    {
        textInputLayoutPassword=findViewById(R.id.textInputLayoutPassword)
        textInputLayoutUsername=findViewById(R.id.textInputLayoutUserName)
        edittextUsername=findViewById(R.id.edittextUsername)
        edittextPassword=findViewById(R.id.edittextPassword)
        buttonLogin=findViewById(R.id.buttonLogin)
        progressBarLogin=findViewById(R.id.progressBarLogin)
        fabCopy=findViewById(R.id.fabCopyPassword)
    }
    private fun addTextWatcher()
    {
        edittextUsername.addTextChangedListener {
            viewModel.checkUserNameText(it.toString())
        }
        edittextPassword.addTextChangedListener {
            viewModel.checkPasswordText(it.toString())
        }
    }
    private fun setOnClickListener()
    {
        buttonLogin.setOnClickListener {
            //call API to login
            viewModel.doLogin(edittextUsername.text.toString(),edittextPassword.text.toString())
        }

        fabCopy.setOnClickListener {
            //copy password to clipboard
            viewModel.copyCredentialToClipboard()
        }
    }

    private fun observeOnLiveData()
    {
        viewModel.usernameError.observe(this, {
            textInputLayoutUsername.isErrorEnabled=it.first
            textInputLayoutUsername.error=it.second
        })

        viewModel.passwordError.observe(this,{
            textInputLayoutPassword.isErrorEnabled=it.first
            textInputLayoutPassword.error=it.second
        })
        viewModel.buttonLoginStatus.observe(this,{
            buttonLogin.isEnabled=it
            isLoginButtonEnable=it
        })

        viewModel.showLoading.observe(this,{
            if(it)
            {
                buttonLogin.visibility= View.GONE
                progressBarLogin.visibility= View.VISIBLE
            }
            else
            {
                buttonLogin.visibility= View.VISIBLE
                progressBarLogin.visibility= View.GONE
            }
        })

        viewModel.loginState.observe(this,{
            if(it)
            {
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
        })
    }
    
}