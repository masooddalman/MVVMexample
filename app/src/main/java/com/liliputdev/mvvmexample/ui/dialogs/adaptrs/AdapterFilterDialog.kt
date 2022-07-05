package com.liliputdev.mvvmexample.ui.dialogs.adaptrs

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.liliputdev.mvvmexample.R
import com.liliputdev.mvvmexample.repository.retrofit.apiModel.Category

/**
 * Created by Masood Dalman on 7/5/2022.
 */
class AdapterFilterDialog:BaseQuickAdapter<Category,BaseViewHolder>(R.layout.item_filter_dialog) {
    override fun convert(holder: BaseViewHolder, item: Category) {
        holder.setText(android.R.id.text1,item.name)
    }
}