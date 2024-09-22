package com.example.tododemoapp.common.presentation.exception
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @RestContoller 예외 발생 시, 공통 처리
 *
 **/
@RestControllerAdvice
class GlobalExceptionHandler {

    // 사용자 정의 Exception 처리
    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException): ResponseEntity<ErrorResponse> {

        val errorResponse = ErrorResponse(
            error = e.errorCode.name,
            message = e.errorCode.message
        )

        return ResponseEntity(errorResponse, e.errorCode.httpStatus)
    }

    data class ErrorResponse(
        val error: String,
        val message: String?
    )
}