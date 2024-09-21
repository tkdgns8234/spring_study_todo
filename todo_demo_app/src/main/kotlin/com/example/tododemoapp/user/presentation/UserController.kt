package com.example.tododemoapp.user.presentation

import com.example.tododemoapp.user.domain.User
import com.example.tododemoapp.user.application.UserService
import com.example.tododemoapp.user.presentation.dto.UserCreateDTO
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers() : List<User> = userService.findAll()

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long) = userService.findById(id)

    @PostMapping
    fun createUser(@RequestBody dto: UserCreateDTO) = userService.save(dto)

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) = userService.deleteById(id)
}