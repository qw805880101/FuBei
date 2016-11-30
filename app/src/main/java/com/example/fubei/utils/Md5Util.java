package com.example.fubei.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

	public static byte[] MD5(String source) {
		try {
			MessageDigest md = MessageDigest
					.getInstance("MD5");

			md.update(source.getBytes("utf-8"));
			byte tmp[] = md.digest();
			return tmp;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String SHA1(String decript) {
		try {
			MessageDigest digest = MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
