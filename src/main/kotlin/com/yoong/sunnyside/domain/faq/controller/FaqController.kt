package com.yoong.sunnyside.domain.Fna.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.faq.dto.CreateFaqRequest
import com.yoong.sunnyside.domain.faq.dto.FaqResponse
import com.yoong.sunnyside.domain.faq.dto.UpdateFaqRequest
import com.yoong.sunnyside.domain.faq.service.FaqService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "FAQ", description = "FAQ API")
@RestController
@RequestMapping("/faq")
class FaqController(
    private val faqService: FaqService
) {
    @Operation(summary = "fna 등록")
    @PostMapping
    fun creatFna(@RequestBody request: CreateFaqRequest): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(faqService.createFaq(request))
    }

    @Operation(summary = "fna 전체 보기")
    @GetMapping
    fun getAllFna(
        @RequestParam question: String?,
        @PageableDefault(page = 0, size = 10, sort = ["createdAt"], direction = Sort.Direction.DESC) pageable: Pageable
    ): ResponseEntity<Page<FaqResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(faqService.getFaqPage(question, pageable))
    }

    @Operation(summary = "fna 단건조회")
    @GetMapping("{fnaId}")
    fun getFna(@PathVariable("fnaId") fnaId: Long): ResponseEntity<FaqResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(faqService.getFaq(fnaId))
    }

    @Operation(summary = "fna 수정하기")
    @PutMapping("{fnaId}")
    fun updateFna(
        @PathVariable("fnaId") id: Long,
        @RequestBody request: UpdateFaqRequest
    ): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(faqService.updateFaq(id, request))
    }

    @Operation(summary = "fna 삭제하기")
    @DeleteMapping("{fnaId}")
    fun deleteFna(@PathVariable("fnaId") id: Long): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(faqService.deleteFaq(id))
    }
}