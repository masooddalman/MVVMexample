package com.liliputdev.mvvmexample.ui.mainActivity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.liliputdev.mvvmexample.R
import com.liliputdev.mvvmexample.adapters.ProductListAdapter
import com.liliputdev.mvvmexample.ui.dialogs.FiltersDialog
import com.liliputdev.mvvmexample.ui.dialogs.interfaces.FilterDialogCallBack
import com.liliputdev.mvvmexample.ui.settings.SettingsActivity

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel
    private val adapter = ProductListAdapter()
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainActivityViewModel(this)
        setContentView(R.layout.activity_main)

        setViews()
        observeOnLiveData()

        viewModel.getAllProduct()

    }

    private fun setViews() {
        //the default animations won't work because of using picasso in the adapters
        //so we comment them and we will implement items animation manually inside the adapter
       /* //enable empty layout
        adapter.isUseEmpty = true
        //use progress view to show while app is trying to fetch data from API
        adapter.setEmptyView(R.layout.list_loading_layout)
        //enable one time animation while scrolling
        adapter.animationEnable = true
        //type of the animation
        adapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInBottom)*/

        recyclerView = findViewById(R.id.recyclerViewMainActivity)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    fun observeOnLiveData() {
        viewModel.listData.observe(this, Observer {
            adapter.addData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menuSettings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
            }
            R.id.menuFilter -> {
                FiltersDialog().initialize(this, layoutInflater).show(
                    listOf(
                        "electronics",
                        "jewelery",
                        "men's clothing",
                        "women's clothing"
                    ),
                    object : FilterDialogCallBack {
                        override fun onFilterSelected(category: String) {
                            adapter.data=viewModel.filterItems(category)
                            adapter.notifyDataSetChanged()
                        }
                    }
                )
            }
        }

        return super.onOptionsItemSelected(item)
    }
}