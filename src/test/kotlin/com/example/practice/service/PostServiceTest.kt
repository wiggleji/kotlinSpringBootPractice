package com.example.practice.service

import com.example.practice.domain.Post
import com.example.practice.domain.dto.PostCreateDto
import com.example.practice.domain.dto.PostUpdateDto
import com.example.practice.exception.CommonException
import com.example.practice.repository.PostRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostServiceTest @Autowired constructor(
    val postService: PostService,
    val postRepository: PostRepository) {

    @Test
    fun `createPost with PostCreateDto`() {
        // given
        val createDto = PostCreateDto(
                title = "testPost",
                content = "testContent",
                author = "testAuthor"
        )

        // when
        val post = postService.createPost(createDto)

        // then
        Assertions.assertInstanceOf(Long::class.javaObjectType, post.id)
        Assertions.assertEquals(post.title, createDto.title)
    }

    @Test
    fun `getPosts returns two posts`() {
        // given
        val post1 = Post("post1", "post1Content", "testAuthor")
        val post2 = Post("post2", "post2Content", "testAuthor")
        postRepository.save(post1)
        postRepository.save(post2)

        // when
        val posts = postService.getPosts()

        // then
        Assertions.assertEquals(posts.size, 2)
    }

    @Test
    fun `getPost returns valid post`() {
        // given
        val post = Post("post", "postContent", "testAuthor")
        val savedPost = postRepository.save(post)

        // when
        val inquiredPost = postService.getPost(savedPost.id)

        // then
        Assertions.assertEquals(inquiredPost?.id, savedPost.id)
    }

    @Test
    fun `getPost returns null with invalid post id`() {
        // given

        // when
        val inquiredPost = postService.getPost(9999)

        // then
        Assertions.assertEquals(inquiredPost, null)
    }

    @Test
    fun `getPost with invalid id throws IllegalStateException`() {
        // given
        val post = Post("post", "postContent", "testAuthor")
        val savedPost = postRepository.save(post)

        // when

        // then
        Assertions.assertEquals(postService.getPost(-9999), null)
    }

    @Test
    fun `updatePost succeed with valid post data`() {
        // given
        val post = Post("post", "postContent", "testAuthor")
        val savedPost = postRepository.save(post)

        val updateDto = PostUpdateDto(
                id = savedPost.id,
                title = "newTitle",
                content = post.content,
                author = post.author
        )

        // when
        val updatedPost = postService.updatePost(savedPost.id, updateDto)

        // then
        Assertions.assertEquals(updatedPost?.title, updateDto.title)
        Assertions.assertEquals(updatedPost?.content, savedPost.content)
    }

    @Test
    fun `updatePost returns null with invalid post id`() {
        // given
        val updateDto = PostUpdateDto(
            id = 9999,
            title = "invalidPost",
            content = "invalidPostContent",
            author = "invalidAuthor"
        )

        // when
        val inquiredPost = postService.updatePost(9999, updateDto)

        // then
        Assertions.assertEquals(inquiredPost, null)
    }
}