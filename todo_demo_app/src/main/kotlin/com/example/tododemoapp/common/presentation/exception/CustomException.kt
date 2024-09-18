package com.example.tododemoapp.common.presentation.exception

import org.springframework.http.HttpStatus

class CustomException(
    val errorCode: ErrorCode,
) : RuntimeException(errorCode.message) {
    override val message: String = errorCode.message
}

enum class ErrorCode (
    val httpStatus: HttpStatus,
    val message: String
) {
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "access denied"),
    DUPLICATE_DATA(HttpStatus.CONFLICT, "duplicate data"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found"),
    TODO_NOT_FOUND(HttpStatus.NOT_FOUND, "todo not found"),
}