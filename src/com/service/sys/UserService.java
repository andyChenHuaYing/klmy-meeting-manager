package com.service.sys;

import com.dao.sys.RoleDao;
import com.dao.sys.UserDao;
import com.util.SysLog;
import com.util.UserHolder;
import com.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
@Service("userService")
public class UserService {
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	@Autowired
	@Qualifier("roleDao")
	private RoleDao roleDaoIbts;
	@Autowired
	@Qualifier("personService")
	private PersonService personService;
	
	@Autowired
	private RolegrpService rolegrpService;
	
	@SuppressWarnings("rawtypes")
	public List<UserVO> queryUser(Map map){
		return this.userDao.queryUser(map);
	}
	
	@SuppressWarnings("rawtypes")
	public UserVO queryUniqueUser(Map map){
		List<UserVO> list = this.userDao.queryUser(map);
		if(null != list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Long addUser(Map map){
		map.put("userId", null);
		int count = this.userDao.queryUserNamesNumber(map);
		if(count > 0 ){
			return -1L;
		}
		return this.userDao.addUser(map);
	}
	@SuppressWarnings("rawtypes")
	public Long addUserAndPerson(Map map){//�������Ա�������Ա��ӳɹ�������û�������ֱ�ӷ���
		Long personId = this.personService.addPerson(map);
		SysLog.logRecord("TB_BASE_PERSON", personId, SysLog.OPERATION_TYPE_ADD, UserHolder.getUserContext()==null?"":UserHolder.getUserContext().getUserCode()+" ���� TB_BASE_PERSON");
		if(personId == null || personId == 0L){
			return 0L;
		}
		map.put("personId", personId);
		map.put("coId", UserHolder.getUserContext()==null?10000000000001L:UserHolder.getUserContext().getCoId());
		Long userId = this.addUser(map);
		SysLog.logRecord("TB_BASE_USER", userId, SysLog.OPERATION_TYPE_ADD, UserHolder.getUserContext()==null?"":UserHolder.getUserContext().getUserCode()+" ���� TB_BASE_USER");
		if(userId == null || userId == 0L){//����û�������󣬻ع�
			throw new RuntimeException();
		}
		return userId;
	}
	public Long addUserAndUpdatePerson(Map map) {
		// TODO Auto-generated method stub
		int updateCnt = this.personService.updatePerson(map);		
		Long userId = 0L;
		if(updateCnt > 0){
			SysLog.logRecord("TB_BASE_PERSON", map.get("personId")==null?null:(Long)map.get("personId"), SysLog.OPERATION_TYPE_UPDATE, UserHolder.getUserContext()==null?"":UserHolder.getUserContext().getUserCode()+"新增 TB_BASE_PERSON");
			map.put("coId", UserHolder.getUserContext()==null?10000000000001L:UserHolder.getUserContext().getCoId());
			userId = this.addUser(map);
		}else{
			return 0L;
		}
		if(userId == null || userId == 0L){//����û�������󣬻ع�
			throw new RuntimeException();
		}
		SysLog.logRecord("TB_BASE_USER", userId, SysLog.OPERATION_TYPE_ADD, UserHolder.getUserContext()==null?"":UserHolder.getUserContext().getUserCode()+" ���� TB_BASE_USER");
		return userId;
	}
	public List<Map<String, Object>> queryUserForMap(Map map) {
		// TODO Auto-generated method stub
		SysLog.logRecord("TB_BASE_USER", null , SysLog.OPERATION_TYPE_QUERY, UserHolder.getUserContext()==null?"":UserHolder.getUserContext().getUserCode()+" ��ѯ TB_BASE_USER");
		return this.userDao.queryUserForMap(map);
	}
	
	public List<Map<String, Object>> queryUserForMapNew(Map map) {
		// TODO Auto-generated method stub
		SysLog.logRecord("TB_BASE_USER", null , SysLog.OPERATION_TYPE_QUERY, UserHolder.getUserContext()==null?"":UserHolder.getUserContext().getUserCode()+" ��ѯ TB_BASE_USER");
		return this.userDao.queryUserForMapNew(map);
	}
	
	public int queryUserCntForMap(Map map) {
		// TODO Auto-generated method stub
		return this.userDao.queyrUserCntForMap(map);
	}
	public int deleteUser(Map map) throws SQLException {
		// TODO Auto-generated method stub
		this.userDao.deleteUserRole(map);
		this.roleDaoIbts.deleteUserRoleArea(map);
		this.rolegrpService.deleteUserRolegrp(map);
		this.rolegrpService.deleteUserRolegrpArea(map);
		int userCnt = this.userDao.deleteUser(map);
		if(map.get("userIdList")!=null){
			String[] userIdList = (String[])map.get("userIdList");
			for(int i=0;i<userIdList.length;i++){
				SysLog.logRecord("TB_BASE_USER", Long.valueOf(userIdList[i]) , SysLog.OPERATION_TYPE_DELETE, UserHolder.getUserContext()==null?"":UserHolder.getUserContext().getUserCode()+" 删除 TB_BASE_USER");
			}
		}
		if(userCnt<=0){
			throw new RuntimeException("删除失败");
		}
		return userCnt;
	}
	
	@SuppressWarnings("rawtypes")
	public int updateUserForMap(Map map){
//		SysLog.logRecord("TB_BASE_USER", map.get("userId")==null?null:Long.valueOf((String)map.get("userId")) , SysLog.OPERATION_TYPE_UPDATE, UserHolder.getUserContext()==null?"":UserHolder.getUserContext().getUserCode()+" 修改 TB_BASE_USER");
        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_UPDATE, "修改用户信息.");
        return this.userDao.queryUserNamesNumber(map) > 0 ? -1 : this.userDao.updateUser(map);
    }

    public Long queryUserAreaId(Long updatedUserId) throws SQLException {
        return this.userDao.queryUserAreaId(updatedUserId);
    }
}
