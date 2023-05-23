package com.prof.reda.android.project.googlenews.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.prof.reda.android.project.googlenews.data.models.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class NewsDatabase : RoomDatabase(){
    abstract fun userDao():NewsDao?
    companion object{
        private val LOCK: Any = Object()
        private const val DATABASE_NAME:String = "news.db"
        private lateinit var instance:NewsDatabase

        fun getInstance(context:Context): NewsDatabase{
            if (instance == null){
                synchronized(LOCK){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NewsDatabase::class.java,
                        DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }

    }
}