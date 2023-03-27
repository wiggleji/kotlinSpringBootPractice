package com.example.practice.repository

import com.example.practice.domain.Post
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val postRepository: PostRepository) {

    @Test
    fun `When findById then return Post`() {
        val testPost = Post("test post", "test user", "test content")
        entityManager.persist(testPost)
        entityManager.flush()
        entityManager.refresh(testPost)

        // when
        val foundPost: Post? = postRepository.findById(testPost.id).orElse(null)

        // then
        Assertions.assertThat(foundPost).isEqualTo(testPost)
    }

    @Test
    fun `When findByTitle then return Post`() {
        // given
        val testPost = Post("test post", "test user", "test content")
        entityManager.persist(testPost)
        entityManager.flush()

        // when
        val foundPost = postRepository.findByTitle(testPost.title)

        // then
        Assertions.assertThat(foundPost).isEqualTo(testPost)
    }

    @Test
    fun `When findByAuthor then return Post`() {
        // given
        val testPost = Post("test post", "test user", "test content")
        entityManager.persist(testPost)
        entityManager.flush()

        // when
        val foundPost = postRepository.findByAuthor(testPost.author)

        // then
        Assertions.assertThat(foundPost).isEqualTo(testPost)
    }
}