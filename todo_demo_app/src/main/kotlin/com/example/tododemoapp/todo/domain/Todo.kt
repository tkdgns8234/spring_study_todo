package com.example.tododemoapp.todo.domain

import com.example.tododemoapp.user.domain.User
import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import jakarta.persistence.*
import org.springframework.data.domain.AbstractAggregateRoot
import java.time.LocalDateTime

/**
 * AbstractAggregateRoot
 * - DDD 설계 구조에서 AggregateRoot를 통해 EDA 방식으로 처리하기위한 helper class
 * - 외부 서비스들과 의존하지 않고 유연하게 통신할 수 있다
 * - 신규 서비스를 붙일때 이벤트를 구독하기만 하면 된다.
 **/
@Entity
@Table(name = "todos")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
data class Todo (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = true)
    var description: String? = null,

    @Column(nullable = false)
    var completed: Boolean = false,

    /** 수정 일시 */
    @Column(nullable = false)
    var updateDate: LocalDateTime = LocalDateTime.now(),

    /** 등록 일시 */
    @Column(nullable = false)
    var regDate: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User

) : AbstractAggregateRoot<Todo>() {

    /** 서비스 계층은 어플리케이션단 흐름을 관리하고 각 domain 에서 비즈니스 로직을 캡슐화 하여 처리한다.*/
    fun update(title: String?, description: String?, completed: Boolean?) {
        title?.let { this.title = it }
        description?.let { this.description = it }
        completed?.let { this.completed = it }

        this.updateDate = LocalDateTime.now()

        registerEvent(TodoUpdateEvent(this))
    }

    override fun toString(): String {
        return "($title, $description, $completed, $updateDate, $regDate)"
    }
}