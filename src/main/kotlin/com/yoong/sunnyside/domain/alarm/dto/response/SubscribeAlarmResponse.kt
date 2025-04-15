package com.yoong.sunnyside.domain.alarm.dto.response

import java.time.LocalDateTime

data class SubscribeAlarmResponse(
    val projectFunction: String,
    val userId: Long,
    val sessionId: String,       // WebSocket 세션 ID (또는 내부 추적용 UUID)
    val subscribeAt: LocalDateTime,
)
