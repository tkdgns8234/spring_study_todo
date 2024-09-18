package com.example.tododemoapp.common

import com.example.tododemoapp.dto.CreateTodoDTO
import com.example.tododemoapp.dto.UpdateTodoDTO
import com.example.tododemoapp.entity.Todo
import com.example.tododemoapp.entity.User

fun CreateTodoDTO.toEntity(user: User): Todo {
    return Todo(
        title = this.title,
        description = this.description,
        completed = this.completed,
        user = user
    )
}

fun UpdateTodoDTO.toEntity(user: User): Todo {
    return Todo(
        id = this.todoId,
        title = this.title,
        description = this.description,
        completed = this.completed,
        user = user
    )
}