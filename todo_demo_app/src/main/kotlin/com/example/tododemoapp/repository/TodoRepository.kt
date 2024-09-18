package com.example.tododemoapp.repository

import com.example.tododemoapp.entity.Todo
import com.example.tododemoapp.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository: JpaRepository<Todo, Long> {
    fun findByUser(user: User): List<Todo>
}