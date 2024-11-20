package com.yoong.sunnyside.infra.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class RedisUtils(
    private val redisTemplate: RedisTemplate<String, Any>
){

    fun setStringData(key: String, value: String, expiredTimeMillis: Long) {
        val duration: Duration = Duration.ofMillis(expiredTimeMillis)
        redisTemplate.opsForValue().set(key, value, duration)
    }

    fun getStringData(key: String): String {
        return redisTemplate.opsForValue()[key] as? String? ?: "존재하지 않는 값입니다."
    }

    fun deleteStringData(key: String) {
        redisTemplate.delete(key)
    }
}