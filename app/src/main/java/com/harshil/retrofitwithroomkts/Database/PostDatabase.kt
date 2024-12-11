package com.harshil.retrofitwithroomkts.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.harshil.retrofitwithroomkts.Dao.PostDao
import com.harshil.retrofitwithroomkts.Model.Post

@Database(entities = [Post::class], version = 1,exportSchema = false)
abstract class PostDatabase : RoomDatabase() {
    abstract  fun getPostDao(): PostDao
}