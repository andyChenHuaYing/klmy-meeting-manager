package com.dao.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.ContextLoader;

import com.dao.sys.LogDao;
import com.ibatis.sqlmap.client.SqlMapClient;

public class BaseDao {
	@Autowired
	@Qualifier("sqlMapClient")
	public SqlMapClient sqlMapClient;
	public Logger logger = LoggerFactory.getLogger(this.getClass());
}
