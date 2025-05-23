package com.yoong.sunnyside.domain.alarm.dto.request

import com.yoong.sunnyside.infra.security.MemberRole

data class AlarmRequest(
    val projectFunction: String,
    val memberId: Long,
    val memberRole: MemberRole,
    val message: String
)
