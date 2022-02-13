package com.api.yuyue.model.dto

data class DtoNewArticle (
    val content: String,
    val subtype: String,
    val imgName: String?,
)

data class DtoUpdateArticle (
    val id: Int,
    val content: String?,
    val imgName: String?,
)