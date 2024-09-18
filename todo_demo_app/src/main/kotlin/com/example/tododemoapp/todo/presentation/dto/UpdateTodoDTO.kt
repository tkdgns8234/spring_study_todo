package com.example.tododemoapp.todo.presentation.dto

data class UpdateTodoDTO(
    val todoId: Long,

    var title: String,

    var description: String? = null,

    var completed: Boolean = false,

    val userId: Long
)
