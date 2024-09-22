package com.example.tododemoapp.user.domain

import com.example.tododemoapp.todo.domain.Todo
import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import jakarta.persistence.*
import org.hibernate.annotations.BatchSize

/**
 * JsonIdentityInfo: jackson 2.0 이후 신규 추가 (순환 참조 처리)
 **/

/** TODO::User 정보 암호화하기 (password: 단방향 암호화, 그외 사용자 식별정보: 양방향 암호화)
 * aws kms 를 통해 키 관리 및 암/복호화 알고리즘을 적용하여 보다 쉽게 데이터 암호화 처리 가능.
 * aws kms <-> rds와의 연동 기능도 존재하는듯 보임
 */


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
