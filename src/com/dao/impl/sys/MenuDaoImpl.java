package com.dao.impl.sys;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.dao.base.BaseDao;
import com.dao.sys.MenuDao;
import com.ibatis.sqlmap.client.SqlMapClient;
@Repository("menuDao")
public class MenuDaoImpl extends BaseDao implements MenuDao{
	private final static Logger logger = LoggerFactory.getLogger(MenuDaoImpl.class);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map<String,Object>> findMenu(Map map){
		List<Map<String,Object>> list = null;
		try {
			list = (List<Map<String,Object>>)this.sqlMapClient.queryForList("menu.queryMenu", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int addRoleMenu(List<Map<String,Object>> list){
		int rtnCnt = 0;
		try {
			this.sqlMapClient.startBatch();
			for(Map<String,Object> map:list){
				this.sqlMapClient.insert("menu.addRoleMenu", map);
			}			
			rtnCnt = this.sqlMapClient.executeBatch();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("menu.addRoleMenu:"+e.getMessage());
			e.printStackTrace();
		}
		return rtnCnt;
	}

	@Override
	public int deleteRoleMenu(Map map) {
		// TODO Auto-generated method stub
		int cnt = 0;
		try {
			cnt = this.sqlMapClient.delete("menu.deleteRoleMenu",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("menu.deleteRoleMenu:"+e.getMessage());
			e.printStackTrace();
		}
		return cnt;
	};
}
