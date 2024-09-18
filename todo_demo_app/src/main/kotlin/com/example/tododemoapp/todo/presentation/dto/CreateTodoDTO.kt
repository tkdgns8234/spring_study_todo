package com.example.tododemoapp.todo.presentation.dto

import com.example.tododemoapp.todo.domain.Todo
import com.example.tododemoapp.user.domain.User

data class CreateTodoDTO(
    var title: String,

    var description: String? = null,

    var completed: Boolean = false,

    val userId: Long

) {
    fun toEntity(user: User): Todo {
        return Todo(
            title = this.title,
            description = this.description,
            completed = this.completed,
            user = user
        )
    }
}
