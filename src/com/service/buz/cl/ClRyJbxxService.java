package com.service.buz.cl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.util.UserHolder;
import com.vo.UserVO;


@Service("clRyJbxxService")
public class ClRyJbxxService {
	@Autowired
	private SqlMapClient sqlMapClient;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map<String, Object>> queryClRy(Map map) throws SQLException{
		
		List<Map<String, Object>> list = null;
		UserVO tmpUserVo= UserHolder.getUserContext();
		if(tmpUserVo.getRoleAreaList()!=null && tmpUserVo.getRoleAreaList().size()>0){
			Map tmpMap = tmpUserVo.getRoleAreaList();
			if(tmpMap.get(10000000000013L)!=null && !"".equals((String)tmpMap.get(10000000000013L))){
				map.put("areaIdList", ((String)tmpMap.get(10000000000013L)).split(","));
			}
		}
		if(map != null && map.get("rows")!=null && map.get("page")!=null){
			list = this.sqlMapClient.queryForList("clxx.queryClxx", map,(Integer)map.get("rows")*((Integer)map.get("page")-1),(Integer)map.get("rows"));	
		}else{
			this.sqlMapClient.queryForList("clxx.queryClxx", map);
		}
		return list;
	}
	
	@SuppressWarnings({ "rawtypes"})
	public int queryClRyJbxxCnt(Map map) throws SQLException{
		UserVO tmpUserVo= UserHolder.getUserContext();
		if(tmpUserVo.getRoleAreaList()!=null && tmpUserVo.getRoleAreaList().size()>0){
			Map tmpMap = tmpUserVo.getRoleAreaList();
			if(tmpMap.get(10000000000013L)!=null && !"".equals((String)tmpMap.get(10000000000013L))){
				map.put("areaIdList", ((String)tmpMap.get(10000000000013L)).split(","));
			}
		}
		return (Integer)this.sqlMapClient.queryForObject("clxx.queryClxxCnt", map);
		
	}
}
