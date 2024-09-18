package com.example.tododemoapp.todo.application

import com.example.tododemoapp.common.presentation.exception.CustomException
import com.example.tododemoapp.common.presentation.exception.ErrorCode
import com.example.tododemoapp.todo.presentation.dto.CreateTodoDTO
import com.example.tododemoapp.todo.presentation.dto.UpdateTodoDTO
import com.example.tododemoapp.todo.domain.Todo
import com.example.tododemoapp.todo.domain.TodoJpaRepository
import com.example.tododemoapp.user.domain.UserJpaRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoJpaRepository: TodoJpaRepository,
    private val userJpaRepository: UserJpaRepository,
) {
    fun findByTodoId(todoId: Long): Todo {
        return todoJpaRepository.findById(todoId)
            .orElseThrow { NotFoundException() }
    }

    fun findByUserId(userId: Long): List<Todo> {
        val user = userJpaRepository.findById(userId)
            .orElseThrow { CustomException(ErrorCode.USER_NOT_FOUND) }

        return todoJpaRepository.findByUser(user)
    }

    fun create(dto: CreateTodoDTO): Todo {
        val user = userJpaRepository.findById(dto.userId)
            .orElseThrow { CustomException(ErrorCode.USER_NOT_FOUND) }

        val todoEntity = dto.toEntity(user)
        return todoJpaRepository.save(todoEntity)
    }

    fun update(dto: UpdateTodoDTO): Todo {
        val user = userJpaRepository.findById(dto.userId)
            .orElseThrow { CustomException(ErrorCode.USER_NOT_FOUND) }

        val todo = todoJpaRepository.findById(dto.todoId)
            .orElseThrow { CustomException(ErrorCode.TODO_NOT_FOUND) }

        if (user.id != todo.user.id) {
            throw CustomException(ErrorCode.ACCESS_DENIED)
        }

        todo.updateFromDTO(dto)
        return todoJpaRepository.save(todo)
    }

    fun delete(todoId: Long, userId: Long) {
        val user = userJpaRepository.findById(userId)
            .orElseThrow { CustomException(ErrorCode.USER_NOT_FOUND) }

        val todo = todoJpaRepository.findById(todoId)
            .orElseThrow { CustomException(ErrorCode.TODO_NOT_FOUND) }

        if (user.id != todo.user.id) {
            throw CustomException(ErrorCode.ACCESS_DENIED)
        }

        todoJpaRepository.deleteById(todoId)
    }
}