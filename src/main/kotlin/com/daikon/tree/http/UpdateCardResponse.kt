package com.daikon.tree.http

class UpdateCardResponse (
    val id: Long,
    val parentId: Long? = null,
    val userId: Long?,
    val title: String,
    val description: String = "",
    val progress: Float = 0.0f,
    val importance: Int = 1
)
