package com.example.tododemoapp.common.presentation.exception
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @RestContoller 예외 발생 시, 공통 처리
 *
 * Spring 에서 지원하는 예외클래스는 별도 처리하지 않아도 됨
 * e.g) NotFoundException 발생한 경우 자동으로 return 코드 404 반환
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