package com.yoong.sunnyside.domain.alarm.dto.response

import com.yoong.sunnyside.domain.alarm.entity.Alarm

data class AlarmResponse(
    val projectFunction: String,
    val message: String,
    val isRead: Boolean,
){
    companion object {
        fun from(alarm: Alarm): AlarmResponse{
            return AlarmResponse(
                projectFunction = alarm.projectFunc.name,
                message = alarm.message,
                isRead = alarm.isRead,
            )
        }
    }
}
