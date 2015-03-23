package com.dao.impl.sys;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.dao.base.BaseDao;
import com.dao.sys.RoleDao;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.vo.RoleResourceVO;
import com.vo.TBRole;
/**
 * @author gu.xiaogang
 *
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDao implements RoleDao {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);
	
	@Override
	public List<Map<String,Object>> queryRole(Map map) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = null;
		try {
			if(map != null && map.get("pageSize")!=null && map.get("currPage")!=null){
				list = this.sqlMapClient.queryForList("role.queryRole", map,(Integer)map.get("pageSize")*((Integer)map.get("currPage")-1),(Integer)map.get("pageSize"));
			}else{
				list = this.sqlMapClient.queryForList("role.queryRole", map);	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("role.queryRole:"+e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<Map<String,Object>> queryRoleNew(Map map) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = null;
		try {
			if(map != null && map.get("rows")!=null && map.get("page")!=null){
				list = this.sqlMapClient.queryForList("role.queryRole", map,(Integer)map.get("rows")*((Integer)map.get("page")-1),(Integer)map.get("rows"));
			}else{
				list = this.sqlMapClient.queryForList("role.queryRole", map);	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("role.queryRole:"+e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	@Override
	public int deleteRole(Map map) {
		// TODO Auto-generated method stub
		int cnt = 0;
		try {
			cnt = this.sqlMapClient.delete("role.deleteRole", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("role.deleteRole:"+e.getMessage());
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateRole(Map map) {
		// TODO Auto-generated method stub
		int cnt = 0;
		try {
			cnt = this.sqlMapClient.update("role.updateRole", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("role.updateRole:"+e.getMessage());
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public Long addRole(Map map) {
		Long roleId = 0L;
		// TODO Auto-generated method stub
		try {
			roleId = (Long)this.sqlMapClient.insert("role.addRole", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("role.addRole:"+e.getMessage());
			e.printStackTrace();
		}
		return roleId;
	}

	@Override
	public Long addUserRole(Map map) {
		Long userRoleId = 0L;
		// TODO Auto-generated method stub
		try {
			userRoleId = (Long)this.sqlMapClient.insert("role.addUserRole", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("role.addRole:"+e.getMessage());
			e.printStackTrace();
		}
		return userRoleId;
	}

	@Override
	public Long addPatientRole(Map map) {
		Long userRoleId = 0L;
		// TODO Auto-generated method stub
		try {
			userRoleId = (Long)this.sqlMapClient.insert("role.addPatientRole", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("role.addRole:"+e.getMessage());
			e.printStackTrace();
		}
		return userRoleId;
	}

	@Override
	public int deleteUserRole(Map map) {
		// TODO Auto-generated method stub
		int cnt = 0;
		try {
			cnt = this.sqlMapClient.delete("role.deleteUserRole", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("role.deleteUserRole"+e.getMessage());
			e.printStackTrace();
		}
		return cnt;
	}
	public int queryRoleCnt(Map map) {
		// TODO Auto-generated method stub
		int rtnCnt = 0;
		try {
			List<Map<String,Object>> list = this.sqlMapClient.queryForList("role.queryRoleCnt", map);
			if(list!=null && list.size()>0){
				rtnCnt=((BigDecimal)list.get(0).get("CNT")).intValue();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rtnCnt;
	}

	@Override
	public int deleteRoleUnit(Map map) throws SQLException {
		// TODO Auto-generated method stub
		return this.sqlMapClient.delete("role.deleteRoleUnit", map);
	}

	@Override
	public List<RoleResourceVO> queryRoleResouceByRole(Map map) throws SQLException {
		// TODO Auto-generated method stub
		return this.sqlMapClient.queryForList("roleResource.queryRoleSourceForVO", map);
	}

	@Override
	public List<TBRole> queryRoleListForVO(Map map) throws SQLException {
		// TODO Auto-generated method stub
		return this.sqlMapClient.queryForList("role.queryRoleForVO",map);
	}
	/*
	 * 
	 * 增加用户权限地区关联关系
	 * @see com.dao.sys.RoleDao#addUserRoleArea(java.util.List)
	 */
	@Override
	public int addUserRoleArea(List<Map<String, Object>> list) throws SQLException {
		// TODO Auto-generated method stub
		int rtnCnt = 0;
//		this.sqlMapClient.startTransaction();
//		this.sqlMapClient.startBatch();
//		for(int i=0;i<list.size();i++){
//			this.sqlMapClient.update("role.addUserRoleArea", list.get(i));
//		}
//		rtnCnt = this.sqlMapClient.executeBatch();
//		this.sqlMapClient.endTransaction();
		for(int i=0;i<list.size();i++){
			rtnCnt = this.sqlMapClient.update("role.addUserRoleArea", list.get(i));
		}
		return rtnCnt;
	}
	/**
	 * 删除用户权限地区关联关系
	 * @param map 
	 */
	@Override
	public int deleteUserRoleArea(Map map) throws SQLException {
		// TODO Auto-generated method stub
		this.sqlMapClient.delete("role.deleteUserRoleArea", map);
		return 0;
	}

	@Override
	public List<Map<String, Object>> queryUserRoleArea(Map map) throws SQLException {
		// TODO Auto-generated method stub
		
		return this.sqlMapClient.queryForList("role.queryUserRoleArea", map);
	}
	
	public List<Map<String,Object>> queryUserRoleAreaList(Long userId) throws SQLException{
		return this.sqlMapClient.queryForList("role.queryUserRoleAreaList", userId);
	}

	@Override
	public Long queryRoleNameIsExist(String roleName) throws SQLException {
		return (Long) this.sqlMapClient.queryForObject("role.queryRoleNameIsExist", roleName);
	}

	@Override
	public Long queryRoleNameIsExistForUpdate(Map map) throws SQLException {
		return (Long)this.sqlMapClient.queryForObject("role.queryRoleNameIsExistForUpdate", map);
	}
	
}
