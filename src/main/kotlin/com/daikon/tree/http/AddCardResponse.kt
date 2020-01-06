package com.daikon.tree.http

data class AddCardResponse (
    val id: Long,
    val parentId: Long? = null,
    val title: String,
    val description: String,
    val progress: Float,
    val importance: Int
)