package com.liliputdev.mvvmexample.ui.dialogs

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.liliputdev.mvvmexample.R
import com.liliputdev.mvvmexample.repository.retrofit.apiModel.Category
import com.liliputdev.mvvmexample.ui.dialogs.adaptrs.AdapterFilterDialog
import androidx.annotation.NonNull

import com.chad.library.adapter.base.BaseQuickAdapter
import com.liliputdev.mvvmexample.ui.dialogs.interfaces.FilterDialogCallBack


/**
 * Created by Masood Dalman on 7/5/2022.
 */
class FiltersDialog {

    private lateinit var context: Context
    private lateinit var layoutInflater: LayoutInflater
    private lateinit var dialog: BottomSheetDialog

    fun initialize(context: Context, layoutInflater: LayoutInflater): FiltersDialog {
        this.context = context
        this.layoutInflater = layoutInflater
        return this
    }

    fun show(categories:Collection<Category>,callBack: FilterDialogCallBack) {
        dialog = BottomSheetDialog(context)
        val view = layoutInflater.inflate(R.layout.dialog_filter_categpry_layout, null)
        dialog.setContentView(view)
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerviewDialogFilter)
        val adapter = AdapterFilterDialog()

        recyclerview.layoutManager=LinearLayoutManager(context)

        adapter.addData(categories)
        adapter.notifyDataSetChanged()
        /*adapter.setOnItemClickListener { adapter, view, position ->
            Toast.makeText(context, adapter.data[position].toString(), Toast.LENGTH_SHORT).show()
            dialog.dismiss()

        }*/
        adapter.setOnItemClickListener { adapter, view, position ->
            callBack.onFilterSelected(categories.elementAt(position))
            dialog.dismiss()
        }
        recyclerview.adapter = adapter
        dialog.show()
    }



    fun dismiss() {
        dialog.dismiss()
    }
}