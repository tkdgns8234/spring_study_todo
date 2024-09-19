package com.example.tododemoapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


/**
 *  ./gradlew bootrun -Dspring.profile.active=postgres
 *  ./gradlew bootrun -Dspring.profile.active=h2
 **/

@SpringBootApplication
class TodoDemoAppApplication

fun main(args: Array<String>) {
    runApplication<TodoDemoAppApplication>(*args)
}
