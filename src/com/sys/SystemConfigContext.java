package com.sys;

import java.util.HashMap;

public class SystemConfigContext {

	/**
	 * 单例
	 */
	private static SystemConfigContext config = new SystemConfigContext();


	public static long sessionCheckTime = 0;

	/**
	 * 系统运行的属性
	 */
	public static  HashMap<String, String> map = new HashMap<String, String>();

	public static SystemConfigContext getInstance() {
		return config;
	}

	private SystemConfigContext() {

	}

	public HashMap<String, String> getMap() {
		return map;
	}

	public static void setMap(HashMap<String, String> mappara) {
		map = mappara;
	}

	public String getValue(String key) {
		return map.get(key);
	}

	public void init() {
		sessionCheckTime = Long.valueOf(map.get("sessionCheckTime"));
	}

}
