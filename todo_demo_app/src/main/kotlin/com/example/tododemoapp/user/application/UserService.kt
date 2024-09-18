package com.example.tododemoapp.user.application

import com.example.tododemoapp.user.domain.User
import com.example.tododemoapp.user.domain.UserJpaRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userJpaRepository: UserJpaRepository) {
    fun findAll(): List<User> = userJpaRepository.findAll()

    fun findById(id: Long): User? = userJpaRepository.findById(id).orElse(null)

    fun findByEmail(email: String): User? = userJpaRepository.findByEmail(email)

    fun save(auth: User): User = userJpaRepository.save(auth)

    fun deleteById(id: Long) = userJpaRepository.deleteById(id)
}