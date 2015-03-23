package com.buffer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;

import com.dao.base.IBaseEntityDao;
import com.vo.TBMenu;
import com.vo.TBRole;



/**
 * <p>
 * Description:
 * </p>
 * 
 * @author charles.chen on 2012-7-16
 **/
public class MenuBuffer {
	private static MenuBuffer instance = null;
	//所有菜单对象
//	private Map<Long, TBMenu> menuMap = null;
	//用户菜单对象
	private Map<Long,List<Map<String,Object>>> userMenuMap = null;
	private ReadWriteLock rwLock = null;


	/**
	 * 单例模式
	 * 
	 * @return
	 */
	public static MenuBuffer getInstance() {
		if (instance == null) {
			instance = new MenuBuffer();
			try {
				instance.initBuffer();
			} catch (Exception e) {
				instance = null;
				e.printStackTrace();
			}
		}
		return instance;
	}

	private MenuBuffer() {
//		menuMap = new ConcurrentHashMap<Long,TBMenu>();
		userMenuMap = new ConcurrentHashMap<Long,List<Map<String,Object>>>();
		rwLock = new ReentrantReadWriteLock();
	}

	private void initBuffer() {
		//初始化缓存，对缓存中所生命对象进行赋值。
	}
	/**
	 * 得到某个用户所有菜单
	 * @param key
	 * @return
	 */
	public List<Map<String,Object>> getMenuByUserId(Long userId) {
		try {
			rwLock.readLock().lock();
			return userMenuMap.get(userId);
		} finally {
			rwLock.readLock().unlock();
		}
	}

	public void set(List<Map<String,Object>> value,Long userId) {
		try {
			rwLock.writeLock().lock();
			if (null != userMenuMap.get(userId)) {
				
			} else {
				userMenuMap.put(userId, value);
			}
		} finally {
			rwLock.writeLock().unlock();
		}
	}

	public void remove(Long userId) {
		try {
			rwLock.writeLock().lock();
			userMenuMap.remove(userId);
		} finally {
			rwLock.writeLock().unlock();
		}
	}

	public void update(List<Map<String,Object>> value,Long userId) {
		remove(userId);
		set(value,userId);
	}
	
	public Map<Long,List<Map<String,Object>>> getUserMenuMap(){
		return this.userMenuMap;
	}
}
