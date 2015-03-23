package com.service.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dao.sys.UnitDao;
import com.vo.UnitVO;
@Service("unitService")
public class UnitService{
	@Autowired
	@Qualifier("unitDao")
	private UnitDao unitDaoIbts;
	public List<UnitVO> queryUnit(Map map) {
		// TODO Auto-generated method stub
		return unitDaoIbts.queryUnit(map);
	}

	public List<UnitVO> queryUnitByUser(Map map) {
		// TODO Auto-generated method stub
		return unitDaoIbts.queryUnitByUser(map);
	}

	public int deleteUnit(Map map) {
		// TODO Auto-generated method stub
		return unitDaoIbts.deleteUnit(map);
	}

	public int addRoleUnit(Map map) {
		// TODO Auto-generated method stub
		return unitDaoIbts.addRoleUnit(map);
	}

	public List<Map<String, Object>> queryUnitForMap(Map map) {
		// TODO Auto-generated method stub
		return this.unitDaoIbts.queryUnitForMap(map);
	}

}
