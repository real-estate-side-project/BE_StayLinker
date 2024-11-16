package com.yoong.sunnyside.domain.community.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.community.dto.AllCommunityResponse
import com.yoong.sunnyside.domain.community.dto.CommunityRequest
import com.yoong.sunnyside.domain.community.dto.CommunityResponse
import com.yoong.sunnyside.domain.community.service.CommunityService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "커뮤니티 관련 API", description = "커뮤니티 댓글 API는 따로 관리 중입니다")
@RestController
@RequestMapping("/community")
class CommunityController(
    private val communityService: CommunityService
){

    @Operation(summary = "커뮤니티 글 생성 API")
    @PostMapping
    fun createCommunity(
        @RequestBody communityRequest: CommunityRequest,
    ): ResponseEntity<DefaultResponse>
        = ResponseEntity.status(HttpStatus.OK).body(communityService.createCommunity(communityRequest))

    @Operation(summary = "커뮤니티 글 전체 조회 API")
    @GetMapping
    fun getAllCommunity(): ResponseEntity<List<AllCommunityResponse>>
            = ResponseEntity.status(HttpStatus.OK).body(communityService.getAllCommunity())

    @Operation(summary = "커뮤니티 특정 글 조회 API", description = "커뮤니티 글 id 값을 넣어 주시면 됩니다")
    @GetMapping("/{communityId}")
    fun getCommunity(
        @PathVariable("communityId") communityId: Long,
    ): ResponseEntity<CommunityResponse>
            = ResponseEntity.status(HttpStatus.OK).body(communityService.getCommunity(communityId))

    @Operation(summary = "커뮤니티 글 수정 API", description = "커뮤니티 글 id 값을 넣어 주시면 됩니다")
    @PutMapping("/{communityId}")
    fun updateCommunity(
        @PathVariable("communityId") communityId: Long,
        @RequestBody communityRequest: CommunityRequest,
    ): ResponseEntity<DefaultResponse>
            = ResponseEntity.status(HttpStatus.OK).body(communityService.updateCommunity(communityRequest, communityId))

    @Operation(summary = "커뮤니티 글 삭제 API", description = "커뮤니티 글 id 값을 넣어 주시면 됩니다")
    @DeleteMapping("/{communityId}")
    fun deleteCommunity(
        @PathVariable("communityId") communityId: Long,
    ): ResponseEntity<DefaultResponse>
            = ResponseEntity.status(HttpStatus.OK).body(communityService.deleteCommunity(communityId))

    @Operation(summary = "커뮤니티 글 신고 API", description = "커뮤니티 글 id 값을 넣어 주시면 됩니다")
    @PatchMapping("/report/{communityId}")
    fun reportCommunity(
        @PathVariable("communityId") communityId: Long,
    ): ResponseEntity<DefaultResponse>
        = ResponseEntity.status(HttpStatus.OK).body(communityService.reportCommunity(communityId))
}