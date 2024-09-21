package com.example.tododemoapp.todo.presentation.mapper

import com.example.tododemoapp.todo.domain.Todo
import com.example.tododemoapp.todo.presentation.dto.CreateTodoDTO
import com.example.tododemoapp.user.domain.User

fun CreateTodoDTO.toEntity(user: User): Todo {
    return Todo(
        title = this.title,
        description = this.description,
        completed = this.completed,
        user = user
    )
}