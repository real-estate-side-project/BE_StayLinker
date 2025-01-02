package com.yoong.sunnyside.domain.faq.repository

import com.yoong.sunnyside.domain.faq.entity.Faq
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface FaqRepository : JpaRepository<Faq, Long>, FaqQuerydslRepository {
    fun findAllByQuestionContains(title: String?, pageable: Pageable): Page<Faq>
}