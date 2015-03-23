package com.service.sys;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dao.sys.LogDao;

@Service("logService")
public class LogService{
	@Autowired
	@Qualifier("logDao")
	private LogDao logDaoIbts;
	
	public List<Map<String,Object>> queryLog(Map map) {
		// TODO Auto-generated method stub
		return logDaoIbts.queryLog(map);
	}
	
	public List<Map<String,Object>> queryLogNew(Map map) {
		// TODO Auto-generated method stub
		return logDaoIbts.queryLogNew(map);
	}
	
	public Long addLog(Map map) {
		// TODO Auto-generated method stub
		return logDaoIbts.addLog(map);
	}

	public List<Map<String, Object>> queryLogType() throws SQLException {
		// TODO Auto-generated method stub
		return logDaoIbts.queryLogType();
	}

	public Integer queryLogCnt(Map map) throws SQLException {
		// TODO Auto-generated method stub
		return logDaoIbts.queryLogCnt(map);
	}

	public List<Map<String, Object>> queryUserTables(Map map) throws SQLException {
		// TODO Auto-generated method stub
		return logDaoIbts.queryUserTables(map);
	}
	
}
