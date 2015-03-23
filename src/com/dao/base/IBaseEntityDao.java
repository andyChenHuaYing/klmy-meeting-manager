package com.dao.base;


import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Description: 基础的实体数据库访问接口，提供基本的实体操作方法。
 * </p>
 * 
 * @author charles.chen on 2012-7-16
 **/
public interface IBaseEntityDao<T, PK extends Serializable> {
	/**
	 * 根据实体主键ID得到某个实体对象
	 * 
	 * @param statementName
	 * @param entityId
	 * @return
	 */
	public T getEntityById(String statementName, PK entityId) throws SQLException;

	/**
	 * 根据多个条件得到某个实体对象
	 * 
	 * @param statementName
	 * @param parameterMap
	 * @return
	 */
	public T getEntityByConditions(String statementName, Map<String, ?> parameterMap) throws SQLException;

	/**
	 * 得到实体兑现集合
	 * 
	 * @param statementName
	 * @return
	 */
	public List<T> getEntities(String statementName) throws SQLException;

	/**
	 * 根据特定条件得到实体对象集合
	 * 
	 * @param statementName
	 * @param parameterMap
	 * @return
	 */
	public List<T> getEntities(String statementName, Map<String, ?> parameterMap) throws SQLException;
	
	
	/*
	 * 
	 */
	public List<T> getEntities(String statementName, Map<String, ?> parameterMap,Integer startNum,Integer rows) throws SQLException;
	/**
	 * 增加一个实体
	 * 
	 * @param statementName
	 * @param t
	 */
	public void addEntity(String statementName, T t) throws SQLException;

	/**
	 * 修改一个实体
	 * 
	 * @param statementName
	 * @param t
	 */
	public void updateEntity(String statementName, T t) throws SQLException;
	
	/**
	 * 根据条件修改一个实体
	 * 
	 * @param statementName
	 * @param parameterMap
	 */
	public void updateEntity(String statementName, Map<String, ?> parameterMap) throws SQLException;

	/**
	 * 删除一个实体
	 * 
	 * @param statementName
	 * @param entityId
	 */
	public void deleteEntityById(String statementName, PK entityId) throws SQLException;
	/**
	 * 根据条件删除一个实体
	 * 
	 * @param statementName
	 * @param parameterMap
	 */
	public void deleteEntityByConditions(String statementName, Map<String, ?> parameterMap) throws SQLException;
	
	/*
	 * 根据条件查询记录书
	 * 
	 * @param statementName
	 * @param paramterMap
	 */
	public int queryEntityCount(String statementName,Map<String,?> parameterMap) throws SQLException;
	
}
