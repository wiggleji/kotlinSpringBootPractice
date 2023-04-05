package com.example.practice.service

import com.example.practice.domain.Post
import com.example.practice.domain.dto.PostCreateDto
import com.example.practice.domain.dto.PostUpdateDto
import com.example.practice.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PathVariable

@Service
class PostService (
        private val postRepository: PostRepository
){
    @Transactional
    fun createPost(createDto: PostCreateDto): Post {
        return postRepository.save(createDto.toEntity())
    }

    fun getPosts(): List<Post> {
        return postRepository.findAll()
    }

    fun getPost(id:Long): Post? {
        return postRepository.findPostById(id) ?: throw IllegalStateException("no posts")
    }

    @Transactional
    fun updatePost(
            @PathVariable postId: Long,
            updateDto: PostUpdateDto
    ): Post {
        postRepository.findPostById(postId) ?: throw IllegalStateException("no posts")
        return postRepository.save(updateDto.toEntity(postId))
    }
}