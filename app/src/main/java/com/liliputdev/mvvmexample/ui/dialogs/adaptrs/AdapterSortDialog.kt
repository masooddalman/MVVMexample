package com.liliputdev.mvvmexample.ui.dialogs.adaptrs

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.liliputdev.mvvmexample.R
import com.liliputdev.mvvmexample.ui.dialogs.dataModel.SortFilterDataModel

/**
 * Created by Masood Dalman on 8/17/2022.
 */
class AdapterSortDialog :
    BaseQuickAdapter<SortFilterDataModel, BaseViewHolder>(R.layout.item_sort_dialog) {

    override fun convert(holder: BaseViewHolder, item: SortFilterDataModel) {
        holder.setText(R.id.textviewSortDialogTitle, item.title)
            .setText(R.id.textviewSortDialogDescription, item.description)


    }
}