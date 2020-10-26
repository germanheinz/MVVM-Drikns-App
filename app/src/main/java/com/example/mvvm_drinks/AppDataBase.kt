package com.example.mvvm_drinks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm_drinks.data.model.MovieEntity
import com.example.mvvm_drinks.domain.MovieDao

//Start Point to create DataBase

// We need to create a singleton Class
@Database(entities = arrayOf(MovieEntity::class), version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    // Singleton, for access through the entire app
    companion object{
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase{
            INSTANCE = INSTANCE ?: Room.databaseBuilder(context, AppDataBase::class.java, "movie_table").build()
            return INSTANCE!!
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}