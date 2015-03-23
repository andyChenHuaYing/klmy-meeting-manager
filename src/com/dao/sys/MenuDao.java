package com.dao.sys;

import java.util.List;
import java.util.Map;

public interface MenuDao {
	public List<Map<String,Object>> findMenu(Map map);
	public int addRoleMenu(List<Map<String,Object>> list);
	public int deleteRoleMenu(Map map);	
}
