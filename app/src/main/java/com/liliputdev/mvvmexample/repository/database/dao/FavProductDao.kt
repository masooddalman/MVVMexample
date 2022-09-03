package com.liliputdev.mvvmexample.repository.database.dao

import androidx.room.*
import com.liliputdev.mvvmexample.repository.database.dataModel.FavProduct
import com.liliputdev.mvvmexample.repository.database.dataModel.TABLE_FAVORITES

/**
 * Created by Masood Dalman on 9/3/2022.
 */
@Dao
interface FavProductDao {
    @Query("Select * from $TABLE_FAVORITES")
    fun getFavList(): List<FavProduct>
    @Insert
    fun addNewFav(faveProduct:FavProduct)
    @Update
    fun updateFavStatus(faveProduct:FavProduct)
    @Delete
    fun delete(faveProduct:FavProduct)
}