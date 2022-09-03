package com.liliputdev.mvvmexample.repository.database.dataModel

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Masood Dalman on 9/3/2022.
 */
const val TABLE_FAVORITES="favorites"
@Entity(tableName = TABLE_FAVORITES)
data class FavProduct(@PrimaryKey val id:Int, val isFaved:Boolean)
