package com.liliputdev.mvvmexample.ui.mainActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
                FiltersDialog().initialize(this, layoutInflater).show("Filters",
                    listOf(
                        Pair("electronics","show only electronics stuff"),
                        Pair("jewelery","show only jewelries"),
                        Pair("men's clothing","show only men's clothing"),
                        Pair("women's clothing","show only women's clothing"),
                    ),
                    object : FilterDialogCallBack {
                        override fun onFilterSelected(category: String) {
                            adapter.data=viewModel.filterItems(category)
                            adapter.notifyDataSetChanged()
                        }
                    }
                )
            }
            R.id.menuSort->{
                FiltersDialog().initialize(this, layoutInflater).show("Sort By",
                    listOf(
                        Pair("name","sorting entire list based on items name"),
                        Pair("price","sorting entire list based on items price"),
                        Pair("rate","sorting entire list based on items rating")
                    ),
                    object : FilterDialogCallBack {
                        override fun onFilterSelected(sort: String) {
                            adapter.data = viewModel.sortItems(sort)
                            adapter.notifyDataSetChanged()
                        }
                    }
                )
            }
        }

        return super.onOptionsItemSelected(item)
    }
}