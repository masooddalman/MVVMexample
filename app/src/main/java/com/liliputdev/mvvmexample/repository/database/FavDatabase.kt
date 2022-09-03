package com.liliputdev.mvvmexample.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.liliputdev.mvvmexample.repository.database.dao.FavProductDao
import com.liliputdev.mvvmexample.repository.database.dataModel.FavProduct

/**
 * Created by Masood Dalman on 9/3/2022.
 */
@Database(entities = [FavProduct::class], exportSchema = false, version = 1)
abstract class FavDatabase: RoomDatabase() {
    companion object {
        private const val DB_NAME="database"
        var instance: FavDatabase? =null

        fun getInstance(context:Context):FavDatabase
        {
            if(instance==null)
            {
                instance= Room.databaseBuilder(context.applicationContext,FavDatabase::class.java, DB_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
            return instance as FavDatabase
        }
    }

    abstract fun favDao():FavProductDao

}