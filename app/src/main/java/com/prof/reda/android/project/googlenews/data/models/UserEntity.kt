package com.prof.reda.android.project.googlenews.data.models

import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "phone") val phone:String,
    @ColumnInfo(name = "email") val email:String)

@Entity(foreignKeys = arrayOf(
    ForeignKey(entity = UserEntity::class,
    parentColumns = arrayOf("id"),
        childColumns = arrayOf(""),
        onDelete = ForeignKey.CASCADE
    )
))
data class ArticleEntity(
    @ColumnInfo(name = "article_id") val id: Int,
    @ColumnInfo(name = "publishedAt") val publishedAt:String,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "urlToImage") val urlImage: String,
    @ColumnInfo(name = "description") val description:String,
    @ColumnInfo(name = "url") val url:String,
    @ColumnInfo(name = "title") val title:String
)