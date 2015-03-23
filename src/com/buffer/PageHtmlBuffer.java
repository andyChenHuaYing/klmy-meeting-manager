package com.buffer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;



/**
 * <p>
 * Description: 用来缓存页面生成的字符串，只对新增有效
 * </p>
 * 
 * @author xiaogang.gu on 2014.4.24
 **/
public class PageHtmlBuffer {
	private static PageHtmlBuffer instance = null;
	//所有菜单对象
//	private Map<Long, TBMenu> menuMap = null;
	//用户菜单对象
	private Map<String,String> pageHtmlMap = null;
	private Map<String,String> tableHtmlMap = null;
	private ReadWriteLock rwLock = null;


	/**
	 * 单例模式
	 * 
	 * @return
	 */
	public static PageHtmlBuffer getInstance() {
		if (instance == null) {
			instance = new PageHtmlBuffer();
			try {
				instance.initBuffer();
			} catch (Exception e) {
				instance = null;
				e.printStackTrace();
			}
		}
		return instance;
	}

	private PageHtmlBuffer() {
		pageHtmlMap = new ConcurrentHashMap<String, String>();
		tableHtmlMap = new ConcurrentHashMap<String, String>();
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
	public String getPageStringByPageTag(String pageTag) {
		try {
			rwLock.readLock().lock();
			return pageHtmlMap.get(pageTag);
		} finally {
			rwLock.readLock().unlock();
		}
	}
	
	public String getTableStringByTableId(String tableId){
		try{
			rwLock.readLock().lock();
			return tableHtmlMap.get(tableId);
		}finally{
			rwLock.readLock().unlock();
		}
	}
	
	public void setPageString(String value,String pageTag) {
		try {
			rwLock.writeLock().lock();
			if (null != pageHtmlMap.get(pageTag)) {
				
			} else {
				pageHtmlMap.put(pageTag, value);
			}
		} finally {
			rwLock.writeLock().unlock();
		}
	}
	
	public void setTableString(String value,String tableId){
		try{
			rwLock.writeLock().lock();
			if(null != tableHtmlMap.get(tableId)){
				
			}else{
				tableHtmlMap.put(tableId, value);
			}
		}finally{
			rwLock.writeLock().unlock();
		}
	}
	
	public void removePageString(String pageTag) {
		try {
			rwLock.writeLock().lock();
			pageHtmlMap.remove(pageTag);
		} finally {
			rwLock.writeLock().unlock();
		}
	}
	
	public void removeTableString(String tableId){
		try{
			rwLock.writeLock().lock();
			tableHtmlMap.remove(tableId);
		}finally{
			rwLock.writeLock().unlock();
		}
	}
	
	public void updatePageString(String value,String pageTag) {
		removePageString(pageTag);
		setPageString(value,pageTag);
	}
	
	public void updateTableString(String value,String tableId){
		removeTableString(tableId);
		setTableString(value, tableId);
	}
	
	public Map<String,String> getPageHtml(){
		return this.pageHtmlMap;
	}
	
	public Map<String,String> getTableHtml(){
		return this.tableHtmlMap;
	}
	
}
