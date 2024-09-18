package com.example.tododemoapp.common.presentation

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @RestContoller 예외 발생 시, 공통 처리
 * TODO:: Custom Exception 설계 후 관리해보기
 * **/
@RestControllerAdvice
class GlobalExceptionHandler {

    /**
     * 404 Not Found
     * NotFoundException class: java 제공 X, Spring 에서 Custom하게 제공하는 Exception class
     * */
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(exception: NotFoundException): ErrorResponse {
        return ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.message)
    }

    data class ErrorResponse(
        val status: Int,
        val message: String?
    )
}