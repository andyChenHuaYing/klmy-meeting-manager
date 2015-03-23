package com.util;

import com.dao.sys.LogDao;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @className: SysLog 
 * @author: gu.xiaogang
 * @description: 系统日志类
 * @date: 2014年9月10日
 */
public class SysLog{
	public static int OPERATION_TYPE_UNKNOWN = 0;
	public static int OPERATION_TYPE_LOGIN = 1;
	public static int OPERATION_TYPE_lOGOUT = 2;
	public static int OPERATION_TYPE_ADD = 3;
	public static int OPERATION_TYPE_UPDATE = 4;
	public static int OPERATION_TYPE_DELETE = 5;
	public static int OPERATION_TYPE_QUERY = 6;
	public static final int OPERATION_TYPE_AUDIT = 7;
	
	private static LogDao logDaoIbts;
	static{
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();;
		logDaoIbts = (LogDao) ac.getBean("logDao");
		System.out.println("logDaoIbts:"+logDaoIbts);
	}
	/** 
	 * @Title: logRecord 
	 * @Description: 写入信息到日志表
	 * @param resTable 资源表名
	 * @param resId 资源Id
	 * @param oprType 操作类型
	 * @param desc 操作描述
	 * @return void
	 * @throws 
	*/
	public static void logRecord(String resTable,Long resId,Integer oprType,String desc){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", UserHolder.getUserContext()==null?"10000000000001":UserHolder.getUserContext().getUserId());
		map.put("remoteIp", IpUtil.getIpAddr(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest()));
		map.put("resourceTable", resTable);
		map.put("resourceId", resId);
		map.put("operationType", oprType);
		map.put("descption", desc);
		logDaoIbts.addLog(map);
	}

	public static void logRecordSimple(Integer oprType, String desc) {
		logRecord(null, null, oprType, desc);
	}
}
