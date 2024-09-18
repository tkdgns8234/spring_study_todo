package com.example.tododemoapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoDemoAppApplication

fun main(args: Array<String>) {
    runApplication<TodoDemoAppApplication>(*args)
}
