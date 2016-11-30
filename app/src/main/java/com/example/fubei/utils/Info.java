package com.example.fubei.utils;

import android.app.Activity;
import android.content.Context;

import java.util.List;

/**
 * Created by tc on 2016/7/18.
 */
public class Info {

    public static Activity currentActivity;
    public static Context currentContext;

    public final static String frontPubKey =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWAbPGzvKoflkMfiZ5quJS638C0SQ20YhlyR1NpU3lDJKiPze/34EgZR1Mlvw" +
                    "+yvBYJTuj1xw1h1rvDaA5ezGvkt+vzsajj" +
                    "+798WEkbEebYmr7IOvpuuJTFs9tglH4ZnaNcKq9gogjXdcO4Znqbv4VlA2HWUtjh+p/whuPVoaAHQIDAQAB";
    public final static String priKey =
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKEPfNisvXwbSy92NWI2hL46589LRygHyBDuVQXHE1rlxVOZdVx9uddd54+JwvCVuWaL39Ns9LB2g5qpmVZ+8iLL/93mHC99KVPaJ9QI66srren2HnRKmfB5HLdGmFoJetlzdiovD9uQRvamS0iECx8kwxQ4aj8kwZO2syeLnrPxAgMBAAECgYEAhBTAbKyUpB959A070DQnfh2ulsgELabcAk6BeUB99fAyd9GEdnpAmobO7F6seEJBDgCtaKSUsdYvLPni3xUyGdtvQFoH/xzv2XMQyvpYTexIrDiJr3MEpDJd8Iid5ZubpMWqSOmutJtLfV3gC0MGfQm6+nZdGfiXV7ohFRMmwUECQQDY5NNtQcsf+DKv4xJdEZHU6tIK/A0wcswI5QMd/v8l9FGjyX14ApYSCdnPhQZtqWzwuQlUxWPgqSAi1BE8KqEJAkEAvhmPgMqv3co1YXRJ47zVF+ACNOhlaaG8x+OXHBfv2NN6jvYAfHdELa/tYFlWLazOpy0Q0CU26+5rT6h3V1F9qQJAU+6RBrmsMi3o53mWtJ9E8MECETAipnn2DQcaYrQ35mcaZKhnPla53jcjq5ONvkgPGURxoPVVxi2Mew3XsZHJiQJADFbgZ73AWKcte9vuh+fT9S7HNeP34TlsZZUyU9KB8RMZG3qAYZPkSwrmX6Cs5V4YM+XK95fSztG1CYCn7nUNsQJAWanfSF18w5GilDnygQg62rP59X2XNqSLxjLaD15QiodhiiiFrWVjMoFgukkebNCO9ZLGz1jTnMx/9sQJgFxtfQ==";
    public final static String myKey = "NjI2NzMyMjUzMjAyMDYwMzEx";
    public static String pwdKey =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDSHkL5wyZb9IWON0p7NMnvNBYFkGpZINMKTMLiPW3PJsBh3zmIYDwUbQMrwPfYH" +
                    "+Vsz3u6i9Nhjw6pDq9jLWyqWls/L9SI7ASq/vtK073CFJ/MY6scSa7eJKECVk03u418Wrmm8GoKxnGc1VHoDIwP" +
                    "/Cd4KP7KmBEz0a10yCUjqwIDAQAB";
    // 网络超时
    public static int netTimeOut = 60000;
    // 网络返回结果
    public static String netResult;
    // 匿名线程是否取消联网
    public static boolean isCancelThread;
    // 停留超时
    public static boolean isTimeOut = false;

    public static String myprivateKye = "";
    //网络连接错误日志
    public static String cuowu;
    private static String zhongduanid = "";
    public static List<Activity> activityList;
    public static Activity currentActivityHomePage;
    public static List<Activity> transactionList;


    public static String getTime_MMddHHmmss(String str) {
        if (str.length() < 14) {
            return str;
        }
        return str.substring(4, 6) + "-" + str.substring(6, 8) + " "
                + str.substring(8, 10) + ":" + str.substring(10, 12) + ":"
                + str.substring(12, 14);
    }

    public static String getTime(String str) {
        if (str.length() != 14) {
            return str;
        }
        return str.substring(0, 4) + "-" + str.substring(4, 6) + "-"
                + str.substring(6, 8) + " " + str.substring(8, 10) + ":"
                + str.substring(10, 12) + ":" + str.substring(12, 14);
    }

    public static String getCardNum(String str) {
        int num = str.length() / 4 + 1;
        String card = "";
        for (int i = 0; i < num; i++) {
            if (i != (num - 1)) {
                card += str.substring(i * 4, (i + 1) * 4) + " ";
            } else {
                card += str.substring(i * 4, str.length());
            }
        }
        return card;
    }
}
