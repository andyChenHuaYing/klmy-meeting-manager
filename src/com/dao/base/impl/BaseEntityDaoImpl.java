package com.dao.base.impl;


import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.dao.base.IBaseEntityDao;


/**
 * <p>
 * Description:基础的实体数据访问对象。通过泛型进行实体映射，避免每个实体都去写dao的代码。
 * 提供给各个service使用，若您觉得基础实体dao所提供的方法不够用
 * 。您可以自行在service中使用jdbctemplate。或者通过该类的超类SqlSessionDaoSupport取得sqlsession来进行操作。
 * </p>
 * 
 * @author charles.chen on 2012-7-17
 **/
public class BaseEntityDaoImpl<T, PK extends Serializable> extends SqlMapClientDaoSupport implements IBaseEntityDao<T, Serializable> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myideaway.server.framework.dao.IBaseEntityDao#getEntityById(java.
	 * lang.String, java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getEntityById(String statementName, Serializable entityId) throws SQLException {
		// TODO Auto-generated method stub
		return (T)getSqlMapClientTemplate().getSqlMapClient().queryForObject(statementName, entityId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myideaway.server.framework.dao.IBaseEntityDao#getEntityByConditions
	 * (java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getEntityByConditions(String statementName, Map<String, ?> parameterMap) throws SQLException {
		// TODO Auto-generated method stub
		return (T)getSqlMapClientTemplate().getSqlMapClient().queryForObject(statementName, parameterMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myideaway.server.framework.dao.IBaseEntityDao#getEntities(java.lang
	 * .String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getEntities(String statementName) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().getSqlMapClient().queryForList(statementName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myideaway.server.framework.dao.IBaseEntityDao#getEntities(java.lang
	 * .String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getEntities(String statementName, Map<String, ?> parameterMap) throws SQLException{
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().getSqlMapClient().queryForList(statementName,parameterMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myideaway.server.framework.dao.IBaseEntityDao#addEntity(java.lang
	 * .String, java.lang.Object)
	 */
	@Override
	public void addEntity(String statementName, T t) throws SQLException{
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().getSqlMapClient().insert(statementName, t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myideaway.server.framework.dao.IBaseEntityDao#updateEntity(java.lang
	 * .String, java.lang.Object)
	 */
	@Override
	public void updateEntity(String statementName, T t) throws SQLException{
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().getSqlMapClient().update(statementName, t);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myideaway.server.framework.dao.IBaseEntityDao#updateEntity(java.lang
	 * .String, java.lang.Object)
	 */
	@Override
	public void updateEntity(String statementName, Map<String, ?> parameterMap) throws SQLException{
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().getSqlMapClient().update(statementName, parameterMap);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myideaway.server.framework.dao.IBaseEntityDao#deleteEntityById(java
	 * .lang.String, java.io.Serializable)
	 */
	@Override
	public void deleteEntityById(String statementName, Serializable entityId) throws SQLException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().getSqlMapClient().delete(statementName, entityId);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myideaway.server.framework.dao.IBaseEntityDao#deleteEntityByConditions(java
	 * .lang.String, java.io.Serializable)
	 */
	@Override
	public void deleteEntityByConditions(String statementName, Map<String, ?> parameterMap) throws SQLException{
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().getSqlMapClient().delete(statementName, parameterMap);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getEntities(String statementName,
			Map<String, ?> parameterMap, Integer startNum, Integer rows)
			throws SQLException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().getSqlMapClient().queryForList(statementName,parameterMap,startNum,rows);
	}

	@Override
	public int queryEntityCount(String statementName,
			Map<String, ?> parameterMap) throws SQLException {
		int cnt = 0;
		cnt = (Integer)getSqlMapClientTemplate().getSqlMapClient().queryForObject(statementName, parameterMap);
		return cnt;
	}
	
}
