package com.example.practice.controller.dto

data class ErrorResponse (
    val errorCode: String,
    val message: String?
)