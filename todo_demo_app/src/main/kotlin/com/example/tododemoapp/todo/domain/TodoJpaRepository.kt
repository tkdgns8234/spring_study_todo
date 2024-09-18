package com.example.tododemoapp.todo.domain

import com.example.tododemoapp.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoJpaRepository: JpaRepository<Todo, Long> {
    fun findByUser(user: User): List<Todo>
}