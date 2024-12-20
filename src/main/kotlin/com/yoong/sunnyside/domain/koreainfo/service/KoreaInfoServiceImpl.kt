package com.yoong.sunnyside.domain.koreainfo.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.ModelNotFoundException
import com.yoong.sunnyside.domain.koreainfo.dto.CreateKoreaInfoRequest
import com.yoong.sunnyside.domain.koreainfo.dto.KoreaInfoResponse
import com.yoong.sunnyside.domain.koreainfo.dto.UpdateKoreaInfoRequest
import com.yoong.sunnyside.domain.koreainfo.entity.KoreaInfo
import com.yoong.sunnyside.domain.koreainfo.repository.KoreaInfoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class KoreaInfoServiceImpl(
    private val koreaInfoRepository: KoreaInfoRepository
) : KoreaInfoService {
    @Transactional
    override fun createKoreaInfo(createKoreaInfoRequest: CreateKoreaInfoRequest, adminId: Long): DefaultResponse {
        koreaInfoRepository.save(
            koreaInfoRepository.save(KoreaInfo(createKoreaInfoRequest, adminId))
        )
        return DefaultResponse("한국정보가 생성되었습니다")
    }

    @Transactional
    override fun updateKoreaInfo(koreaInfoId: Long, updateKoreaInfoRequest: UpdateKoreaInfoRequest): DefaultResponse {
        val koreaInfo =
            koreaInfoRepository.findByIdOrNull(koreaInfoId) ?: throw ModelNotFoundException("{koreaInfoId}가 존재하지 않아요")
        koreaInfo.update(updateKoreaInfoRequest)
        return DefaultResponse("수정되었습니다")
    }

    @Transactional
    override fun deleteKoreaInfo(koreaInfoId: Long): DefaultResponse {
        val koreaInfo =
            koreaInfoRepository.findByIdOrNull(koreaInfoId) ?: throw ModelNotFoundException("{koreaInfoId}가 존재하지 않아요")
        koreaInfo.delete()
        return DefaultResponse("삭제되었습니다")
    }

    override fun getKoreaInfo(koreaInfoId: Long): KoreaInfoResponse {
        val koreaInfo =
            koreaInfoRepository.findByIdOrNull(koreaInfoId) ?: throw ModelNotFoundException("{koreaInfoId}가 존재하지 않아요")
        return KoreaInfoResponse.from(koreaInfo)
    }

    override fun getKoreaInfoPage(title: String?, pageable: Pageable): Page<KoreaInfoResponse> {
        TODO("Not yet implemented")
    }
}