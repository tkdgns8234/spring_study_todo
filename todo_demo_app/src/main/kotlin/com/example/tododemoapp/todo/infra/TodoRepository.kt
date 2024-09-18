package com.example.tododemoapp.todo.infra

import com.example.tododemoapp.todo.domain.Todo
import com.example.tododemoapp.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository: JpaRepository<Todo, Long> {
    fun findByUser(user: User): List<Todo>
}