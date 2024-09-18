package com.example.tododemoapp.user.application

import com.example.tododemoapp.user.domain.User
import com.example.tododemoapp.user.infra.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun findAll(): List<User> = userRepository.findAll()

    fun findById(id: Long): User? = userRepository.findById(id).orElse(null)

    fun findByEmail(email: String): User? = userRepository.findByEmail(email)

    fun save(auth: User): User = userRepository.save(auth)

    fun deleteById(id: Long) = userRepository.deleteById(id)
}