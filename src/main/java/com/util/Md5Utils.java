package com.util;

import java.security.MessageDigest;

/**
 * @author Tyler Yin
 */
public class Md5Utils {
	public static String md5(String s) throws Exception {
		if (s.trim() == null) {
			return "null";
		}
		MessageDigest messagedigest = MessageDigest.getInstance("MD5");
		byte[] abyte0 = s.getBytes("utf-8");
		byte[] abyte1 = messagedigest.digest(abyte0);
		return bytes2Hex(abyte1).toUpperCase();
	}

	/**
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static String md5To16Bit(String s) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(s.getBytes());
		byte[] b = md.digest();
		int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0) {
				i += 256;
			}

			if (i < 16) {
				buf.append("0");
			}

			buf.append(Integer.toHexString(i));
		}
		return buf.toString().substring(8, 24);
	}

	private static String bytes2Hex(byte[] b) {
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < b.length; ++i) {
			String s1 = Integer.toHexString(b[i] & 0xFF);
			if (s1.length() == 1) {
				sb = sb.append("0");
			}
			sb = sb.append(s1);
		}
		return sb.toString();
	}
}
