package com.yoong.sunnyside.infra.encrypt.utils

import com.yoong.sunnyside.infra.encrypt.Encrypt
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.Charset
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.math.log

@Component
class AESUtil(
    @Value("\${encrypt.key}") private val key : String,
    @Value("\${encrypt.iv}") private val iv : String,
    @Value("\${encrypt.transformation}") private val transformation: String,
): Encrypt {

    private final val log = LoggerFactory.getLogger("AESUtil")

    private lateinit var secretKeySpec: SecretKeySpec
    private lateinit var ivParameterSpec: IvParameterSpec

    init {
        val encodedKey = key.toByteArray(Charset.forName("UTF-8"))
        secretKeySpec = SecretKeySpec(encodedKey, "AES")
        log.info("secretKeySpec: $secretKeySpec")

        val encodedIv = iv.toByteArray(Charset.forName("UTF-8"))
        ivParameterSpec = IvParameterSpec(encodedIv)
        log.info("iv: $ivParameterSpec")
    }

    override fun encrypt(content: String): String {

        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)

        val encryptedBytes = cipher.doFinal(content.toByteArray())

        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

    override fun decrypt(content: String): String {
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)
        val decodedBytes = cipher.doFinal(Base64.getDecoder().decode(content.toByteArray()))
        return String(decodedBytes)
    }
}