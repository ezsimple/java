package net.ion.util;

import java.util.Map;

public class ResultUtil {

	private final static String[] keys = {"success","message"};
	
	public static Map<String, Object> newResult() {
		Map<String, Object>result = MapUtil.newMap();
		setDefault(result);
		return result;
	}

	public static void setDefault(Map<String, Object> result) {
		result.put(keys[0], false);
		result.put(keys[1], "요청이 실패 하였습니다.");
	}

	public static void setSuccess(Map<String, Object> result, Boolean value) {
		final String key = keys[0];
		if (result.containsKey(key)) {
			result.remove(key);
			result.put(key, value);
			return;
		}
		result.put(key, value);
	}
	
	public static boolean isSuccess(Map<String, Object> result) {
		final String key = keys[0];
		return (boolean) result.get(key);
	}

	public static void setMessage(Map<String, Object> result, String value) {
		final String key = keys[1];
		if (result.containsKey(key)) {
			result.remove(key);
			result.put(key, value);
			return;
		}
		result.put(key, value);
	}

	public static void setSuccessMessage(Map<String, Object> result, String value) {
		setSuccess(result, true);
		setMessage(result, value);
	}
}