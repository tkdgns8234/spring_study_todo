package com.example.todo_demo_app.service

import com.example.todo_demo_app.entity.User
import com.example.todo_demo_app.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun findAll(): List<User> = userRepository.findAll()

    fun findById(id: Long): User? = userRepository.findById(id).orElse(null)

    fun findByEmail(email: String): User? = userRepository.findByEmail(email)

    fun save(auth: User): User = userRepository.save(auth)

    fun deleteById(id: Long) = userRepository.deleteById(id)
}