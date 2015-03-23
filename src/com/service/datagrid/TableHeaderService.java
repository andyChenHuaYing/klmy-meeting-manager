package com.service.datagrid;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.base.IBaseEntityDao;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.vo.table.TConfigTableHeader;

@Service("tableHeaderService")
public class TableHeaderService {
	
	@Autowired
	private IBaseEntityDao<TConfigTableHeader, String> dao;
	
	@Autowired
	private SqlMapClient sqlMapClient;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<TConfigTableHeader> queryTable(Map map) throws SQLException{
		return dao.getEntities("tableHeader.queryTableHeaderForMap", map);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map<String,Object>> queryUserTabColumn(Map map) throws SQLException{
		return this.sqlMapClient.queryForList("tableHeader.queryUserTabColumn", map);
	}
	
	@SuppressWarnings("rawtypes")
	public int addUserTabColumn(Map map) throws SQLException{
		return this.sqlMapClient.update("tableHeader.addUserTabColumn", map);
	}
	
	public int addUserTabColumnList(List<Map<String,Object>> list) throws SQLException{
		int cnt =0;
		for(int i=0;i<list.size();i++){
			cnt = this.sqlMapClient.update("tableHeader.addUserTabColumn", list.get(i));
		}
		return cnt;
	}
	
	@SuppressWarnings("rawtypes")
	public int deleteUserTabColumn(Map map) throws SQLException{
		return this.sqlMapClient.delete("tableHeader.deleteUserTabColumn", map);
	}
	
	/** 
	 * @Title: queryUserTabHeaderMap 
	 * @Description: 查询用户表头关联信息
	 * @param 
	 * @return Map<String,List<String>>
	 * @throws 
	*/
	@SuppressWarnings("unchecked")
	public Map<String,List<String>> queryUserTabHeaderMap(Long userId) throws SQLException{
		Map<String,List<String>> rtnMap = new HashMap<String,List<String>>();
		List<Map<String,String>> resultList = this.sqlMapClient.queryForList("tableHeader.queryUserTabHeaderMap", userId);
		for(int i=0;i<resultList.size();i++){
			if(rtnMap.get(resultList.get(i).get("TABLE_ID"))!=null){
				rtnMap.get(resultList.get(i).get("TABLE_ID")).add(resultList.get(i).get("TAB_COLUMN_ID"));
			}else{
				List<String> tmpList = new ArrayList<String>();
				tmpList.add(resultList.get(i).get("TAB_COLUMN_ID"));
				rtnMap.put(resultList.get(i).get("TABLE_ID"),tmpList );
			}
		}
		return rtnMap;
	}
}
