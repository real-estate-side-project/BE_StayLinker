package com.yoong.sunnyside.domain.faq.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import com.yoong.sunnyside.domain.faq.entity.Faq
import com.yoong.sunnyside.domain.faq.entity.QFaq
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository

@Repository
class FaqRepositoryImpl(
    @PersistenceContext
    private val em: EntityManager
) : FaqQuerydslRepository {
    private val queryFactory = JPAQueryFactory(em)
    private val faq = QFaq.faq

}