package com.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.vo.TConfigDictionary;

@Service("dictionaryService")
public class DictionaryService {
	
	@Autowired
	private SqlMapClient sqlMapClient;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String,List<TConfigDictionary>> queryDictionaryClassify(Map map) throws SQLException{
		Map<String,List<TConfigDictionary>> rtnMap = new HashMap<String, List<TConfigDictionary>>();
		List<TConfigDictionary> list = this.sqlMapClient.queryForList("dictionary.queryDic", map);
		for(int i=0;i<list.size();i++){
			if(rtnMap.get(list.get(i).getKeyword())!=null){
				((List)rtnMap.get(list.get(i).getKeyword())).add(list.get(i));
			}else{
				List<TConfigDictionary> tmpList = new ArrayList<TConfigDictionary>();
				tmpList.add(list.get(i));
				rtnMap.put(list.get(i).getKeyword(), tmpList);
			}
		}
		return rtnMap;
	}
}
