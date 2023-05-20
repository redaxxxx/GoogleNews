package com.prof.reda.android.project.googlenews.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "phone") val phone:String,
    @ColumnInfo(name = "email") val email:String)

