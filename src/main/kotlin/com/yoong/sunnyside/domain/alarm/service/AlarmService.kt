package com.yoong.sunnyside.domain.alarm.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.ModelNotFoundException
import com.yoong.sunnyside.domain.alarm.dto.response.SubscribeAlarmResponse
import com.yoong.sunnyside.domain.alarm.dto.response.AlarmResponse
import com.yoong.sunnyside.domain.alarm.repository.AlarmRepository
import org.springframework.stereotype.Service

@Service
class AlarmService(
    val alarmRepository: AlarmRepository,
){

    fun getAlarms(id: Long): List<AlarmResponse> {

        val alarms = alarmRepository.findAllByMemberId(id)

        return alarms.map { AlarmResponse.from(it) }
    }

    fun getUnReadAlarms(id: Long): List<AlarmResponse> {

        val alarms = alarmRepository.findAllByUnreadAlarms(id)

        return alarms.map { AlarmResponse.from(it) }
    }

    fun getAlarm(alarmId: Long): AlarmResponse {

        val alarm = alarmRepository.findByIdOrNull(alarmId) ?: throw ModelNotFoundException("alarm not found")

        return AlarmResponse.from(alarm)
    }

    fun readAlarm(alarmId: Long): DefaultResponse {

        val alarm = alarmRepository.findByIdOrNull(alarmId) ?: throw ModelNotFoundException("alarm not found")

        alarm.updateRead()

        return DefaultResponse("alarm readed")
    }

    fun readAllAlarm(id: Long): DefaultResponse {
        val alarms = alarmRepository.findAllByMemberId(id)

        alarms.forEach { alarm -> alarm.updateRead()}

        return DefaultResponse("alarm read")
    }

    fun deleteAlarm(alarmId: Long): DefaultResponse {
        val alarm = alarmRepository.findByIdOrNull(alarmId) ?: throw ModelNotFoundException("alarm not found")

        alarm.delete()

        return DefaultResponse("alarm deleted")
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