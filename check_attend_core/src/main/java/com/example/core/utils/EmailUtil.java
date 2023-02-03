package com.example.core.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Wmy
 * @date 2021/10/5
 */
public class EmailUtil {

	//判断Email合法性
	public static boolean isEmail(String email) {
		if (email == null)
			return false;
		String rule = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(rule);
		matcher = pattern.matcher(email);
		if (matcher.matches())
			return true;
		else
			return false;
	}

	/**
	 * description:生成邮箱验证码
	 *
	 * @return String
	 * @author Ming
	 * @date 2021/3/11 22:15
	 */
	public static String creatMailCode() {
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		for (int i = 0; i < 6; i++) {
			sb.append(rand.nextInt(10));
		}
		return sb.toString();
	}
}
