package com.harshil.retrofitwithroomkts.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class PostModel(
    val body: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}