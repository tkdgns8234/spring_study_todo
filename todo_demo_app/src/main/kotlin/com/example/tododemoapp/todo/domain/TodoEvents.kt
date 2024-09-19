package com.example.tododemoapp.todo.domain

/** kotlin: ApplcationEvent 상속받지 않아도 됨 (spring 지원) */
data class TodoUpdateEvent(
    val todo: Todo
)