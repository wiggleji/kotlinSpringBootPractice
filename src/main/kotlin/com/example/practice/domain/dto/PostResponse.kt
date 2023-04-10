package com.example.practice.domain.dto

import com.example.practice.domain.Post

data class PostResponse(
        val id: Long,
        val title: String,
        val author: String,
        val content: String) {

}

// Post to PostResponse DTO
fun Post.toPostResponse() = PostResponse(
        id = this.id,
        title = this.title,
        author = this.author,
        content = this.content
)
