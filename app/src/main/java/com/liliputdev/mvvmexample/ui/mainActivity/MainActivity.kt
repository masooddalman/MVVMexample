package com.liliputdev.mvvmexample.ui.mainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.liliputdev.mvvmexample.R
import com.liliputdev.mvvmexample.ui.settings.SettingsActivity

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel=MainActivityViewModel(this)
        

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.menuSettings->{
                startActivity(Intent(this,SettingsActivity::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }
}