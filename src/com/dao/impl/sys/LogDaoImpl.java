package com.dao.impl.sys;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dao.base.BaseDao;
import com.dao.sys.LogDao;
@Repository("logDao")
public class LogDaoImpl extends BaseDao implements LogDao{
	private final static Logger logger = LoggerFactory.getLogger(LogDaoImpl.class);
	
	@Override
	public List<Map<String,Object>> queryLog(Map map) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = null;
		try {
			
			if(map != null && map.get("pageSize")!=null && map.get("currPage")!=null){
		        list = this.sqlMapClient.queryForList("log.queryLog", map,(Integer)map.get("pageSize")*((Integer)map.get("currPage")-1),(Integer)map.get("pageSize"));
		      }else{
		        list = this.sqlMapClient.queryForList("log.queryLog", map);  
		      }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("log.queryLog: "+e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<Map<String,Object>> queryLogNew(Map map) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = null;
		try {
			
			if(map != null && map.get("rows")!=null && map.get("page")!=null){
		        list = this.sqlMapClient.queryForList("log.queryLog", map,(Integer)map.get("rows")*((Integer)map.get("page")-1),(Integer)map.get("rows"));
		      }else{
		        list = this.sqlMapClient.queryForList("log.queryLog", map);  
		      }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("log.queryLog: "+e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public Long addLog(Map map) {
		// TODO Auto-generated method stub
		Long logId = 0L;
		try {
			logId = (Long)this.sqlMapClient.insert("log.addLog", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("log.addLog:"+e.getMessage());
			e.printStackTrace();
		}
		return logId;
	}

	@Override
	public List<Map<String, Object>> queryLogType() throws SQLException {
		// TODO Auto-generated method stub
		return this.sqlMapClient.queryForList("log.queryLogType");
	}

	@Override
	public Integer queryLogCnt(Map map) throws SQLException {
		// TODO Auto-generated method stub
		return (Integer)this.sqlMapClient.queryForObject("log.queryLogCnt", map);
	}

	@Override
	public List<Map<String, Object>> queryUserTables(Map map) throws SQLException {
		// TODO Auto-generated method stub
		return this.sqlMapClient.queryForList("log.queryUserTables", map);
	}
}
