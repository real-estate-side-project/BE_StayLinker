package com.yoong.sunnyside.domain.consumer.dto

import java.util.Base64

data class AlienRegistrationCardRequest(
    val foreignJumin: String, // 외국인 등록 번호
    val issueDate: String, // 발급 일자
    val serialNumber: String,
){

    fun toEncrypt(): AlienRegistrationCardRequest {
        return AlienRegistrationCardRequest(
            foreignJumin = Base64.getEncoder().encodeToString(this.foreignJumin.toByteArray()),
            issueDate = Base64.getEncoder().encodeToString(this.issueDate.toByteArray()),
            serialNumber = Base64.getEncoder().encodeToString(this.serialNumber.toByteArray()),
        )
    }

    fun toIssueDateEncrypt(): AlienRegistrationCardRequest {
        return AlienRegistrationCardRequest(
            foreignJumin = foreignJumin,
            issueDate = Base64.getEncoder().encodeToString(this.issueDate.toByteArray()),
            serialNumber = this.serialNumber,
        )
    }
}
