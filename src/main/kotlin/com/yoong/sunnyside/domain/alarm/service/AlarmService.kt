package com.yoong.sunnyside.domain.alarm.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.alarm.dto.response.SubscribeAlarmResponse
import com.yoong.sunnyside.domain.alarm.dto.response.AlarmResponse
import org.springframework.stereotype.Service

@Service
class AlarmService {

    fun getAlarms(): List<AlarmResponse> {
        TODO()
    }

    fun getUnReadAlarms(): List<AlarmResponse> {
        TODO()
    }

    fun getAlarm(alarmId: Long): AlarmResponse {
        TODO()
    }

    fun readAlarm(alarmId: Long): DefaultResponse {
        TODO()
    }

    fun readAllAlarm(): DefaultResponse {
        TODO()
    }

    fun deleteAlarm(alarmId: Long): DefaultResponse {
        TODO()
    }

    fun sendAlarms(): DefaultResponse {
        TODO()
    }

    fun subscribeAlarm(): DefaultResponse {
        TODO()
    }

    fun unSubscribeAlarm(): DefaultResponse {
        TODO()
    }

    fun getSubscribeStatus(): List<SubscribeAlarmResponse> {
        TODO()
    }


}