package com.example.practice.domain.dto

import com.example.practice.domain.Post

class PostCreateDto (
    val title: String,
    val author: String,
    val content: String,
) {
    fun toEntity() = Post(
            title = title,
            author = author,
            content = content
    )
}