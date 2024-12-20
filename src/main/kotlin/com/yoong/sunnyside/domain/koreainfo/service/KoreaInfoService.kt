package com.yoong.sunnyside.domain.koreainfo.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.koreainfo.dto.CreateKoreaInfoRequest
import com.yoong.sunnyside.domain.koreainfo.dto.KoreaInfoResponse
import com.yoong.sunnyside.domain.koreainfo.dto.UpdateKoreaInfoRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface KoreaInfoService {
    fun createKoreaInfo(createKoreaInfoRequest: CreateKoreaInfoRequest, adminId: Long): DefaultResponse
    fun updateKoreaInfo(koreaInfoId: Long, updateKoreaInfoRequest: UpdateKoreaInfoRequest): DefaultResponse
    fun deleteKoreaInfo(koreaInfoId: Long): DefaultResponse
    fun getKoreaInfo(koreaInfoId: Long): KoreaInfoResponse
    fun getKoreaInfoPage(title: String?, pageable: Pageable): Page<KoreaInfoResponse>
}