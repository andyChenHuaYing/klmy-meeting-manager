package com.service.sys;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.base.IBaseEntityDao;
import com.vo.UserVO;

@Service
public class UserServiceTest {
	@Autowired
	private IBaseEntityDao<UserVO, Long> dao;
	/**
	 * 根据主键得到一个用户
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public UserVO getUserById(Long userId) throws SQLException{
		return dao.getEntityById("user.getUserById", userId);
	}
}
