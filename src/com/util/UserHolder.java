package com.util;

import com.vo.TBRole;
import com.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 
 * @className: UserHolder 
 * @author: gu.xiaogang
 * @description: 用户持久化类
 * @date: 2014年9月10日
 */
public class UserHolder {
	private static ThreadLocal<UserHolder> threadLocal = new ThreadLocal<UserHolder>();
	private final static Logger logger = LoggerFactory.getLogger(UserHolder.class); 
	private UserVO userContext;
	private List<TBRole> roleList;
	public UserHolder(UserVO userContext){
		this.userContext = userContext;
	}
	public UserHolder(UserVO userContext,List<TBRole> roleList)
	{
		this.userContext = userContext;
		this.roleList = roleList;
	}
	
	public static void setUserContext(UserVO userContext){
		UserHolder userHolder = (UserHolder)threadLocal.get();
		if(userHolder==null){
			userHolder = new UserHolder(userContext);
			threadLocal.set(userHolder);
		}else{
			userHolder.userContext = userContext;
		}
	}
	
	public static void setUserContext(UserVO userContext,List<TBRole> roleList){
		UserHolder userHolder = (UserHolder)threadLocal.get();
		if(userHolder==null){
			userHolder = new UserHolder(userContext,roleList);
			threadLocal.set(userHolder);		
		}else{
			userHolder.userContext = userContext;
			userHolder.roleList = roleList;
		}
	}
	
	public static UserVO getUserContext(){
		UserHolder userHolder = (UserHolder)threadLocal.get();
		if(userHolder == null){
			return null;
		}else{
			return userHolder.userContext;
		}
	}
	
	public static List<TBRole> getRoleList(){
		UserHolder userHolder = (UserHolder)threadLocal.get();
		if(userHolder == null){
			return null;
		}else{
			return userHolder.roleList;
		}
	}

	public static String getUserCode() {
		return getUserContext().getUserCode() == null ? "" : getUserContext().getUserCode();
	}
}
