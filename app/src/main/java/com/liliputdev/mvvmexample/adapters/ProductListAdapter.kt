package com.liliputdev.mvvmexample.adapters

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.res.ColorStateList
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
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

       holder.setText(R.id.itemTextViewProductTitle, item.title).
               setText(R.id.itemTextViewProductPrice,"$${item.price}").
               setText(R.id.itemTextViewProductRate,"${item.rating.rate} / ${item.rating.count}")

        Picasso.get().load(item.image).centerCrop().resize(300,300).into(holder.getView(R.id.itemImageViewProductImage) as ImageView)
        holder.getView<ImageView>(R.id.itemImageviewFav).let {
            if (item.isfaved)
                ImageViewCompat.setImageTintList(
                    it,
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            context,
                            R.color.design_default_color_primary
                        )
                    )
                )
            else
                ImageViewCompat.setImageTintList(
                    it,
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            context,
                            R.color.md_theme_dark_primary
                        )
                    )
                )
        }
        animateItem(holder.itemView,1000)
        addChildClickViewIds(R.id.itemImageviewFav)
    }

    private fun animateItem(itemView: View, duration:Long)
    {
        itemView.scaleX = 0.7f
        itemView.scaleY = 0.7f
        itemView.alpha = 0.0f
        val scaleX = ObjectAnimator.ofFloat(
            itemView, "scaleX", 0.7f, 1f
        )
        scaleX.duration = duration
        scaleX.interpolator = OvershootInterpolator()

        val scaleY = ObjectAnimator.ofFloat(
            itemView, "scaleY", 0.7f, 1f
        )
        scaleY.duration = duration
        scaleY.interpolator = OvershootInterpolator()

        val alpha = ObjectAnimator.ofFloat(itemView, "alpha", 0f, 1f)
        alpha.duration = duration
        alpha.interpolator = OvershootInterpolator()

        val set = AnimatorSet()
        set.playTogether(scaleX, scaleY, alpha)
        set.start()
    }
}