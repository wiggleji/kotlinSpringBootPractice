package com.example.practice.controller

import com.example.practice.domain.Post
import com.example.practice.security.SecurityConfig
import com.example.practice.service.PostService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

import org.mockito.BDDMockito.given;
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension::class)
@WebMvcTest(value = [PostController::class],
    includeFilters = [ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = arrayOf(SecurityConfig::class))])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostControllerTest @Autowired constructor(
        @MockBean val postService: PostService) {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `findAll() returns two posts`() {
        // given
        val uri = "/posts"

        val postResponses = listOf(
                Post("testTitle1", "testAuthor", "testContent1"),
                Post("testTitle2", "testAuthor", "testContent2"))
        given(postService.getPosts()).willReturn(postResponses)

        // when

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(postResponses[0].title))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value(postResponses[1].title))
    }
}