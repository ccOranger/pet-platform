package com.licc.cat.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Base64;

public class WXBizDataCrypt {


    public WXBizDataCrypt() {
    }

    public static String decrypt(String encryptedData, String iv, String sessionKey) throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] keyByte = decoder.decode(sessionKey);
        byte[] encryptedDataByte = decoder.decode(encryptedData);
        byte[] ivByte = decoder.decode(iv);


        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
        AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
        parameters.init(new IvParameterSpec(ivByte));
        //设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, spec, parameters);

        byte[] resultByte = cipher.doFinal(encryptedDataByte);

        String result = null;
        if (null != resultByte && resultByte.length > 0) {
            result = new String(resultByte, "UTF-8");
        }
// 返回前，可以对比appId
        return result;
    }

}
