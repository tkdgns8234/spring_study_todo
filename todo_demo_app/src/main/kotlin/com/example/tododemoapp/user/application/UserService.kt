package com.example.tododemoapp.user.application

import com.example.tododemoapp.common.presentation.exception.CustomException
import com.example.tododemoapp.common.presentation.exception.ErrorCode
import com.example.tododemoapp.user.domain.User
import com.example.tododemoapp.user.domain.UserJpaRepository
import jakarta.transaction.Transactional
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException.Conflict

@Service
class UserService(private val userJpaRepository: UserJpaRepository) {
    fun findAll(): List<User> = userJpaRepository.findAll()

    fun findById(id: Long): User  {
        return userJpaRepository.findById(id)
            .orElseThrow { NotFoundException() }
    }

    fun findByEmail(email: String): User? = userJpaRepository.findByEmail(email)

    @Transactional
    fun save(user: User): User {
        // Email 중복 체크
        if (findByEmail(user.email) != null) {
            throw CustomException(ErrorCode.DUPLICATE_DATA)
        }

        return userJpaRepository.save(user)
    }

    @Transactional
    fun deleteById(id: Long) {
        if (findById(id) == null) {
            throw NotFoundException()
        }

        userJpaRepository.deleteById(id)
    }
}