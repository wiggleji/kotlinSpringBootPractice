package com.example.practice.service

import com.example.practice.domain.Post
import com.example.practice.domain.dto.PostCreateDto
import com.example.practice.domain.dto.PostUpdateDto
import com.example.practice.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PathVariable

@Service
@Transactional(readOnly = true)
class PostService (
        val postRepository: PostRepository
){
    @Transactional
    fun createPost(createDto: PostCreateDto): Post = postRepository.save(createDto.toEntity())

    fun getPosts(): List<Post>  = postRepository.findAll()

    fun getPost(id:Long): Post? = postRepository.findPostById(id) ?: throw IllegalStateException("no posts")

    @Transactional
    fun updatePost(
            id: Long,
            updateDto: PostUpdateDto
    ): Post {
        postRepository.findPostById(id) ?: throw IllegalStateException("no posts")
        return postRepository.save(updateDto.toEntity(id))
    }
}