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
import com.dao.sys.UnitDao;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.vo.UnitVO;
@Repository("unitDao")
public class UnitDaoImpl extends BaseDao implements UnitDao {
	private final static Logger logger = LoggerFactory.getLogger(UnitDaoImpl.class);
	@SuppressWarnings({ "finally", "unchecked", "rawtypes" })
	@Override
	public List<UnitVO> queryUnit(Map map) {
		// TODO Auto-generated method stub
		List<UnitVO> list = null;		
		try {
			list = (List<UnitVO>)sqlMapClient.queryForList("unit.queryUnit", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally{
			return list;
		}
	}

	@SuppressWarnings({ "unchecked", "finally", "rawtypes" })
	@Override
	public List<UnitVO> queryUnitByUser(Map map) {
		List<UnitVO> list = null;		
		try {
			list = (List<UnitVO>)sqlMapClient.queryForList("unit.queryUnitByUser", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally{
			return list;
		}
	}
	@SuppressWarnings({ "finally", "rawtypes" })
	public int deleteUnit(Map map){
		int deleteCnt = -1;
		try {
			deleteCnt = sqlMapClient.delete("unit.deleteRoleUnit",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally{			
			return deleteCnt;
		}
	}
	
	@SuppressWarnings({ "finally", "rawtypes" })
	public int addRoleUnit(Map map){
		int insertCnt = 0;		
		try {
			sqlMapClient.insert("unit.addRoleUnit", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			return insertCnt;
		}
	}
	public List<Map<String,Object>> queryUnitForMap(Map map){
		List<Map<String,Object>> list = null;		
		try {
			list = sqlMapClient.queryForList("unit.queryUnitForMap", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int saveRoleUnit(List<Map<String,Object>> list) {
		// TODO Auto-generated method stub
		int rtnCnt = 0;
		try {
			this.sqlMapClient.startBatch();
			for(Map<String,Object> map:list){
				sqlMapClient.insert("unit.insertRoleUnit",map);
			}
			rtnCnt = this.sqlMapClient.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("unit.insertRoleUnit:"+e.getMessage());
			e.printStackTrace();
		}
		return rtnCnt;
	}

	@Override
	public int deleteRoleUnit(Map map) {
		// TODO Auto-generated method stub
		int cnt = 0;
		try {
			cnt = this.sqlMapClient.delete("unit.deleteRoleUnit", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("unit.deleteRoleUnit:"+e.getMessage());
			e.printStackTrace();
		}
		return cnt;
	}
}
