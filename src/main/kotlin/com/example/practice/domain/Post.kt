package com.example.practice.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Post(
        val title: String,
        val author: String,
        val content: String,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
) : BaseEntity()