package com.yoong.sunnyside.domain.admin.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.business.dto.BusinessResponse
import com.yoong.sunnyside.domain.business.dto.BusinessSignupRequest
import com.yoong.sunnyside.domain.business.dto.TempBusinessResponse
import com.yoong.sunnyside.domain.business.entity.Business
import com.yoong.sunnyside.domain.business.repository.BusinessRepository
import com.yoong.sunnyside.domain.business.repository.TempBusinessRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminService(
    private val businessRepository: BusinessRepository,
    private val tempBusinessRepository: TempBusinessRepository
) {

    @Transactional
    fun allowBusiness(id: Long): DefaultResponse {
        val tempBusiness = tempBusinessRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("id $id under review")

        if (businessRepository.existsByBusinessCode(tempBusiness.businessCode))
            throw IllegalArgumentException("business code ${tempBusiness.businessCode} already exists")

        businessRepository.save(
            Business.from(tempBusiness)
        )
        return DefaultResponse("allow business")
    }

    fun getAllApplication(): List<TempBusinessResponse> {
        return tempBusinessRepository.findAll().map { TempBusinessResponse.from(it) }
    }

    fun getAllBusiness(): List<BusinessResponse> {
        return businessRepository.findAll().map { BusinessResponse.from(it) }
    }

}