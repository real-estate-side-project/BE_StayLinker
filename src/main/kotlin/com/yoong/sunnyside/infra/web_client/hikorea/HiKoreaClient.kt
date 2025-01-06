package com.yoong.sunnyside.infra.web_client.hikorea

import com.yoong.sunnyside.domain.consumer.dto.AlienRegistrationCardRequest
import com.yoong.sunnyside.infra.web_client.WebClientConfig
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters

@Component
class HiKoreaClient(
    private val webClient: WebClientConfig,
){

    fun request(alienRegistrationCardRequest: AlienRegistrationCardRequest): String{

        return webClient.connect()
            .post()
            .uri("/scrap/docInq/hikorea/ForeignerAuthenticity")
            .body(BodyInserters.fromValue(alienRegistrationCardRequest.toEncrypt()))
            .retrieve()
            .bodyToMono(String::class.java)
            .block() ?: ""
    }
}