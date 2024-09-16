package com.example.todo_demo_app.common

import com.example.todo_demo_app.dto.CreateTodoDTO
import com.example.todo_demo_app.dto.UpdateTodoDTO
import com.example.todo_demo_app.entity.Todo
import com.example.todo_demo_app.entity.User

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