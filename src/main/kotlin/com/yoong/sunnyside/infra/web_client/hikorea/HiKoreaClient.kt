package com.yoong.sunnyside.infra.web_client.hikorea

import com.yoong.sunnyside.domain.consumer.dto.AlienRegistrationCardRequest
import com.yoong.sunnyside.infra.web_client.WebClientConfig
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters

@Component
class HiKoreaClient(
    private val webClient: WebClientConfig,
){

    fun request(alienRegistrationCardRequest: AlienRegistrationCardRequest): Map<String, Any>{

        return webClient.connect()
            .post()
            .uri("/scrap/docInq/hikorea/ForeignerAuthenticity")
            .body(BodyInserters.fromValue(alienRegistrationCardRequest))
            .retrieve()
            .bodyToMono(object : ParameterizedTypeReference<Map<String, Any>>() {})
            .block() ?: mapOf()
    }
}