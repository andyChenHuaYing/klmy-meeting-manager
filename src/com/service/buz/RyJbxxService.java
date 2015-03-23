package com.service.buz;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.base.IBaseEntityDao;
import com.vo.buz.RyJbxx;

@Service("ryJbxxService")
public class RyJbxxService {
	
	@Autowired
	private IBaseEntityDao<RyJbxx, String> dao;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<RyJbxx> queryRy(Map map) throws SQLException{
		
		List<RyJbxx> list = null;
		if(map != null && map.get("rows")!=null && map.get("page")!=null){
			list = dao.getEntities("ryJbxx.queryRyJbxx", map,(Integer)map.get("rows")*((Integer)map.get("page")-1),(Integer)map.get("rows"));
		}else{
			list = dao.getEntities("ryJbxx.queryRyJbxx", map);
		}
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int queryRyJbxxCnt(Map map) throws SQLException{
		return dao.queryEntityCount("ryJbxx.queryRyJbxxCnt", map);
	}
}
