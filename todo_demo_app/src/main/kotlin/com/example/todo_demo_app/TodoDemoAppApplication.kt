package com.example.todo_demo_app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoDemoAppApplication

fun main(args: Array<String>) {
    runApplication<TodoDemoAppApplication>(*args)
}
