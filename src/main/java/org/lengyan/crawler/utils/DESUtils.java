package org.lengyan.crawler.utils;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by Administrator on 2015/4/7.
 */
public class DESUtils {
    Key key;

    public DESUtils() {

    }

    public DESUtils(String str) {
        setKey(str); // 生成密匙
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * 根据参数生成 KEY
     */
    public void setKey (String strKey) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("DES");
            //防止linux下 随机生成key
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
            secureRandom.setSeed(strKey.getBytes());

            _generator.init(56,secureRandom);
            this.key = _generator.generateKey();
            _generator = null;
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error initializing SqlMap class. Cause: " + e);
        }
    }

    /**
     * 根据参数生成 KEY
     */
    /*public void setKey(String strKey) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("DES");
            _generator.init(new SecureRandom(strKey.getBytes()));
            this.key = _generator.generateKey();
            _generator = null;
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error initializing SqlMap class. Cause: " + e);
        }
    }*/

    /**
     * 加密 String 明文输入 ,String 密文输出
     */
    public String encryptStr(String strMing) {
        byte[] byteMi = null;
        byte[] byteMing = null;
        String strMi = "";
        BASE64Encoder base64en = new BASE64Encoder();
        try {
            byteMing = strMing.getBytes("UTF8");
            byteMi = this.encryptByte(byteMing);
            strMi = base64en.encode(byteMi);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error initializing SqlMap class. Cause: " + e);
        } finally {
            base64en = null;
            byteMing = null;
            byteMi = null;
        }
        return strMi;
    }

    /**
     * 解密 以 String 密文输入 ,String 明文输出
     *
     * @param strMi
     * @return
     */
    public String decryptStr(String strMi) {
        BASE64Decoder base64De = new BASE64Decoder();
        byte[] byteMing = null;
        byte[] byteMi = null;
        String strMing = "";
        try {
            byteMi = base64De.decodeBuffer(strMi);
            byteMing = this.decryptByte(byteMi);
            strMing = new String(byteMing, "UTF8");
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error initializing SqlMap class. Cause: " + e);
        } finally {
            base64De = null;
            byteMing = null;
            byteMi = null;
        }
        return strMing;
    }

    /**
     * 加密以 byte[] 明文输入 ,byte[] 密文输出
     *
     * @param byteS
     * @return
     */
    private byte[] encryptByte(byte[] byteS) {
        byte[] byteFina = null;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byteFina = cipher.doFinal(byteS);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error initializing SqlMap class. Cause: " + e);
        } finally {
            cipher = null;
        }
        return byteFina;
    }

    /**
     * 解密以 byte[] 密文输入 , 以 byte[] 明文输出
     *
     * @param byteD
     * @return
     */
    private byte[] decryptByte(byte[] byteD) {
        Cipher cipher;
        byte[] byteFina = null;
        try {
            cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byteFina = cipher.doFinal(byteD);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error initializing SqlMap class. Cause: " + e);
        } finally {
            cipher = null;
        }
        return byteFina;
    }

    public static void main(String[] args) throws Exception {
        DESUtils des = new DESUtils("5igdd_Encrypt");
        // DES 加密文件
        // des.encryptFile("G:/test.doc", "G:/ 加密 test.doc");
        // DES 解密文件
        // des.decryptFile("G:/ 加密 test.doc", "G:/ 解密 test.doc");
//        String str1 = "5igdd";
//        //String str1 = "djYd/lUfOK1vgorVTlUCkA==";
//        String tmp = str1;
//        for (int i = 0; i < 10; i++) {
//            System.out.println(" 加密前： " + tmp);
//            // DES 加密字符串
//            tmp = des.encryptStr(tmp);
//            System.out.println(" 加密后： " + tmp);
//            System.out.println(" 加密后长度： " + tmp.length());
//            System.out.println("_________________________");
//        }
//        for (int i = 0; i < 10; i++) {
//            // DES 解密字符串
//            tmp = des.decryptStr(tmp);
//            System.out.println(" 解密后： " + tmp);
//            System.out.println(" 解密后长度： " + tmp.length());
//
//        }
        System.out.println(des.encryptStr("i#_#5#_#69dbd38c-ce28-4d3a-9af3-69fd2fa8e6e1#_#f1261aee-4033-481e-ab11-9aee2f691acf"));
        System.out.println(des.decryptStr("9+dlNG1v90EkfGARMhbc50ojJ5bZBgIwo4E1iv7k82Xd07NtwLJ+JsfMQHoAOKQ2mwUsq+aHDT05JSmi/BHEfH+xt2id/0DSBniLxm0o5h640vWV5c8hSQ=="));
    }
}
