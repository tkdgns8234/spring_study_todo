package com.example.tododemoapp

import com.example.tododemoapp.todo.domain.Todo
import com.example.tododemoapp.user.domain.User
import com.example.tododemoapp.todo.infra.TodoRepository
import com.example.tododemoapp.user.infra.UserRepository
import com.example.tododemoapp.todo.application.TodoService
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*

/**
 * StringSpec: kotlin test framework style
 * **/

class TodoServiceTest : StringSpec({
    val todoRepository = mockk<TodoRepository>()
    val userRepository = mockk<UserRepository>()
    val todoService = TodoService(todoRepository, userRepository)

    "TODO ID로 TODO 단건 조회" {
        val user = User(id = 1L, name = "정상훈", email = "tkdgns8234@nate.com", password = "top secret")
        val todo = Todo(id = 1L, title = "cat cat", description = "1111", completed = true, user = user)

        every { todoRepository.findById(todo.id) } returns Optional.of(todo)

        todoService.findByTodoId(todo.id) shouldBe todo

        verify(exactly = 1) { todoRepository.findById(todo.id) }
    }

    "유저의 모든 TODO 정보 조회" {
        val user = User(id = 1L, name = "정상훈", email = "tkdgns8234@nate.com", password = "top secret")

        val todos = listOf(
            Todo(id = 1L, title = "cat cat", description = "1111", completed = true, user = user),
            Todo(id = 2L, title = "dog dog", description = "2222", completed = true, user = user)
        )

        every { userRepository.findById(user.id) } returns Optional.of(user)
        every { todoRepository.findByUser(user) } returns todos

        todoService.findByUserId(user.id) shouldBe todos

        verify(exactly = 1) { userRepository.findById(user.id) }
        verify(exactly = 1) { todoRepository.findByUser(user) }
    }

    "TODO 저장" {
        val user = User(id = 1L, name = "정상훈", email = "tkdgns8234@nate.com", password = "top secret")
        val todo = Todo(id = 1L, title = "cat cat", description = "1111", completed = true, user = user)

        every { userRepository.findById(todo.user.id) } returns Optional.of(user)
        every { todoRepository.save(todo) } returns todo

        todoService.create(todo) shouldBe todo

        verify(exactly = 1) { todoRepository.save(todo) }
    }

    "TODO 삭제" {
        val user = User(id = 1L, name = "정상훈", email = "tkdgns8234@nate.com", password = "top secret")
        val todo = Todo(id = 1L, title = "cat cat", description = "1111", completed = true, user = user)

        every { userRepository.findById(user.id) } returns Optional.of(user)
        every { todoRepository.findById(todo.id) } returns Optional.of(todo)
        every { todoRepository.deleteById(todo.id) } returns Unit

        todoService.delete(user.id, todo.id)

        verify(exactly = 1) { todoRepository.deleteById(todo.id) }
    }
})