package com.yoong.sunnyside.domain.alarm.repository

import com.yoong.sunnyside.domain.alarm.entity.Alarm
import org.springframework.stereotype.Repository

@Repository
class AlarmRepositoryImpl(
    private val jpaRepository: AlarmJpaRepository
): AlarmRepository {

    override fun findAllByMemberId(memberId: Long): List<Alarm> {
        TODO("Not yet implemented")
    }

    override fun findAllByUnreadAlarms(memberId: Long): List<Alarm> {
        TODO("Not yet implemented")
    }

    override fun findByIdOrNull(id: Long): Alarm? {
        TODO("Not yet implemented")
    }
}