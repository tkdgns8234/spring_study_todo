package com.example.tododemoapp.todo.presentation

import com.example.tododemoapp.todo.presentation.dto.CreateTodoDTO
import com.example.tododemoapp.todo.presentation.dto.UpdateTodoDTO
import com.example.tododemoapp.todo.domain.Todo
import com.example.tododemoapp.todo.application.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todos")
class TodoController(
    private val todoService: TodoService,
) {

    @GetMapping("/{id}")
    fun getTodoById(@PathVariable id: Long): ResponseEntity<Todo> {
        val todo = todoService.findByTodoId(id)
        return if (todo == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(todo)
        }
    }

    // TODO: user 정보 파라미터로 전달받는것이 아니라 token or session 방식
    @GetMapping
    fun getAllTodosByUserId(@RequestParam userId: Long): ResponseEntity<List<Todo>> {
        return try {
            val todos = todoService.findByUserId(userId)
            ResponseEntity.ok(todos)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createTodo(@RequestBody dto: CreateTodoDTO): ResponseEntity<Todo> {
        return try {
            val todo = todoService.create(dto)
            ResponseEntity.ok(todo)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping
    fun updateTodo(@RequestBody dto: UpdateTodoDTO): ResponseEntity<Todo> {
        return try {
            val todo = todoService.update(dto)
            ResponseEntity.ok(todo)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        } catch (e: IllegalAccessException) {
            ResponseEntity(HttpStatus.FORBIDDEN)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable id: Long, @RequestParam userId: Long): ResponseEntity<Void> {
        return try {
            todoService.delete(todoId = id, userId = userId)
            ResponseEntity.noContent().build()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        } catch (e: IllegalAccessException) {
            ResponseEntity(HttpStatus.FORBIDDEN)
        }
    }
}