package com.dao.sys;

import java.util.List;
import java.util.Map;

import com.vo.UnitVO;

public interface UnitDao {
	public List<UnitVO> queryUnit(Map map);
	public List<UnitVO> queryUnitByUser(Map map);
	public int deleteUnit(Map map);
	public int addRoleUnit(Map map);
	public List<Map<String,Object>> queryUnitForMap(Map map);
	public int saveRoleUnit(List<Map<String,Object>> list);
	public int deleteRoleUnit(Map map);
}
