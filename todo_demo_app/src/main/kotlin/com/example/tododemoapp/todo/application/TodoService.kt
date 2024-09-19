package com.example.tododemoapp.todo.application

import com.example.tododemoapp.common.presentation.exception.CustomException
import com.example.tododemoapp.common.presentation.exception.ErrorCode
import com.example.tododemoapp.todo.presentation.dto.CreateTodoDTO
import com.example.tododemoapp.todo.presentation.dto.UpdateTodoDTO
import com.example.tododemoapp.todo.domain.Todo
import com.example.tododemoapp.todo.domain.TodoJpaRepository
import com.example.tododemoapp.user.application.UserService
import jakarta.transaction.Transactional
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoJpaRepository: TodoJpaRepository,
    private val userService: UserService
) {
    fun findByTodoId(todoId: Long): Todo {
        return todoJpaRepository.findById(todoId)
            .orElseThrow { NotFoundException() }
    }

    fun findByUserId(userId: Long): List<Todo> {
        val user = userService.findById(userId)

        return todoJpaRepository.findByUser(user)
    }

    @Transactional
    fun create(dto: CreateTodoDTO): Todo {
        val user = userService.findById(dto.userId)
        val todoEntity = dto.toEntity(user)

        return todoJpaRepository.save(todoEntity)
    }

    @Transactional
    fun update(dto: UpdateTodoDTO): Todo {
        val user = userService.findById(dto.userId)
        val todo = this.findByTodoId(dto.todoId)

        if (user.id != todo.user.id) {
            throw CustomException(ErrorCode.ACCESS_DENIED)
        }

        // update 및 event 발행
        todo.update(dto.title, dto.description, dto.completed)

        // save 작업 commit 이후 이벤트 발생
        return todoJpaRepository.save(todo)
    }

    @Transactional
    fun delete(todoId: Long, userId: Long) {
        val user = userService.findById(userId)
        val todo = this.findByTodoId(todoId)

        if (user.id != todo.user.id) {
            throw CustomException(ErrorCode.ACCESS_DENIED)
        }

        todoJpaRepository.deleteById(todoId)
    }
}