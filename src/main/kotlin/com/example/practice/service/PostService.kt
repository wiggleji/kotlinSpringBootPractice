package com.example.practice.service

import com.example.practice.domain.Post
import com.example.practice.domain.dto.PostCreateDto
import com.example.practice.domain.dto.PostUpdateDto
import com.example.practice.exception.CommonException
import com.example.practice.repository.PostRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostService (
        val postRepository: PostRepository
){
    @Transactional
    fun createPost(createDto: PostCreateDto): Post = postRepository.save(createDto.toEntity())

    fun getPosts(): List<Post>  = postRepository.findAll()

    fun getPost(id:Long): Post? = postRepository.findPostById(id)

    @Transactional
    fun updatePost(
            id: Long,
            updateDto: PostUpdateDto
    ): Post? {
        postRepository.findPostById(id) ?: return null
        return postRepository.save(updateDto.toEntity(id))
    }
}