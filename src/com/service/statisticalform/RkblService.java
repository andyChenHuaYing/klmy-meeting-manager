package com.service.statisticalform;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;

@Service("rkblService")
public class RkblService {
	@Autowired
	@Qualifier("sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryAreaAgeRatio() throws SQLException{
		return this.sqlMapClient.queryForList("rkbl.queryAreaAgeRatio");
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryAreaHhRatio() throws SQLException {
		return this.sqlMapClient.queryForList("rkbl.queryAreaHhRatio");
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryHhRatio() throws SQLException {
		return this.sqlMapClient.queryForList("rkbl.queryHhRatio");
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> querySexYearNum() throws SQLException {
		return this.sqlMapClient.queryForList("rkbl.querySexYearNum");
	}
	

}
