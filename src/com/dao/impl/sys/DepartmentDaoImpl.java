package com.dao.impl.sys;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dao.base.BaseDao;
import com.dao.sys.DepartmentDao;
import com.vo.TbBaseDepartment;
@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDao implements DepartmentDao{
	private final static Logger logger = LoggerFactory.getLogger(CompanyDaoImpl.class);
	@Override
	public List<Map<String, Object>> queryDepartment(Map map) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = null;
		
		try {
			list = this.sqlMapClient.queryForList("department.queryDepartment", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("department.queryDepartment:"+e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TbBaseDepartment> queryDepartmentForVo(Map map) {
		// TODO Auto-generated method stub
		List<TbBaseDepartment> list = null;
		try {
			list = this.sqlMapClient.queryForList("department.queryDepartmentForVo", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("department.queryDepartment:"+e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	@SuppressWarnings({ "finally", "rawtypes" })
	public int updateDept(Map map){
		int cnt = 0;
		try {
			cnt = this.sqlMapClient.update("department.updateDept",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return cnt;
		}
	}
	
	@SuppressWarnings({ "finally", "rawtypes" })
	public int updateDeptLevel(Map map){
		int cnt = 0;
		try {
			cnt = this.sqlMapClient.update("department.updateDeptLevel",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return cnt;
		}
	}
	@SuppressWarnings({ "finally", "rawtypes" })
	public int deleteDept(Map map){
		int cnt = 0;
		try {
			cnt = this.sqlMapClient.delete("department.deleteDept", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return cnt;
		}
	}
	
	@SuppressWarnings({ "finally", "rawtypes" })
	public int addDept(Map map){
		int cnt = 0;
		try {
			cnt = this.sqlMapClient.update("department.addDept",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return cnt;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public Integer queryDeptNameNumber(Map map) {
		int cnt = -1;
		try {
			cnt = (Integer) this.sqlMapClient.queryForObject("department.queryDeptNameNumber", map);
			System.out.println(cnt + " ========================");
			return cnt;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return cnt;
		}
	}
}
