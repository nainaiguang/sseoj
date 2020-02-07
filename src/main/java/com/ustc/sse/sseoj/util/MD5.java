package com.ustc.sse.sseoj.util;

import java.security.MessageDigest;

/**
 * @author 邱乃光
 * @version 1.0
 * 采用MD5加密解密
 * 摘要算法
 * 加密，而是用于数字签名或密码加密，用法如下
 * 1.先把密码进行MD5加密，然后存放在数据库中
 * 2.在密码对比过程中使用MD5加密，然后和数据库中MD5比较。
 * 备注：MD5无法被解密，作用是防止管理员和劫包者知道密码，只在用户端自行加密
 *
 */
public class MD5 {
    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 执行一次加密，两次解密
     * 对密码进行掩盖，而非真实加密
     */
    public static String convertMD5(String inStr){

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

}
