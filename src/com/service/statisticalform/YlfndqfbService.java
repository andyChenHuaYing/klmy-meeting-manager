package com.service.statisticalform;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;

@Service("ylfndqfbService")
public class YlfndqfbService {
	@Autowired
	@Qualifier("sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryAreaFWomanDistribution() throws SQLException{
		return this.sqlMapClient.queryForList("ylfndqfb.queryAreaFWomanDistribution");
	}
}
