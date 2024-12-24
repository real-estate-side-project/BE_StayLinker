package com.yoong.sunnyside.domain.koreainfo.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.yoong.sunnyside.common.enum_class.SearchType
import com.yoong.sunnyside.domain.notification.entity.Notification
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class KoreaInfoRepositoryImpl(
    @PersistenceContext
    private val em: EntityManager
) : KoreaInfoQuerydslRepository {
    private val queryFactory = JPAQueryFactory(em)
    override fun findPage(
        pageable: Pageable,
        division: String?,
        searchType: SearchType?,
        keyword: String?
    ): Page<Notification> {
        TODO("Not yet implemented")
    }
}