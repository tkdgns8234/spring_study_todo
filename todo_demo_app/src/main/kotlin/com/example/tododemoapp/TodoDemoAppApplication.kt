package com.example.tododemoapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


/**
 *  ./gradlew bootrun -Dspring.profile.active=postgres
 *  ./gradlew bootrun -Dspring.profile.active=h2
 **/

@SpringBootApplication
@EnableJpaAuditing
class TodoDemoAppApplication

fun main(args: Array<String>) {
    runApplication<TodoDemoAppApplication>(*args)
}
