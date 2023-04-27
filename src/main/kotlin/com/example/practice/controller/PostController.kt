package com.example.practice.controller

import com.example.practice.domain.dto.PostResponse
import com.example.practice.domain.dto.PostUpdateDto
import com.example.practice.domain.dto.toPostResponse
import com.example.practice.exception.CommonException
import com.example.practice.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(
        val postService: PostService) {

    @GetMapping("/posts")
    fun findAll(): ResponseEntity<List<PostResponse>> {
        val posts = postService.getPosts()
        return ResponseEntity.ok(posts.map { it.toPostResponse() })
    }

    @GetMapping("/post/{id}")
    fun findPost(@PathVariable id: Long): ResponseEntity<PostResponse> {
        val post = postService.getPost(id) ?: throw CommonException("ERR_404", "no posts", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(post.toPostResponse())
    }

    @PutMapping("/post/{id}")
    fun updatePost(
            @PathVariable id: Long,
            @RequestBody updateDto: PostUpdateDto): ResponseEntity<PostResponse> {
        val updatePost = postService.updatePost(id, updateDto) ?: throw CommonException("ERR_404", "no posts", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(updatePost.toPostResponse())
    }
}