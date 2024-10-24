package com.yoong.sunnyside.domain.business.controller

import com.yoong.sunnyside.domain.business.dto.BusinessSignupRequest
import com.yoong.sunnyside.domain.business.dto.LoginRequest
import com.yoong.sunnyside.domain.business.dto.LoginResponse
import com.yoong.sunnyside.domain.business.service.BusinessService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/business")
class BusinessController(private val businessService: BusinessService) {

    @PostMapping("/signup")
    fun signup(@RequestBody request: BusinessSignupRequest): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.CREATED).body(businessService.signUp(request))
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(businessService.login(request))
    }

}