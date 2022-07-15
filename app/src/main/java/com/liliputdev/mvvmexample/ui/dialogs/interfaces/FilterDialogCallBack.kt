package com.liliputdev.mvvmexample.ui.dialogs.interfaces

import com.liliputdev.mvvmexample.repository.retrofit.apiModel.Category

/**
 * Created by Masood Dalman on 7/14/2022.
 */
interface FilterDialogCallBack {
    fun onFilterSelected(category: Category)
}