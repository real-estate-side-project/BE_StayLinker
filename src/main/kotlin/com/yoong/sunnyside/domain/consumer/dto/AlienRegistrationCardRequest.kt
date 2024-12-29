package com.yoong.sunnyside.domain.consumer.dto

data class AlienRegistrationCardRequest(
    val rrn: String, // 외국인 등록 번호
    val madeDate: String, // 발급 일자
    val cardSn: String?, // 일련 번호
)
