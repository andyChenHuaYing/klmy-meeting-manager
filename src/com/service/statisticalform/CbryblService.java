package com.service.statisticalform;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;

@Service("cbryblService")
public class CbryblService {
	@Autowired
	@Qualifier("sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryAreaInsurance() throws SQLException{
		return this.sqlMapClient.queryForList("cbrybl.queryAreaInsurance");
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryAgePeriodInsurance() throws SQLException{
		return this.sqlMapClient.queryForList("cbrybl.queryAgePeriodInsurance");
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> querySexInsurance() throws SQLException{
		return this.sqlMapClient.queryForList("cbrybl.querySexInsurance");
	}
}
