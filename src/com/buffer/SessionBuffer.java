package com.buffer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.vo.SessionVO;



/**
 * <p>
 * Description: 用来缓存页面生成的字符串，只对新增有效
 * </p>
 * 
 * @author xiaogang.gu on 2014.4.24
 **/
public class SessionBuffer {
	private static SessionBuffer instance = null;
	//所有菜单对象
//	private Map<Long, TBMenu> menuMap = null;
	//用户菜单对象
	private Map<String,SessionVO> sessionMap = null;
	private ReadWriteLock rwLock = null;


	/**
	 * 单例模式
	 * 
	 * @return
	 */
	public static SessionBuffer getInstance() {
		if (instance == null) {
			instance = new SessionBuffer();
			try {
				instance.initBuffer();
			} catch (Exception e) {
				instance = null;
				e.printStackTrace();
			}
		}
		return instance;
	}

	private SessionBuffer() {
		sessionMap = new ConcurrentHashMap<String, SessionVO>();
		rwLock = new ReentrantReadWriteLock();
	}

	private void initBuffer() {
		//初始化缓存，对缓存中所生命对象进行赋值。
	}
	/**
	 * 得到某个页面的配置信息
	 * @param key
	 * @return
	 */
	public SessionVO getSession(String sessionId) {
		try {
			rwLock.readLock().lock();
			return sessionMap.get(sessionId);
		} finally {
			rwLock.readLock().unlock();
		}
	}
	
	
	public void setSession(SessionVO value,String sessionId) {
		try {
			rwLock.writeLock().lock();
			if (null != sessionMap.get(sessionId)) {
				
			} else {
				sessionMap.put(sessionId, value);
			}
		} finally {
			rwLock.writeLock().unlock();
		}
	}
		
	
	public void removeSession(String sessionId) {
		try {
			rwLock.writeLock().lock();
			sessionMap.remove(sessionId);
		} finally {
			rwLock.writeLock().unlock();
		}
	}
	
	
	public void updatePageString(SessionVO value,String sessionId) {
		removeSession(sessionId);
		setSession(value, sessionId);
	}
	
	public Map<String,SessionVO> getSession(){
		return this.sessionMap;
	}
	
}
