package com.liliputdev.mvvmexample.ui.dialogs.adaptrs

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.liliputdev.mvvmexample.R

/**
 * Created by Masood Dalman on 7/5/2022.
 */
class AdapterFilterDialog:BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_filter_dialog) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.text1,item).setText(R.id.text2,"filter in ${item} stuff...")
    }
}