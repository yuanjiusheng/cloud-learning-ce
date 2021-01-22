package com.yjs.cloud.learning.usercenter.biz.workwechat.util;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * 微信加密数据解密工具
 *
 * @author Bill.lai
 * @since 2020/6/10
 */
@Slf4j
public class WeChatDecryptDataUtil {

    public static String decrypt(String encryptData, String sessionKey, String iv) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] raw = decoder.decodeBuffer(sessionKey);
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(decoder.decodeBuffer(iv));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] decodeBuffer = decoder.decodeBuffer(encryptData);
            byte[] original = cipher.doFinal(decodeBuffer);
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("微信加密数据解密出错", e);
        }
        return null;
    }

}
