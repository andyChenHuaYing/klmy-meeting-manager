package com.dao.sys;

import java.util.List;
import java.util.Map;

import com.vo.TbBaseDepartment;

public interface DepartmentDao {
	public List<Map<String,Object>> queryDepartment(Map map);	
	public List<TbBaseDepartment> queryDepartmentForVo(Map map);
	public int updateDept(Map map);
	public int deleteDept(Map map);
	public int addDept(Map map);
	public int updateDeptLevel(Map map);
	public Integer queryDeptNameNumber(Map map);
}
