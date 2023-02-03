package com.example.core.utils;

import java.util.UUID;

/**
 * @author Wmy
 * @date 2021/7/20
 */
public class UUIDUtil {
	/**
	 * 生成32字符长度的随即UUID
	 */
	public static String generateRandomUUID() {
		String[] strs = UUID.randomUUID().toString().split("-");
		String uuid = "";
		for (String str : strs)
			uuid += str;
		return uuid;
	} 
}
