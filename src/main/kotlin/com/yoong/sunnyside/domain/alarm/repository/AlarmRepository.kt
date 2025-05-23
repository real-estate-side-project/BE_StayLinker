package com.yoong.sunnyside.domain.alarm.repository

import com.yoong.sunnyside.domain.alarm.entity.Alarm

interface AlarmRepository {

    fun findAllByMemberId(memberId: Long): List<Alarm>

    fun findAllByUnreadAlarms(memberId: Long): List<Alarm>

    fun findByIdOrNull(id: Long): Alarm?
}