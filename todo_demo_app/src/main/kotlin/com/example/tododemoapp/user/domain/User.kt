package com.example.tododemoapp.user.domain

import com.example.tododemoapp.todo.domain.Todo
import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import jakarta.persistence.*
import org.hibernate.annotations.BatchSize

/**
 * JsonIdentityInfo: jackson 2.0 이후 신규 추가 (순환 참조 처리)
 **/

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
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
    @BatchSize(size = 10)
    val todos: List<Todo> = mutableListOf()
) {
    override fun toString(): String {
        return "($id,  $name, $email, $password)"
    }
}
