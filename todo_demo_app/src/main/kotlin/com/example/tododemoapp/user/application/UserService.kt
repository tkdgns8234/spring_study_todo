package com.example.tododemoapp.user.application

import com.example.tododemoapp.common.presentation.exception.CustomException
import com.example.tododemoapp.common.presentation.exception.ErrorCode
import com.example.tododemoapp.user.domain.User
import com.example.tododemoapp.user.domain.UserJpaRepository
import com.example.tododemoapp.user.presentation.dto.UserCreateDTO
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserService(private val userJpaRepository: UserJpaRepository) {
    fun findAll(): List<User> = userJpaRepository.findAll()

    fun findById(id: Long): User  {
        return userJpaRepository.findById(id)
            .orElseThrow { CustomException(ErrorCode.USER_NOT_FOUND) }
    }

    fun findByEmail(email: String): User? = userJpaRepository.findByEmail(email)

    @Transactional
    fun save(dto: UserCreateDTO): User {
        // Email 중복 체크
        if (findByEmail(dto.email) != null) {
            throw CustomException(ErrorCode.DUPLICATE_DATA)
        }

        val user = User(
            name = dto.name,
            email = dto.email,
            password = dto.password
        )

        return userJpaRepository.save(user)
    }

    @Transactional
    fun deleteById(id: Long) {
        if (findById(id) == null) {
            throw CustomException(ErrorCode.USER_NOT_FOUND)
        }

        userJpaRepository.deleteById(id)
    }
}