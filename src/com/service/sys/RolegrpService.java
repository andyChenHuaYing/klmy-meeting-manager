package com.service.sys;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.util.UserHolder;
import com.vo.TbBaseRolegrp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("rolegrpService")
public class RolegrpService {
	
	@Autowired
	@Qualifier("sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	@Autowired
	private RoleService roleService;
	
	private Logger logger = LoggerFactory.getLogger(RolegrpService.class);
	
	/** 
	 * @Title: queryRolegrp 
	 * @Description: 查询权限组
	 * @param 
	 * @return List<TbBaseRolegrp>
	 * @throws 
	*/
	@SuppressWarnings("unchecked")
	public List<TbBaseRolegrp> queryRolegrp(@SuppressWarnings("rawtypes") Map map){
		List<TbBaseRolegrp> list = null;
		try {
			if(map != null && map.get("pageSize")!=null && map.get("currPage")!=null){
				list = this.sqlMapClient.queryForList("rolegrp.queryRolegrp", map,(Integer)map.get("pageSize")*((Integer)map.get("currPage")-1),(Integer)map.get("pageSize"));
			}else{
				list = this.sqlMapClient.queryForList("rolegrp.queryRolegrp", map);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("role.queryRole:"+e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	/** 
	 * @Title: queryRolegrpCnt 
	 * @Description: 查询权限组数量
	 * @param 
	 * @return int
	 * @throws 
	*/
	@SuppressWarnings("rawtypes")
	public int queryRolegrpCnt(Map map) throws SQLException{
		return (Integer)this.sqlMapClient.queryForObject("rolegrp.queryRolegrpCnt", map);	
	}
	
	/** 
	 * @Title: addRolegrp 
	 * @Description: 新增权限组
	 * @param 
	 * @return int
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Long addRolegrp(Map map) throws SQLException{
		map.put("userId", UserHolder.getUserContext().getUserId());
		return (Long)this.sqlMapClient.insert("rolegrp.addRolegrp",map);
	}
	
	/** 
	 * @Title: updateRolegrp 
	 * @Description: 修改权限组
	 * @param 
	 * @return int
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int updateRolegrp(Map map) throws SQLException{
		map.put("modifyUserId", UserHolder.getUserContext().getUserId());
		return this.sqlMapClient.update("rolegrp.updateRolegrp",map);
	}
	
	/** 
	 * @Title: deleteRolegrp 
	 * @Description: 删除权限组
	 * @param 
	 * @return int
	 * @throws 
	*/
	@SuppressWarnings("rawtypes")
	public int deleteRolegrp(Map map) throws SQLException{
		return this.sqlMapClient.delete("rolegrp.deleteRolegrp",map);
	}
	
	/** 
	 * @Title: addRolegrpRole 
	 * @Description: 新增权限组与权限的关联关系
	 * @param 
	 * @return int
	 * @throws 
	*/
	@SuppressWarnings("rawtypes")
	public int addRolegrpRole(Map map) throws SQLException {
		return this.sqlMapClient.update("rolegrp.addRolegrpRole",map);
	}
	
	/** 
	 * @Title: deleteRolegrpRole 
	 * @Description: 删除权限组与权限的关联关系
	 * @param 
	 * @return int
	 * @throws 
	*/
	@SuppressWarnings("rawtypes")
	public int deleteRolegrpRole(Map map) throws SQLException {
		return this.sqlMapClient.delete("rolegrp.deleteRolegrpRole",map);
	}
	/** 
	 * @Title: addUserRolegrp 
	 * @Description: 新增用户与权限组的关联关系
	 * @param 
	 * @return int
	 * @throws 
	*/
	@SuppressWarnings("rawtypes")
	public int addUserRolegrp(Map map) throws SQLException {
		return this.sqlMapClient.update("rolegrp.addUserRolegrp",map);
	}
	/** 
	 * @Title: deleteUserRolegrp 
	 * @Description: 删除用户与权限组的关联关系
	 * @param 
	 * @return int
	 * @throws 
	*/
	@SuppressWarnings("rawtypes")
	public int deleteUserRolegrp(Map map) throws SQLException {
		return this.sqlMapClient.delete("rolegrp.deleteUserRolegrp",map);
	}
	
	/** 
	 * @Title: addUserRolegrpArea 
	 * @Description: 新增用户权限组地区关联关系
	 * @param 
	 * @return int
	 * @throws 
	*/
	@SuppressWarnings("rawtypes")
	public int addUserRolegrpArea(Map map) throws SQLException{
		return this.sqlMapClient.update("rolegrp.addUserRolegrpArea",map);
	}
	
	/** 
	 * @Title: queryUserRolegrp 
	 * @Description: 查询用户权限组关联关系
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map<String,Object>> queryUserRolegrp(Map map) throws SQLException{
		return this.sqlMapClient.queryForList("rolegrp.queryUserRolegrp", map);
	}
	
	/** 
	 * @Title: queryUserRolegrpArea 
	 * @Description: 查询用户权限组地区的关联关系
	 * @param 
	 * @return Map<String,List<Long>>
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String,List<Long>> queryUserRolegrpArea(Map map) throws SQLException{
		Map<String,List<Long>> rtnMap = new HashMap<String,List<Long>>();
		List<Map<String,Object>> resultList = this.sqlMapClient.queryForList("rolegrp.queryUserRolegrpArea", map);
		for(int i=0;i<resultList.size();i++){
			if(rtnMap.get(((BigDecimal)resultList.get(i).get("ROLEGRP_ID")).toString())!=null){
				rtnMap.get(((BigDecimal)resultList.get(i).get("ROLEGRP_ID")).toString()).add(((BigDecimal)resultList.get(i).get("AREA_ID")).longValue());
			}else{
				List<Long> tmpList = new ArrayList<Long>();
				tmpList.add(((BigDecimal)resultList.get(i).get("AREA_ID")).longValue());
				rtnMap.put(((BigDecimal)(resultList.get(i).get("ROLEGRP_ID"))).toString(),tmpList );
			}
		}
		return rtnMap;
	}
	
	/** 
	 * @Title: deleteUserRolegrpArea 
	 * @Description: 删除用户权限组地区关联关系
	 * @param 
	 * @return int
	 * @throws 
	*/
	@SuppressWarnings("rawtypes")
	public int deleteUserRolegrpArea(Map map) throws SQLException{
		return this.sqlMapClient.delete("rolegrp.deleteUserRolegrpArea",map);
	}
	
	/** 
	 * @Title: queryRoleList 
	 * @Description: 查询权限组待选权限点列表和已选权限点列表
	 * @param 
	 * @return Map<String,Object>
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String,Object> queryRoleList(Long rolegrpId){
		Map<String,Object> rtnMap = new HashMap<String, Object>();
		Map map = new HashMap<String, Object>();
		map.put("orderByRoleNameFlag", true);
		if(rolegrpId==null){
			List<Map<String, Object>> roleList = this.roleService.queryRole(map);
			rtnMap.put("roleList", roleList);
		}else{
			map.put("offRolegrpId", rolegrpId);
			List<Map<String, Object>> roleList = this.roleService.queryRole(map);
			rtnMap.put("roleList", roleList);
			map.remove("offRolegrpId");
			map.put("rolegrpId", rolegrpId);
			List<Map<String, Object>> selectedRoleList = this.roleService.queryRole(map);
			rtnMap.put("selectedRoleList", selectedRoleList);
		}
		return rtnMap;
	}
	
	/**
	 * @throws SQLException 
	 * @Title: queryRoleGroupNameCount
	 * @Description: 新增修改时、查询指定权限组名称个数。
	 * @param 
	 * @return int
	 * @throws 
	 */
	@SuppressWarnings("rawtypes") 
	public Integer queryRoleGroupNameCount(Map map) throws SQLException{
		return (Integer)this.sqlMapClient.queryForObject("rolegrp.queryRoleGroupNameCount", map);
	}
	
	/** 
	 * @Title: addRolegrpInfo 
	 * @Description: 新增权限组，绑定权限组与权限点的关联关系
	 * @param 
	 * @return int
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int addRolegrpInfo(Map map) throws SQLException{
		int rtn=0;
		map.put("rolegrpId", null);
		int count = this.queryRoleGroupNameCount(map);
		if(count > 0){
			return -1;
		}
		Long rolegrpId = this.addRolegrp(map);
		if(rolegrpId!=null){
			rtn=1;
		}
		if(map.containsKey("roleList")){
			List<Long> roleList = (ArrayList<Long>)map.get("roleList");
			for(int i=0;i<roleList.size();i++){
				Map tmpMap = new HashMap<String, Object>();
				tmpMap.put("rolegrpId", rolegrpId);
				tmpMap.put("roleId", roleList.get(i));
				this.addRolegrpRole(tmpMap);
			}
		}
		return rtn;
	}
	
	/** 
	 * @Title: updateRolegrpInfo 
	 * @Description: 修改权限组
	 * @param 
	 * @return int
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int updateRolegrpInfo(Map map,Long rolegrpId) throws SQLException{
		int rtn=0;
		map.put("rolegrpId", rolegrpId);
		int count = this.queryRoleGroupNameCount(map);
		if(count > 0){
			return -1;
		}
		
		rtn = this.updateRolegrp(map);
		//如果更新成功，则先删除与权限点的关联关系，再新增
		if(rtn>0 && map.containsKey("roleList")){
			this.deleteRolegrpRole(map);
			List<Long> roleList = (ArrayList<Long>)map.get("roleList");
			for(int i=0;i<roleList.size();i++){
				Map tmpMap = new HashMap<String, Object>();
				tmpMap.put("rolegrpId", rolegrpId);
				tmpMap.put("roleId", roleList.get(i));
				this.addRolegrpRole(tmpMap);
			}
		}
		return rtn;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int deleteRolegrpInfo(Long rolegrpId) throws SQLException{
		int rtn;
		Map map = new HashMap<String,Object>();
		map.put("rolegrpId", rolegrpId);
		this.deleteRolegrpRole(map);
		rtn = this.deleteRolegrp(map);
		return rtn;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int saveUserRolegrpArea(List<Map<String,Object>> list,Long userId) throws SQLException{
		int rtnCnt = 0;
		if(userId!=null){
			Map map = new HashMap<String, Object>();
			map.put("userId", userId);
			rtnCnt = this.deleteUserRolegrpArea(map);
		}
		
		if(list!=null && list.size()>0){//新增 用户权限地区关联
			for(int i=0;i<list.size();i++){
				this.addUserRolegrpArea(list.get(i));
			}
				
		}
		return rtnCnt;
	}
	
}
