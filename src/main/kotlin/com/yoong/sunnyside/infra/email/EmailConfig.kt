package com.yoong.sunnyside.infra.email

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*

@Configuration
class EmailConfig(
    @Value("\${spring.mail.host}") private val host: String,
    @Value("\${spring.mail.port}") private val port: Int,
    @Value("\${spring.mail.username}") private val username: String,
    @Value("\${spring.mail.password}") private val password: String,
    @Value("\${spring.mail.properties.smtp.auth}") private val auth: Boolean,
    @Value("\${spring.mail.properties.smtp.starttls.enable}") private val starttlsEnable: Boolean,
    @Value("\${spring.mail.properties.smtp.starttls.required}") private val starttlsRequired: String,
    @Value("\${spring.mail.properties.smtp.connectionTimeout}") private val connectionTimeOut: Int,
    @Value("\${spring.mail.properties.smtp.timeout}") private val timeout: Int,
    @Value("\${spring.mail.properties.smtp.writeTimeout}") private val writeTimeout: Int,
){


    @Bean
    fun javaMailSender(): JavaMailSender {

        val mailSender = JavaMailSenderImpl()

        mailSender.host = host
        mailSender.port = port
        mailSender.username = username
        mailSender.password = password
        mailSender.defaultEncoding = "UTF-8"
        mailSender.javaMailProperties = getProperties()

        return mailSender
    }

    private fun getProperties(): Properties{

        val properties = Properties()

        properties["mail.smtp.auth"] = auth
        properties["mail.smtp.starttls.enable"] = starttlsEnable
        properties["mail.smtp.starttls.required"] = starttlsRequired
        properties["mail.smtp.connectionTimeout"] = connectionTimeOut
        properties["mail.smtp.timeout"] = timeout
        properties["mail.smtp.writeTimeout"] = writeTimeout

        return properties
    }

}