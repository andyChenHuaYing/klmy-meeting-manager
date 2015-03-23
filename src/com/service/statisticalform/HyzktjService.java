package com.service.statisticalform;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;

@Service("hyzktjService")
public class HyzktjService {
	@Autowired
	@Qualifier("sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryAreaMaritalStatus() throws SQLException{
		return this.sqlMapClient.queryForList("hyzktj.queryAreaMaritalStatus");
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryYearMaritalStatus() throws SQLException{
		return this.sqlMapClient.queryForList("hyzktj.queryYearMaritalStatus");
	}
	
}
