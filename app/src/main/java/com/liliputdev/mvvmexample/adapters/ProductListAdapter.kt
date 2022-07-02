package com.liliputdev.mvvmexample.adapters

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.liliputdev.mvvmexample.R
import com.liliputdev.mvvmexample.repository.retrofit.apiModel.APIModelAllProductElement
import com.squareup.picasso.Picasso

/**
 * Created by Masood Dalman on 7/1/2022.
 */
class ProductListAdapter:BaseQuickAdapter<APIModelAllProductElement, BaseViewHolder>(R.layout.item_product) {
    override fun convert(holder: BaseViewHolder, item: APIModelAllProductElement) {
       holder.setText(R.id.itemTextViewProductTitle,item.title).
               setText(R.id.itemTextViewProductPrice,"$${item.price}").
               setText(R.id.itemTextViewProductRate,"${item.rating.rate} / ${item.rating.count}")

        Picasso.get().load(item.image).centerCrop().resize(300,300).into(holder.getView(R.id.itemImageViewProductImage) as ImageView)
    }
}