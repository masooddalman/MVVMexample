package com.liliputdev.mvvmexample.ui.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.liliputdev.mvvmexample.R
import com.liliputdev.mvvmexample.ui.dialogs.adaptrs.AdapterFilterDialog
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

    fun show(dialogTitle:String,categories:Collection<Pair<String,String>>,callBack: FilterDialogCallBack) {
        dialog = BottomSheetDialog(context)
        val view = layoutInflater.inflate(R.layout.dialog_filter_categpry_layout, null)
        dialog.setContentView(view)
        val title=view.findViewById<TextView>(R.id.textviewDialogFilterTitle);
        title.text=dialogTitle
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerviewDialogFilter)
        val adapter = AdapterFilterDialog()

        recyclerview.layoutManager=LinearLayoutManager(context)

        adapter.addData(categories)
        adapter.notifyDataSetChanged()
        adapter.setOnItemClickListener { adapter, view, position ->
            callBack.onFilterSelected(categories.elementAt(position).first)
            dismiss()
        }
        recyclerview.adapter = adapter
        val clear=view.findViewById<TextView>(R.id.textViewDialogFilterClear)
        clear.setOnClickListener {
            callBack.onFilterSelected("")
            dismiss()
        }
        dialog.show()
    }



    fun dismiss() {
        dialog.dismiss()
    }
}