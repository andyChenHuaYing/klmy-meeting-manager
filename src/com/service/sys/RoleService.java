package com.service.sys;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.sys.MenuDao;
import com.dao.sys.RoleDao;
import com.dao.sys.UnitDao;
import com.util.SysLog;
import com.util.UserHolder;
import com.vo.RoleResourceVO;
import com.vo.TBRole;
@Service("roleService")
public class RoleService {

	@Autowired
	@Qualifier("roleDao")
	private RoleDao roleDaoIbts;
	
	@Autowired
	@Qualifier("menuDao")
	private MenuDao menuDaoIbts;
	
	@Autowired
	@Qualifier("unitDao")
	private UnitDao unitDaoIbts;
	
	public List<Map<String, Object>> queryRole(Map map) {
		// TODO Auto-generated method stub
		return this.roleDaoIbts.queryRole(map);
	}
	
	public List<Map<String, Object>> queryRoleNew(Map map) {
		// TODO Auto-generated method stub
		return this.roleDaoIbts.queryRoleNew(map);
	}
	
	public Long addUserRole(Map map) {
		// TODO Auto-generated method stub
		return this.roleDaoIbts.addUserRole(map);
	}

	public Long addPatientRole(Map map) {
		// TODO Auto-generated method stub
		return this.roleDaoIbts.addPatientRole(map);
	}

	public int deleteUserRole(Map map) {
		// TODO Auto-generated method stub
		return this.roleDaoIbts.deleteUserRole(map);
	}	
	public int queryRoleCnt(Map map){
		SysLog.logRecord("TB_BASE_ROLE", null , SysLog.OPERATION_TYPE_QUERY, UserHolder.getUserContext()==null?"":UserHolder.getUserContext().getUserCode()+" 查询 TB_BASE_ROLE");
		return this.roleDaoIbts.queryRoleCnt(map);
	}

	public int deleteRoleForMap(Map map) {
		// TODO Auto-generated method stub
		if(map.get("roleIdList")!=null){
			String[] roleIdList = (String[])map.get("roleIdList");
			for(int i=0;i<roleIdList.length;i++){
				SysLog.logRecord("TB_BASE_ROLE", Long.valueOf(roleIdList[i]) , SysLog.OPERATION_TYPE_DELETE, UserHolder.getUserContext()==null?"":UserHolder.getUserContext().getUserCode()+" 删除 TB_BASE_ROLE");
			}
		}
		return this.roleDaoIbts.deleteRole(map);
	}

	public long addRoleInfo(Map map, String[] menuList) throws SQLException {
		// TODO Auto-generated method stub
		Long roleId = 0L;
		//判断权限名是否存在。
		roleId = this.roleDaoIbts.queryRoleNameIsExist(map.get("roleName").toString());
		if(roleId > 0){
			return -1;
		}
		roleId = this.roleDaoIbts.addRole(map);
		SysLog.logRecord("TB_BASE_ROLE", roleId, SysLog.OPERATION_TYPE_ADD, UserHolder.getUserContext().getUserCode()+" 新增 TB_BASE_ROLE");
		if(roleId>0){
			List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
			for(int i=0;i<menuList.length;i++){
				Map<String,Object> tmpMap = new HashMap<String, Object>();
				tmpMap.put("roleId", roleId);
				tmpMap.put("menuId", menuList[i]);
				tmpMap.put("createUserId", UserHolder.getUserContext().getUserId());
				list1.add(tmpMap);
			}
			if(list1.size()>0){
				this.menuDaoIbts.addRoleMenu(list1);
			}
		}		
		return roleId;
	}

	public int updateRoleInfo(Map map, String[] menuList) throws SQLException {
		// TODO Auto-generated method stub
		SysLog.logRecord("TB_BASE_ROLE", Long.valueOf((String)map.get("roleId")), SysLog.OPERATION_TYPE_UPDATE, UserHolder.getUserContext().getUserCode()+" 更新 TB_BASE_ROLE");
		
		Long count = this.roleDaoIbts.queryRoleNameIsExistForUpdate(map);
		if(count > 0){
			return -1;
		}
		
		int cnt = this.roleDaoIbts.updateRole(map);
		
		if(cnt>0){
			this.menuDaoIbts.deleteRoleMenu(map);
			List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
			for(int i=0;i<menuList.length;i++){
				Map<String,Object> tmpMap = new HashMap<String, Object>();
				tmpMap.put("roleId", map.get("roleId"));
				tmpMap.put("menuId", menuList[i]);
				tmpMap.put("createUserId", UserHolder.getUserContext().getUserId());
				list1.add(tmpMap);				
			}
			if(list1.size()>0){
				this.menuDaoIbts.addRoleMenu(list1);
			}
		}		
		return cnt;
	}

	public List<RoleResourceVO> queryRoleResouceByRole(Map map)
			throws SQLException {
		// TODO Auto-generated method stub
		return this.roleDaoIbts.queryRoleResouceByRole(map);
	}

	public List<TBRole> queryRoleListForVO(Map map) throws SQLException {
		// TODO Auto-generated method stub
		return this.roleDaoIbts.queryRoleListForVO(map);
	}
	/*
	 * 保存用户权限地区关联表
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public int saveUserRoleArea(List<Map<String,Object>> list,Long userId) throws SQLException{
		int rtnCnt = 0;
		if(userId!=null){
			Map map = new HashMap<String, Object>();
			map.put("userId", userId);
			rtnCnt = this.roleDaoIbts.deleteUserRoleArea(map);
		}
		
		if(list!=null && list.size()>0){//新增 用户权限地区关联
			rtnCnt = this.roleDaoIbts.addUserRoleArea(list);	
		}
		
		return rtnCnt;
	}
	
	public Map<String,List<Long>> queryUserRoleArea(Map map) throws SQLException{
		Map<String,List<Long>> rtnMap = new HashMap<String,List<Long>>();
		List<Map<String,Object>> resultList = this.roleDaoIbts.queryUserRoleArea(map);
		for(int i=0;i<resultList.size();i++){
			if(rtnMap.get(((BigDecimal)resultList.get(i).get("ROLE_ID")).toString())!=null){
				rtnMap.get(((BigDecimal)resultList.get(i).get("ROLE_ID")).toString()).add(((BigDecimal)resultList.get(i).get("AREA_ID")).longValue());
			}else{
				List<Long> tmpList = new ArrayList<Long>();
				tmpList.add(((BigDecimal)resultList.get(i).get("AREA_ID")).longValue());
				rtnMap.put(((BigDecimal)(resultList.get(i).get("ROLE_ID"))).toString(),tmpList );
			}
		}
		return rtnMap;
	}
	
	public Map<Long,String> queryUserRoleAreaList(Long userId) throws SQLException{
		Map<Long,String> map = new HashMap<Long,String>();
		List<Map<String,Object>> list = this.roleDaoIbts.queryUserRoleAreaList(userId);
		for(int i=0;i<list.size();i++){
			map.put(((BigDecimal)(list.get(i).get("ROLE_ID"))).longValue(), list.get(i).get("IDLIST")==null?"":(String)list.get(i).get("IDLIST"));
		}
		return map;
	}
	
	public boolean hasAuthority(long roleId){
		boolean flag = false;
		List<TBRole> list = UserHolder.getUserContext().getRoleList();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getRoleId().longValue() == roleId){
				flag = true;
				break;
			}
		}
		return flag;
	}
}
