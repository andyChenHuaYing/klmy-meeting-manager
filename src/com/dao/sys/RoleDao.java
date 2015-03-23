package com.dao.sys;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.vo.RoleResourceVO;
import com.vo.TBRole;

public interface RoleDao {
	public List<Map<String,Object>> queryRole(Map map);
	public List<Map<String,Object>> queryRoleNew(Map map);
	public int deleteRole(Map map);
	public int updateRole(Map map);
	public Long addRole(Map map);
	public Long addUserRole(Map map);
	public Long addPatientRole(Map map);
	public int addUserRoleArea(List<Map<String,Object>> list) throws SQLException;
	public int deleteUserRole(Map map);
	public int queryRoleCnt(Map map);
	public int deleteRoleUnit(Map map) throws SQLException;
	public int deleteUserRoleArea(Map map) throws SQLException;
	public List<RoleResourceVO> queryRoleResouceByRole(Map map) throws SQLException;
	public List<TBRole> queryRoleListForVO(Map map) throws SQLException;
	public List<Map<String,Object>> queryUserRoleArea(Map map) throws SQLException;
	public List<Map<String,Object>> queryUserRoleAreaList(Long userId) throws SQLException;
	public Long queryRoleNameIsExist(String roleName) throws SQLException;
	public Long queryRoleNameIsExistForUpdate(Map map) throws SQLException;
}
