package com.example.practice.controller

import com.example.practice.controller.dto.ErrorResponse
import com.example.practice.exception.CommonException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalControllerAdvice {

    @ExceptionHandler(value = [CommonException::class])
    fun exception(e: CommonException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(e.code, e.message),
            e.status)
    }

}