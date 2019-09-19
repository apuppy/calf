package com.demo.calf.service;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


@Service
public class EncryptService {

    private final static String SECRETE_KEY = "kdDICruj3WtBfLes";// 128 bit key （128/8）

    private static final String ALGORITHM = "AES";

    private static final String UNICODE_FORMAT = "UTF8";

    private static final Logger logger = LoggerFactory.getLogger(EncryptService.class);

    /**
     * 测试AES加解密
     */
    public void testEncryption() {

        String uri = "https://www.ibm.com/support/knowledgecenter/zh/SSWPVP_3.0.0.1/com.ibm.sklm.doc/overview/"
                + "cpt/cpt_ic_oview_tech_cryptographic_algorithm.html";

        String enStr = AESEncrypt(uri, SECRETE_KEY);
        logger.info("ENCRYPTED: " + enStr);

        String deStr = AESDecrypt(enStr, SECRETE_KEY);
        logger.info("DECRYPTED: " + deStr);
    }

    /**
     * AES加密字符串
     *
     * @param text 原始字符串
     * @param key  加密key
     * @return 返回加密后且base64编码后的字符串
     */
    private String AESEncrypt(String text, String key) {
        String strEncrypted = "";
        try {
            Key aesKey = new SecretKeySpec(key.getBytes(UNICODE_FORMAT), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encryptedBytes = cipher.doFinal(text.getBytes(UNICODE_FORMAT));
            strEncrypted = Base64.encodeBase64String(encryptedBytes);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return strEncrypted;
    }


    /**
     * AES解密字符串
     *
     * @param strEncrypted 加密过且base64编码过的字符串
     * @param key          加密key
     * @return 返回解密后base64解码后的字符串
     */
    private String AESDecrypt(String strEncrypted, String key) {
        String strDecrypted = "";
        try {
            Key aesKey = new SecretKeySpec(key.getBytes(UNICODE_FORMAT), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            byte[] encryptedBytes = Base64.decodeBase64(strEncrypted.getBytes());
            // decrypt the text
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            strDecrypted = new String(cipher.doFinal(encryptedBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDecrypted;
    }

}
