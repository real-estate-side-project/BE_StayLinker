package com.yoong.sunnyside.domain.consumer.dto

data class PasswordRequest(
    val password: String,
    val retryPassword: String
)