package com.service.buz.js;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.util.UserHolder;
import com.vo.UserVO;


@Service("jsRyJbxxService")
public class JsRyJbxxService {
	@Autowired
	private SqlMapClient sqlMapClient;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map<String, Object>> queryJsRy(Map map) throws SQLException{
		
		List<Map<String, Object>> list = null;
		UserVO tmpUserVo= UserHolder.getUserContext();
		if(tmpUserVo.getRoleAreaList()!=null && tmpUserVo.getRoleAreaList().size()>0){
			Map tmpMap = tmpUserVo.getRoleAreaList();
			if(tmpMap.get(10000000000009L)!=null && !"".equals((String)tmpMap.get(10000000000009L))){
				map.put("areaIdList", ((String)tmpMap.get(10000000000009L)).split(","));
			}
		}
		if(map != null && map.get("rows")!=null && map.get("page")!=null){
			list = this.sqlMapClient.queryForList("jsxx.queryJsxx", map,(Integer)map.get("rows")*((Integer)map.get("page")-1),(Integer)map.get("rows"));	
		}else{
			this.sqlMapClient.queryForList("jsxx.queryJsxx", map);
		}
		return list;
	}
	
	@SuppressWarnings({ "rawtypes"})
	public int queryJsRyJbxxCnt(Map map) throws SQLException{
		UserVO tmpUserVo= UserHolder.getUserContext();
		if(tmpUserVo.getRoleAreaList()!=null && tmpUserVo.getRoleAreaList().size()>0){
			Map tmpMap = tmpUserVo.getRoleAreaList();
			if(tmpMap.get(10000000000009L)!=null && !"".equals((String)tmpMap.get(10000000000009L))){
				map.put("areaIdList", ((String)tmpMap.get(10000000000009L)).split(","));
			}
		}
		return (Integer)this.sqlMapClient.queryForObject("jsxx.queryJsxxCnt", map);
		
	}
}
