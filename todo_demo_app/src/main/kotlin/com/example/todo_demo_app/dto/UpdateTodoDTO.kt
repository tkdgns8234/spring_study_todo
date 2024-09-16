package com.example.todo_demo_app.dto

data class UpdateTodoDTO(
    val todoId: Long,

    var title: String,

    var description: String? = null,

    var completed: Boolean = false,

    val userId: Long
)
