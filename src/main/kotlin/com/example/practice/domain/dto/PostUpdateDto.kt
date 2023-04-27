package com.example.practice.domain.dto

import com.example.practice.domain.Post

data class PostUpdateDto (
    val id: Long,
    val title: String,
    val author: String,
    val content: String,
) {
    fun toEntity(id: Long) = Post(
            id = id,
            title = title,
            author = author,
            content = content
    )
}
