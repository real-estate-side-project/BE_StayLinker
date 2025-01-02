package com.yoong.sunnyside.domain.faq.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.ModelNotFoundException
import com.yoong.sunnyside.domain.faq.dto.CreateFaqRequest
import com.yoong.sunnyside.domain.faq.dto.FaqResponse
import com.yoong.sunnyside.domain.faq.dto.UpdateFaqRequest
import com.yoong.sunnyside.domain.faq.entity.Faq
import com.yoong.sunnyside.domain.faq.repository.FaqRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FaqService(
    private val faqRepository: FaqRepository,
) {
    @Transactional
    fun createFaq(
        createFaqRequest: CreateFaqRequest,
    ): DefaultResponse {

        faqRepository.save(
            Faq(createFaqRequest)
        )

        return DefaultResponse("fna 생성이 완료 되었습니다")
    }

    fun getFaq(faqId: Long): FaqResponse {
        val faq = faqRepository.findByIdOrNull(faqId)
            ?: throw ModelNotFoundException("{faqId}가 존재하지 않아요")
        return FaqResponse.from(faq)
    }

    fun getFaqPage(question: String?, pageable: Pageable): Page<FaqResponse> {
        val faqPage = faqRepository.findAllByQuestionContains(question, pageable)
        return faqPage.map { FaqResponse.from(it) }

    }

    @Transactional
    fun updateFaq(
        faqId: Long,
        updateFaqRequest: UpdateFaqRequest
    ): DefaultResponse {
        val faq = faqRepository.findByIdOrNull(faqId)
            ?: throw ModelNotFoundException("{faqId}가 존재하지 않아요")
        faq.update(updateFaqRequest)
        return DefaultResponse("fna 수정이 완료 되었습니다")
    }

    @Transactional
    fun deleteFaq(faqId: Long): DefaultResponse {
        val faq = faqRepository.findByIdOrNull(faqId)
            ?: throw ModelNotFoundException("{faqId}가 존재하지 않아요")
        faq.delete()
        return DefaultResponse("fna 삭제가 완료되었습니다.")
    }
}