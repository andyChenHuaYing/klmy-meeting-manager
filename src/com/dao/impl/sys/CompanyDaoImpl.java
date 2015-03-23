package com.dao.impl.sys;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.dao.base.BaseDao;
import com.dao.sys.CompanyDao;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.vo.CompanyVo;
@Repository("companyDao")
public class CompanyDaoImpl extends BaseDao implements CompanyDao{
	private final static Logger logger = LoggerFactory.getLogger(CompanyDaoImpl.class);
	@Override
	public List<CompanyVo> queryCompany(Map map) {
		List<CompanyVo> list = null;
		try {
			list = this.sqlMapClient.queryForList("company.queryCompany", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("company.queryCompany"+e.getMessage());
			e.printStackTrace();
		}
		return list;
	}	
}
