package com.liliputdev.mvvmexample.ui.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.liliputdev.mvvmexample.R
import com.liliputdev.mvvmexample.ui.dialogs.adaptrs.AdapterSortDialog
import com.liliputdev.mvvmexample.ui.dialogs.dataModel.SortFilterDataModel
import com.liliputdev.mvvmexample.ui.dialogs.interfaces.SortDialogCallback

/**
 * Created by Masood Dalman on 8/17/2022.
 */
class SortDialog {
    private lateinit var context: Context
    private lateinit var layoutInflater: LayoutInflater
    private lateinit var dialog: BottomSheetDialog

    fun initialize(context: Context, layoutInflater: LayoutInflater): SortDialog {
        this.context = context
        this.layoutInflater = layoutInflater
        return this
    }

    fun show(
        dialogTitle: String,
        sortList:MutableList<SortFilterDataModel>,
        callBack: SortDialogCallback
    ) {
        dialog = BottomSheetDialog(context)
        val view = layoutInflater.inflate(R.layout.dialog_filter_categpry_layout, null)
        dialog.setContentView(view)
        val title = view.findViewById<TextView>(R.id.textviewDialogFilterTitle);
        title.text = dialogTitle
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerviewDialogFilter)
        val adapter = AdapterSortDialog()

        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter
        adapter.addData(sortList)
        adapter.notifyDataSetChanged()

         adapter.setOnItemClickListener { _, view, position ->

             val menu = PopupMenu(context, view)

             menu.menu.add("ASC")
             menu.menu.add("DESC")

             menu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                 item.title
                 callBack.onSortSelected(sortList.elementAt(position).title,item.title=="ASC")
             dismiss()
                 true
             })

             menu.show()
         }

        val clear = view.findViewById<TextView>(R.id.textViewDialogFilterClear)
        clear.setOnClickListener {
            callBack.onSortSelected("", true)
            dismiss()
        }
        dialog.show()
    }


    fun dismiss() {
        dialog.dismiss()
    }
}