package com.example.tododemoapp.todo.presentation

import com.example.tododemoapp.todo.presentation.dto.CreateTodoDTO
import com.example.tododemoapp.todo.presentation.dto.UpdateTodoDTO
import com.example.tododemoapp.todo.domain.Todo
import com.example.tododemoapp.todo.application.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

// TODO: user 정보 파라미터로 전달받는것이 아니라 token or session 방식으로 처리
@RestController
@RequestMapping("/todos")
class TodoController(
    private val todoService: TodoService,
) {

    @GetMapping("/{id}")
    fun getTodoById(@PathVariable id: Long) = todoService.findByTodoId(id)

    @GetMapping
    fun getAllTodosByUserId(@RequestParam userId: Long) = todoService.findByUserId(userId)

    @PostMapping
    fun createTodo(@RequestBody dto: CreateTodoDTO) = todoService.create(dto)

    @PutMapping
    fun updateTodo(@RequestBody dto: UpdateTodoDTO) = todoService.update(dto)

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable id: Long, @RequestParam userId: Long): ResponseEntity<Void> {
        todoService.delete(todoId = id, userId = userId)
        return ResponseEntity.noContent().build()
    }
}