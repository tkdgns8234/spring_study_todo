package com.example.tododemoapp.user.domain

import com.example.tododemoapp.todo.domain.Todo
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import org.hibernate.annotations.BatchSize

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val password: String,

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    @BatchSize(size = 10)
    val todos: List<Todo> = mutableListOf()
)
