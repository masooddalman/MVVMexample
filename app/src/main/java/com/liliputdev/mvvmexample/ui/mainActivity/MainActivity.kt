package com.liliputdev.mvvmexample.ui.mainActivity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.liliputdev.mvvmexample.R
import com.liliputdev.mvvmexample.adapters.ProductListAdapter
import com.liliputdev.mvvmexample.ui.settings.SettingsActivity

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel
    private val adapter=ProductListAdapter()
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=MainActivityViewModel(this)
        setContentView(R.layout.activity_main)

        setViews()

    }

    private fun setViews()
    {
        recyclerView=findViewById(R.id.recyclerViewMainActivity)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=adapter
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