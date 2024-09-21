package com.example.tododemoapp.todo.presentation.dto

data class CreateTodoDTO(
    var title: String,

    var description: String? = null,

    var completed: Boolean = false,

    val userId: Long

)