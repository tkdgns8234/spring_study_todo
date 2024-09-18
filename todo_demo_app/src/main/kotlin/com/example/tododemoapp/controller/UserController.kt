package com.example.tododemoapp.controller

import com.example.tododemoapp.entity.User
import com.example.tododemoapp.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    fun getUserById(@PathVariable id: Long) : ResponseEntity<User> {
        val user = userService.findById(id)
        return if (user == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(user)
        }
    }

    @PostMapping
    fun createUser(@RequestBody user: User) : ResponseEntity<User> {
        // email로 중복 체크
        return if (userService.findByEmail(user.email) != null) {
            ResponseEntity(HttpStatus.CONFLICT)
        } else {
            val user = userService.save(user)
            return ResponseEntity.ok(user)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) : ResponseEntity<Void> {
        return if (userService.findById(id) == null) {
            ResponseEntity.notFound().build()
        } else {
            userService.deleteById(id)
            ResponseEntity.noContent().build()
        }
    }
}