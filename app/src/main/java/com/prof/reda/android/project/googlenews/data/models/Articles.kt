// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.prof.reda.android.project.googlenews.data.models

data class Articles (
    val totalResults: Long,
    val articles: List<Article>,
    val status: String
)

data class Article (
    val publishedAt: String,
    val author: String? = null,
    val urlToImage: String? = null,
    val description: String? = null,
    val source: Source,
    val title: String,
    val url: String,
    val content: String
)

data class Source (
    val name: String,
    val id: String? = null
)
