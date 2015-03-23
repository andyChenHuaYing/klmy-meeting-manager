package com.service.statisticalform;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;

@Service("chryfqtjService")
public class ChryfqtjService {
	@Autowired
	@Qualifier("sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryAreaCoMedicalInsurance() throws SQLException{
		return this.sqlMapClient.queryForList("chryfqtj.queryAreaCoMedicalInsurance");
	}
}
