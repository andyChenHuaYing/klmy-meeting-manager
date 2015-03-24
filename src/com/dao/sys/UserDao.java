package com.dao.sys;

import com.vo.UserVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserDao {
	public List<UserVO> queryUser(Map map);
	public List<Map<String,Object>> queryUserForMap(Map map);
	public Long addUser(Map map);
	public int queyrUserCntForMap(Map map);
	public int deleteUserRole(Map map);
	public int deleteUser(Map map);
	public int updateUser(Map map);
	public List<Map<String, Object>> queryUserForMapNew(Map map);
	public Integer queryUserNamesNumber(Map map);

    Long queryUserAreaId(Long updatedUserId) throws SQLException;
}
