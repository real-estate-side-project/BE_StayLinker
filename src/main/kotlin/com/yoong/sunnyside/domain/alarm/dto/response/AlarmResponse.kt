package com.yoong.sunnyside.domain.alarm.dto.response

data class AlarmResponse(
    val projectFunction: String,
    val message: String,
    val isRead: Boolean,
)
