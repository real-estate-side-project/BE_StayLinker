package com.yoong.sunnyside.domain.alarm.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.alarm.dto.response.AlarmResponse
import com.yoong.sunnyside.domain.alarm.dto.response.SubscribeAlarmResponse
import com.yoong.sunnyside.domain.alarm.service.AlarmService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/alarm")
class AlarmController(
    private val alarmService: AlarmService
){

    @GetMapping
    fun getAlarms(): ResponseEntity<List<AlarmResponse>>
        = ResponseEntity.status(HttpStatus.OK).body(alarmService.getAlarms())

    @GetMapping("/unread")
    fun getUnReadAlarms(): ResponseEntity<List<AlarmResponse>>
            = ResponseEntity.status(HttpStatus.OK).body(alarmService.getUnReadAlarms())

    @GetMapping("/{alarmId}")
    fun getAlarm(
        @PathVariable alarmId: Long,
    ): ResponseEntity<AlarmResponse>
            = ResponseEntity.status(HttpStatus.OK).body(alarmService.getAlarm(alarmId))

    // 알람 읽음 표시 설정
    @PatchMapping("/{alarmId}/read")
    fun readAlarm(
        @PathVariable alarmId: Long,
    ): ResponseEntity<DefaultResponse>
            = ResponseEntity.status(HttpStatus.OK).body(alarmService.readAlarm(alarmId))

    // 알람 전체 읽음 표시 설정
    @PatchMapping("/read-all")
    fun readAllAlarm(): ResponseEntity<DefaultResponse>
            = ResponseEntity.status(HttpStatus.OK).body(alarmService.readAllAlarm())

    @DeleteMapping("/{alarmId}")
    fun deleteAlarm(
        @PathVariable alarmId: Long,
    ): ResponseEntity<DefaultResponse>
            = ResponseEntity.status(HttpStatus.OK).body(alarmService.deleteAlarm(alarmId))

// -----------------------------------------------------------------------------------------
// 아래 API 는 관리자 권한 으로 동작 하거나 장애 발생 시에 수동으로 동작 하게끔 설정 하는 API 입니다
// ------------------------------------------------------------------------------------------

    @PostMapping
    fun sendAlarms(

    ): ResponseEntity<DefaultResponse>
            = ResponseEntity.status(HttpStatus.OK).body(alarmService.sendAlarms())

    // 알람 구독 신청 API
    @PostMapping("/subscribe")
    fun subscribeAlarm(): ResponseEntity<DefaultResponse>
            = ResponseEntity.status(HttpStatus.OK).body(alarmService.subscribeAlarm())

    // 알람 구독 해제 API
    @DeleteMapping("/unsubscribe")
    fun unSubscribeAlarm(): ResponseEntity<DefaultResponse>
            = ResponseEntity.status(HttpStatus.OK).body(alarmService.unSubscribeAlarm())

    // 알람 구독 상태 확인 API
    @DeleteMapping("/subscribe-status")
    fun getSubscribeStatus(): ResponseEntity<List<SubscribeAlarmResponse>>
            = ResponseEntity.status(HttpStatus.OK).body(alarmService.getSubscribeStatus())

}