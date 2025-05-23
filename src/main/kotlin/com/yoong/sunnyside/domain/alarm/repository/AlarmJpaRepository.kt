package com.yoong.sunnyside.domain.alarm.repository

import com.yoong.sunnyside.domain.alarm.entity.Alarm
import org.springframework.data.jpa.repository.JpaRepository

interface AlarmJpaRepository: JpaRepository<Alarm, Long> {
}