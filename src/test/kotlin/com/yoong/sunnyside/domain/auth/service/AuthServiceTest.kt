package com.yoong.sunnyside.domain.auth.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.CustomIllegalArgumentException
import com.yoong.sunnyside.domain.auth.dto.EmailRequest
import com.yoong.sunnyside.domain.auth.dto.NicknameResponse
import com.yoong.sunnyside.domain.auth.dto.VerifyCodeRequest
import com.yoong.sunnyside.domain.auth.repository.AuthRepository
import com.yoong.sunnyside.domain.business.repository.TempBusinessRepository
import com.yoong.sunnyside.domain.consumer.entity.TempConsumer
import com.yoong.sunnyside.domain.consumer.repository.TempConsumerJpaRepository
import com.yoong.sunnyside.infra.email.EmailUtils
import com.yoong.sunnyside.infra.redis.RedisUtils
import com.yoong.sunnyside.infra.security.MemberPrincipal
import com.yoong.sunnyside.infra.security.MemberRole
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk

class AuthServiceTest : StringSpec({

    val emailUtils = mockk<EmailUtils>()
    val redisUtils = mockk<RedisUtils>()
    val tempConsumerRepository = mockk<TempConsumerJpaRepository>()
    val tempBusinessRepository = mockk<TempBusinessRepository>()
    val authRepository = mockk<AuthRepository>()

    val authService = AuthService(
        emailUtils = emailUtils,
        redisUtils = redisUtils,
        tempConsumerRepository = tempConsumerRepository,
        tempBusinessRepository = tempBusinessRepository,
        authRepository = authRepository,
        300000
    )

    "닉네임 중복 검사 중복 발생 시에 CustomIllegalArgumentException"{

        every { authRepository.validNickname(any()) } returns true

        shouldThrow<CustomIllegalArgumentException> {
            authService.checkNickname("test")
        }.let {
            it.message shouldBe "There is a duplicate nickname"
        }
    }

    "닉네임 중복 검사 정상 로직"{

        every { authRepository.validNickname(any()) } returns false

        val result = authService.checkNickname("a")

        result shouldBe NicknameResponse(true)
    }

    "인증 이메일 정상 동작"{

        val emailRequest = EmailRequest(
            "test@test.com",
            MemberRole.CONSUMER
        )

        every { emailUtils.sendEmail(emailRequest.email, any(), any()) } just Runs
        every { emailUtils.createCode() } returns "test"
        every { redisUtils.setStringData("${AUTHENTICATE}_${emailRequest.email}", any(), any()) } just Runs

        every { redisUtils.getStringData(any()) } returns "test"

        val result = authService.sendEmail(emailRequest)
        val redisResult = redisUtils.getStringData("${AUTHENTICATE}_${emailRequest.email}")

        result shouldBe DefaultResponse("Email send Successful")
        redisResult shouldBe "test"
    }

    "인증 번호가 일치하지 않을 경우 CustomIllegalArgumentException"{

        val verifyCodeRequest = VerifyCodeRequest(
            email = "test@test.com",
            role = MemberRole.CONSUMER,
            code = "test2"
        )

        every { redisUtils.getStringData("${AUTHENTICATE}_${verifyCodeRequest.email}") } returns "test"

        shouldThrow<CustomIllegalArgumentException> {
            authService.verifyEmail(verifyCodeRequest)
        }.let {
            it.message shouldBe "Verification code does not match"
        }

    }

    "인증 번호 검증 정상 동작"{

        val verifyCodeRequest = VerifyCodeRequest(
            email = "test@test.com",
            role = MemberRole.CONSUMER,
            code = "test"
        )

        val tempConsumer = TempConsumer(
            email = verifyCodeRequest.email,
            password = "",
            address = "",
            nickname = "",
            country = "",
            phoneNumber = "",
            foreignNumber = null,
            foreignCreateAt = null,
            role = MemberRole.CONSUMER
        )

        every { redisUtils.getStringData("${AUTHENTICATE}_${verifyCodeRequest.email}") } returns "test"
        every { redisUtils.deleteStringData("${AUTHENTICATE}_${verifyCodeRequest.email}") } just Runs
        every { tempConsumerRepository.saveAndFlush(any()) } answers {
            tempConsumer.id = 1L
            tempConsumer
        }

        val result = authService.verifyEmail(verifyCodeRequest)

        result.id shouldBe 1L
        result.role shouldBe MemberRole.CONSUMER

    }

    "토큰 부여시 권한 체크 정상 작동 확인"{

        val principal = MemberPrincipal(
            1L,
            MemberRole.CONSUMER
        )

        val result = authService.getRole(principal)

        result.id shouldBe principal.id
        result.role shouldBe "CONSUMER"
    }
})