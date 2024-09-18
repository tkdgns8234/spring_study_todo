package com.example.tododemoapp.todo.application

import com.example.tododemoapp.common.presentation.toEntity
import com.example.tododemoapp.todo.presentation.dto.CreateTodoDTO
import com.example.tododemoapp.todo.presentation.dto.UpdateTodoDTO
import com.example.tododemoapp.todo.domain.Todo
import com.example.tododemoapp.todo.domain.TodoJpaRepository
import com.example.tododemoapp.user.domain.UserJpaRepository
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoJpaRepository: TodoJpaRepository,
    private val userJpaRepository: UserJpaRepository,
) {
    fun findByTodoId(todoId: Long): Todo? = todoJpaRepository.findById(todoId).orElse(null)

    fun findByUserId(userId: Long): List<Todo> {
        val user = userJpaRepository.findById(userId)
            .orElseThrow { IllegalArgumentException("user not found") }

        return todoJpaRepository.findByUser(user)
    }

    fun create(dto: CreateTodoDTO): Todo {
        val user = userJpaRepository.findById(dto.userId)
            .orElseThrow { IllegalArgumentException("user not found") }

        val todoEntity = dto.toEntity(user)
        return todoJpaRepository.save(todoEntity)
    }

    fun update(dto: UpdateTodoDTO): Todo {
        val user = userJpaRepository.findById(dto.userId)
            .orElseThrow { IllegalArgumentException("user not found") }

        val todo = todoJpaRepository.findById(dto.todoId)
            .orElseThrow { IllegalArgumentException("todo not found") }

        if (user.id != todo.user.id) {
            throw IllegalAccessException()
        }

        val todoEntity = dto.toEntity(user)
        return todoJpaRepository.save(todoEntity)
    }


    fun delete(todoId: Long, userId: Long) {
        val user = userJpaRepository.findById(userId)
            .orElseThrow { IllegalArgumentException("user not found") }
        val todo = todoJpaRepository.findById(todoId)
            .orElseThrow { IllegalArgumentException("todo not found") }

        if (user.id != todo.user.id) {
            throw IllegalAccessException()
        }

        todoJpaRepository.deleteById(todoId)
    }
}