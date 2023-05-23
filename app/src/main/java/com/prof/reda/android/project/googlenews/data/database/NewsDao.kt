package com.prof.reda.android.project.googlenews.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.prof.reda.android.project.googlenews.data.models.ArticleEntity
import com.prof.reda.android.project.googlenews.data.models.UserEntity
import com.prof.reda.android.project.googlenews.data.models.UserWithArticle

@Dao
interface NewsDao {
    @Query("SELECT * FROM user ORDER BY id desc")
    fun getUserData(): List<UserEntity>?

    @Insert
    fun insertUser(user: UserEntity): Long

    @Insert
    fun insertArticle(article: ArticleEntity)

    @Query("SELECT * FROM user inner join ArticleEntity on user.id = ArticleEntity.article_id")
    fun getAllUserWithArticle():List<UserWithArticle>
}