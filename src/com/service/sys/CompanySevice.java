package com.service.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dao.sys.CompanyDao;
import com.vo.CompanyVo;
@Component("companyService")
public class CompanySevice{
	
	@Autowired
	@Qualifier("companyDao")
	private CompanyDao companyDaoIbts;
	
	public List<CompanyVo> queryCompany(Map map) {
		// TODO Auto-generated method stub
		return this.companyDaoIbts.queryCompany(map);
	}

}
