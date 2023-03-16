package com.example.practice.repository

import com.example.practice.domain.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface PostRepository : JpaRepository<Post, Long> {
    fun findByTitle(title: String): Post?

    fun findByAuthor(name: String): Post?
}