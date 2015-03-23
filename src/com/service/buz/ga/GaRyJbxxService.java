package com.service.buz.ga;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.util.UserHolder;
import com.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service("gaRyJbxxService")
public class GaRyJbxxService {
	
	@Autowired
	private SqlMapClient sqlMapClient;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map<String, Object>> queryGaRy(Map map) throws SQLException{
		
		List<Map<String, Object>> list = null;
		UserVO tmpUserVo= UserHolder.getUserContext();
		if(tmpUserVo.getRoleAreaList()!=null && tmpUserVo.getRoleAreaList().size()>0){
			Map tmpMap = tmpUserVo.getRoleAreaList();
			if(tmpMap.get(10000000000006L)!=null && !"".equals((String)tmpMap.get(10000000000006L))){
				map.put("areaIdList", ((String)tmpMap.get(10000000000006L)).split(","));
			}
		}
		if(map != null && map.get("rows")!=null && map.get("page")!=null){
			list = this.sqlMapClient.queryForList("gaxx.queryGaRyJbxx", map,(Integer)map.get("rows")*((Integer)map.get("page")-1),(Integer)map.get("rows"));	
		}else{
			list = this.sqlMapClient.queryForList("gaxx.queryGaRyJbxx", map);
		}
		return list;
	}
	
	@SuppressWarnings({ "rawtypes"})
	public int queryGaRyJbxxCnt(Map map) throws SQLException{
		UserVO tmpUserVo= UserHolder.getUserContext();
		if(tmpUserVo.getRoleAreaList()!=null && tmpUserVo.getRoleAreaList().size()>0){
			Map tmpMap = tmpUserVo.getRoleAreaList();
			if(tmpMap.get(10000000000006L)!=null && !"".equals((String)tmpMap.get(10000000000006L))){
				map.put("areaIdList", ((String)tmpMap.get(10000000000006L)).split(","));
			}
		}
		return (Integer)this.sqlMapClient.queryForObject("gaxx.queryGaRyJbxxCnt", map);
		
	}
}
