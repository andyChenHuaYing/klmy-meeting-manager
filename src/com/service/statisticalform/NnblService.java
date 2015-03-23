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

@Service("nnblService")
public class NnblService {
	@Autowired
	@Qualifier("sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> querySexAreaNumber() throws SQLException{
		return this.sqlMapClient.queryForList("nnbl.querySexAreaNumber");
	}
	@SuppressWarnings("unchecked")
	public Map<String,List<Object>> querySexAgePeriodNumber() throws SQLException{
		Map<String,List<Object>> map = new HashMap<String, List<Object>>();
		List<Object> labelList = new ArrayList<Object>();
		labelList.add("15岁以下");
		labelList.add("16~65岁");
		labelList.add("65~90岁");
		labelList.add("90岁以上");
		List<Object> maleList = new ArrayList<Object>();
		List<Object> femaleList = new ArrayList<Object>();
		List<Map<String,Object>> list = this.sqlMapClient.queryForList("nnbl.querySexAgePeriodNumber");
		/*
		 * 查出来的结果应该只有两条记录
		 */
		for(int i=0;i<(list.size()<2?list.size():2);i++){
			if("男".equals((String)list.get(i).get("SEX"))){
				maleList.add(list.get(i).get("AGE1"));
				maleList.add(list.get(i).get("AGE2"));
				maleList.add(list.get(i).get("AGE3"));
				maleList.add(list.get(i).get("AGE4"));
			}else if("女".equals((String)list.get(i).get("SEX"))){
				femaleList.add(list.get(i).get("AGE1"));
				femaleList.add(list.get(i).get("AGE2"));
				femaleList.add(list.get(i).get("AGE3"));
				femaleList.add(list.get(i).get("AGE4"));
			}
		}
		map.put("labelList", labelList);
		map.put("maleList", maleList);
		map.put("femaleList", femaleList);
		return map;
	}
	
	
}
