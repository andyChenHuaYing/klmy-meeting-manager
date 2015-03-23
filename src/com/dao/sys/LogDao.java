package com.dao.sys;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface LogDao {
	public List<Map<String,Object>> queryLog(Map map);
	public Long addLog(Map map);
	public List<Map<String,Object>> queryLogType() throws SQLException;
	public Integer queryLogCnt(Map map) throws SQLException;
	public List<Map<String,Object>> queryUserTables(Map map) throws SQLException;
	public List<Map<String,Object>> queryLogNew(Map map);
}
