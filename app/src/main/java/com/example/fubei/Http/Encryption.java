package com.example.fubei.Http;

import android.os.CountDownTimer;

import com.example.fubei.utils.Base64Class;
import com.example.fubei.utils.DES3code;
import com.example.fubei.utils.Info;
import com.example.fubei.utils.Md5Util;
import com.example.fubei.utils.RSAcode;

import java.util.Random;

/**
 * Created by tc on 2016/11/25.
 */

public class Encryption {

    public static byte[] DES3key;

    public static String encode(String Message, String SN) throws Exception {
        String sendMessage = "";
        // System.out.println("~~~~~~~~~~~~~sendMessage:" + Message);
        // 初始化密钥
        Info.myprivateKye = getMyprivateKey();
        DES3key = Info.myprivateKye.getBytes();
        sendMessage = Base64Encode(SN) + "|"
                + RsaEncode(DES3key, Info.frontPubKey.getBytes()) + "|"
                + Des3Encode(Message, DES3key);
        return sendMessage;
    }

    /**
     * 报文加密
     *
     * @param Message 报文明文
     * @return 报文密文
     * @throws Exception
     */
    public static String encode(String Message) throws Exception {
        String sendMessage = "";
        // 初始化密钥
        // Info.myprivateKye = getMyprivateKey();
        DES3key = Info.myKey.getBytes();
        sendMessage = Base64Encode("0000") + "|" + Des3Encode(Message, DES3key)
                + "|" + Md5Util.SHA1(Message);
        return sendMessage;
    }

    public static String getMyprivateKey() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 24; i++) {
            // 获得随机数
            sb.append(Math.abs(random.nextInt()) % 10);
        }
        return sb.toString();
    }

    public static String Base64Encode(String str) {
        return Base64Class.encodeToString(str.getBytes(), 0);
    }

    public static String RsaEncode(byte[] des3KeyData, byte[] front) {

        // 加密
        byte[] encodedData2 = null;
        try {
            encodedData2 = RSAcode.encryptByPublicKey(des3KeyData,
                    Base64Class.decode(front, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String temp = Base64Class.encodeToString(encodedData2, 0);
        String tempstr = "";
        for (int i = 0; i < temp.toCharArray().length; i++) {
            if (temp.charAt(i) != '\r' && temp.charAt(i) != '\n') {
                tempstr += temp.charAt(i);
            }
        }
        return tempstr;
    }

    public static String Rsa(byte[] des3KeyData, byte[] front) {
        // 加密
        byte[] encodedData2 = null;
        try {
            encodedData2 = RSAcode.encryptByPublicKey(des3KeyData,
                    Base64Class.decode(front, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String temp = Base64Class.encodeToString(encodedData2, 0);
        String tempstr = "";
        for (int i = 0; i < temp.toCharArray().length; i++) {
            if (temp.charAt(i) != '\r' && temp.charAt(i) != '\n') {
                tempstr += temp.charAt(i);
            }
        }
        return tempstr;
    }

    public static String Des3Encode(String str, byte[] des3KeyData) {
        byte[] inputData = null;
        inputData = str.getBytes();
        // 加密inputStr
        try {
            inputData = DES3code.encrypt(inputData, des3KeyData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64Class.encodeToString(inputData, 0);
    }

    public static String Md5Encode(String str) {
        byte[] md5 = Md5Util.MD5(str);
        String base64str = Base64Class.encodeToString(md5, 0);
        return getString(base64str);
    }

    public static String getString(String str) {
        String tempstr = "";
        for (int i = 0; i < str.toCharArray().length; i++) {
            if (str.charAt(i) != '\r' && str.charAt(i) != '\n') {
                tempstr += str.charAt(i);
            }
        }
        return tempstr;
    }

    static String outputStr;

    // 解析报文
    public static boolean decode(String str) {
        if (str == null) {
            return false;
        }
        String[] temp = str.split("\\|");
        if (temp.length != 3) {
            return false;
        }
        if (temp[0].equals("0")) { // 服务器解析失败
            try {
                if (!temp[1].equals("A9")) {
                    outputStr = new String(Base64Class.decode(temp[2], 0),
                            "UTF-8");
                } else {
                    outputStr = "您的账号已在其他客户端登录，为了您的账户信息安全，请重新登录";
                }
                Info.cuowu = outputStr;
                Info.netResult = null;
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else if (temp[0].equals("1")) { // 服务器解析成功
            try {
                String text1 = new String(Des3Decode(
                        Base64Class.decode(temp[1], 0), DES3key), "UTF-8");
                Info.netResult = text1;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static byte[] Des3Decode(byte[] str, byte[] des3KeyData) {
        byte[] inputData = null;
        try {
            inputData = DES3code.decrypt(str, des3KeyData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputData;
    }

    static CountDownTimer mTime;
    public static boolean flgcr = true;
    /**
     * 登录超时
     */
    public static void getCR() {
        if (mTime != null) {
            mTime.cancel();
            if (flgcr) {
                mTime = new CountDownTimer(60000 * 60, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        flgcr = true;
                        Info.isTimeOut = false;
                    }

                    @Override
                    public void onFinish() {
                        flgcr = false;
                        Info.isTimeOut = true;
                    }
                };
                mTime.start();
            }
        } else {
            mTime = new CountDownTimer(60000 * 60, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    flgcr = true;
                    Info.isTimeOut = false;
                }

                @Override
                public void onFinish() {
                    flgcr = false;
                    Info.isTimeOut = true;
                }
            };
            mTime.start();
        }
    }
}
