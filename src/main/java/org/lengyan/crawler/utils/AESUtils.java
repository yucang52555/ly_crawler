package org.lengyan.crawler.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by Rock on 2015/1/14 0014.
 */
public class AESUtils {

    private static KeyGenerator kgen;
    private static Cipher cipher;

    static {
        try {
            kgen = KeyGenerator.getInstance("AES");
            cipher = Cipher.getInstance("AES");// 创建密码器
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }
    /**
     * 加密
     *
     * @param password 需要加密的内容
     * @param userName  加密名称
     * @param userKey  加密因子
     * @return
     */
    public static String encrypt(String password, String userName, String userKey) {
        try {

            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed((userName + userKey).getBytes());
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            byte[] byteContent = password.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return parseByte2HexStr(result); // 加密
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**解密
     * @param password  待解密内容
     * @param userName 解密名称
     * @param userKey 解密因子
     * @return
     */
    public static String decrypt(String password, String userName, String userKey) {
        try {
            byte[] decryptFrom = parseHexStr2Byte(password);
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed((userName+userKey).getBytes());
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(decryptFrom);
            return new String(result, "utf-8"); // 加密
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密
     *
     * @param password 需要加密的内容
     * @param userKey  加密因子
     * @return
     */
    public static String encrypt(String password, String userKey) {
        try {

            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed((userKey).getBytes());
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            byte[] byteContent = password.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return parseByte2HexStr(result); // 加密
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**解密
     * @param password  待解密内容
     * @param userKey 解密因子
     * @return
     */
    public static String decrypt(String password, String userKey) {
        try {
            byte[] decryptFrom = parseHexStr2Byte(password);
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed((userKey).getBytes());
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(decryptFrom);
            return new String(result, "utf-8"); // 加密
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
//        String username = "admin"; //加密因子
//        String password = "admin";
        String key="dafa";
//        //加密
//        for (int i = 0; i < 10; i++) {
//
//            System.out.println("加密前：" + password);
//            String encryptResultStr = AESUtils.encrypt(password, username, key);
//            System.out.println("加密后：" + encryptResultStr);
//            //解密
////            String decryptResult = decrypt(encryptResultStr, username, key);
////            System.out.println("解密后：" + decryptResult);
//        }
//
//
//        String decryptResult = decrypt("3A2C5758534E0B7E140601D7C8C7753E", "123", "love7758521");
//        System.out.println("解密后：" + decryptResult);
        //String[] users = {"chenxue","renjl","fuyq","chenliang","dinggy","houxu","jiaohong","songjun","lijx","zhuyb","shiju","wangsb","xiaoyl","sunmb","weiyg","houyj","sunsz","huzh"};
//        String[] users = {"13410031559"};
//        for (int i = 0; i < users.length; i++) {
//            String encryptResultStr = AESUtils.encrypt("5igdd123", users[i], key);
//            System.out.println("'"+users[i]+ "','" + encryptResultStr+"'");
//        }
        //System.out.println(AESUtils.decrypt("02BB67145CA9D20958583ECF37571407","18603028596",  key));
//        System.out.println(AES2Utils.decode("4C9E9498666AD7BE5AC0CC2A09863E524CD711603FF92B97597F1185B1EB24430D0D27B2184FD55AE696B2D57A067F32CA3DEA337AD1F11561BA9ACF9FA6040E1C165304BD1C69B27EE8E0E5AAC058E98B0E81B9B94B2486ED96DAFC50EEB509DE6E0DEB2DE55D1A47775D44721931174BEC6D4605374D3F9BF4C3FF0E7A96D46D4E3789A9689BB0627A12715302ADB66DE0D0FCD5A0AAE971C9529F7FB1564F40BE5FF93E18F9CD4434571B3AB610A64C96ED62CD8E03F65AE2303AA0DD203A92FDBE6890EA4E14315DF5F636D6851658F82BD0A4F39DAA71043003DA28F516D1D1EF01AC92870FE1AFF2C7261120DE204E6A8432796E010BC07FCAE6B285AC251563DA9F8A4B322E03FF9C54393E97", "LH3%kU71sLJRoc21"));
//        System.out.println(AES2Utils.encode("{}", "LH3%kU71sLJRoc21"));
        System.out.println(AESUtils.encrypt("AAAAAA","jrlt7758521"));
//        System.out.println(AESUtils.decrypt("8073E0EE52EA68F0DCC982F0E842039D","Rjra5iGddEncrypt"));
        System.out.println(AESUtils.decrypt("54DFD9729FD2230E33B8196788118ED9","love7758521"));
        System.out.println(AES2Utils.decode("8B1A1805A5605C9E1C84CC903157E352A3F0A3D5A28F0F4245F0B55071A9DBC0CE3E720EE69CB26200EE28B5514B23E44D0021C2A0A572DA12F01AC9356D2A5C", "Rjra5iGddEncrypt"));

    }
}
