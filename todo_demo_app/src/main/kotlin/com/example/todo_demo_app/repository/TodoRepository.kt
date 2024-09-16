package com.example.todo_demo_app.repository

import com.example.todo_demo_app.entity.Todo
import com.example.todo_demo_app.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository: JpaRepository<Todo, Long> {
    fun findByUser(user: User): List<Todo>
}