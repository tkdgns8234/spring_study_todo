package com.example.tododemoapp.todo.domain

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class TodoEventListener {

    /** 파라미터 타입으로 이벤트 필터링 */
    @EventListener
    fun todoUpdateListener(event: TodoUpdateEvent) {
        println("todo update event occurred")

        // 외부 서비스 알림 작업 처리
        notifyUserTodoUpdateEvent(event.todo)
    }

    fun notifyUserTodoUpdateEvent(todo: Todo) {
        println("고객님께서 작성하신 TODO 업데이트가 발생하였습니다! 세부 내용: $todo")
    }

}