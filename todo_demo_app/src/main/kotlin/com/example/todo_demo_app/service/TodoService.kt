package com.example.todo_demo_app.service

import com.example.todo_demo_app.common.toEntity
import com.example.todo_demo_app.dto.CreateTodoDTO
import com.example.todo_demo_app.dto.UpdateTodoDTO
import com.example.todo_demo_app.entity.Todo
import com.example.todo_demo_app.repository.TodoRepository
import com.example.todo_demo_app.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository,
) {
    fun findByTodoId(todoId: Long): Todo? = todoRepository.findById(todoId).orElse(null)

    fun findByUserId(userId: Long): List<Todo> {
        val user = userRepository.findById(userId)
            .orElseThrow { IllegalArgumentException("user not found") }

        return todoRepository.findByUser(user)
    }

    fun create(dto: CreateTodoDTO): Todo {
        val user = userRepository.findById(dto.userId)
            .orElseThrow { IllegalArgumentException("user not found") }

        val todoEntity = dto.toEntity(user)
        return todoRepository.save(todoEntity)
    }

    fun update(dto: UpdateTodoDTO): Todo {
        val user = userRepository.findById(dto.userId)
            .orElseThrow { IllegalArgumentException("user not found") }

        val todo = todoRepository.findById(dto.todoId)
            .orElseThrow { IllegalArgumentException("todo not found") }

        if (user.id != todo.user.id) {
            throw IllegalAccessException()
        }

        val todoEntity = dto.toEntity(user)
        return todoRepository.save(todoEntity)
    }


    fun delete(todoId: Long, userId: Long) {
        val user = userRepository.findById(userId)
            .orElseThrow { IllegalArgumentException("user not found") }
        val todo = todoRepository.findById(todoId)
            .orElseThrow { IllegalArgumentException("todo not found") }

        if (user.id != todo.user.id) {
            throw IllegalAccessException()
        }

        todoRepository.deleteById(todoId)
    }
}