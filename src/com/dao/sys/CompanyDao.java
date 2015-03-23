package com.dao.sys;

import java.util.List;
import java.util.Map;

import com.vo.CompanyVo;

public interface CompanyDao {
	public List<CompanyVo> queryCompany(Map map);	
}
