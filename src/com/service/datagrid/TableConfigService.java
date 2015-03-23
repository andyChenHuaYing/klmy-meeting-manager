package com.service.datagrid;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.util.UserHolder;
import com.vo.table.TConfigTable;
import com.vo.table.TConfigTableHeader;

@Service("tableConfigService")
public class TableConfigService {
	@Autowired
	@Qualifier("gridService")
	private TableService tableService;
	
	@Autowired
	@Qualifier("tableHeaderService")
	private TableHeaderService tableHeaderService;
	
	/*
	 * 查询表头配置信息
	 * 
	 */
	public Map<String,Object> queryTableConfigInfo(String tableId,String userCondition) throws SQLException{
		Map<String,Object> rtnMap = new HashMap<String, Object>();
		TConfigTable tableVo = this.tableService.queryTableById(tableId);
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tableId", tableId);
		if(userCondition!=null){
			paramMap.put("userId", UserHolder.getUserContext().getUserId());
		}
		List<TConfigTableHeader> list = this.tableHeaderService.queryTable(paramMap);
		rtnMap.put("tableInfo", tableVo);
		rtnMap.put("tableHeaderInfo", list);
		return rtnMap;
	}
	
	public List<TConfigTable> queryAllTable() throws SQLException{
		return this.tableService.queryAllTable();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int setUserTabColumnRel(Long userId,Map<String,ArrayList<Long>> tabColumnMap) throws SQLException{
		int rtnCnt=0;
		Iterator<Entry<String, ArrayList<Long>>> it = tabColumnMap.entrySet().iterator();
		List<Map<String,Object>> paramlist = new ArrayList<Map<String,Object>>();
		while(it.hasNext()){
			Entry<String,ArrayList<Long>> entry = it.next();
			ArrayList<Long> list = entry.getValue();
			for(int i=0;i<list.size();i++){
				Map tmpMap = new HashMap<String,Long>();
				tmpMap.put("userId", userId);
				tmpMap.put("tableId", entry.getKey());
				tmpMap.put("tabColumnId", list.get(i));
				paramlist.add(tmpMap);
			}
		}
		Map paramMap = new HashMap<String,Object>();
		paramMap.put("userId", userId);
		this.tableHeaderService.deleteUserTabColumn(paramMap);
		rtnCnt = this.tableHeaderService.addUserTabColumnList(paramlist);
		return rtnCnt;
	}
	public Map<String,List<String>> queryUserTabHeaderMap(Long userId) throws SQLException{
		return this.tableHeaderService.queryUserTabHeaderMap(userId);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int copyQuerySetting(Long userId,Long copyUserId) throws SQLException{
		int cnt = 0;
		Map map = new HashMap<String,Object>();
		map.put("userId",userId);
		this.tableHeaderService.deleteUserTabColumn(map);
		map.put("userId",copyUserId);
		List<Map<String,Object>> list = this.tableHeaderService.queryUserTabColumn(map);
		for(int i=0;i<list.size();i++){
			Map tmpMap = list.get(i);
			tmpMap.put("userId", userId);
			tmpMap.put("tableId", tmpMap.get("TABLE_ID"));
			tmpMap.put("tabColumnId", tmpMap.get("TAB_COLUMN_ID"));
		}
		cnt = this.tableHeaderService.addUserTabColumnList(list);
		return cnt;
	}
	
}
