package com.yoong.sunnyside.domain.faq.dto

import com.yoong.sunnyside.domain.faq.entity.Faq
import java.time.LocalDateTime

data class FaqResponse(
    val id: Long,
    val question: String,
    val answer: String,
    val division: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun from(faq: Faq): FaqResponse {
            return FaqResponse(
                id = faq.id!!,
                question = faq.question,
                answer = faq.answer,
                division = faq.division,
                createdAt = faq.createdAt,
                updatedAt = faq.updatedAt,
            )
        }

    }
}
