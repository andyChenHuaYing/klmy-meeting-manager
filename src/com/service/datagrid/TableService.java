package com.service.datagrid;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.base.IBaseEntityDao;
import com.vo.table.TConfigTable;

@Service("gridService")
public class TableService {
	
	@Autowired
	private IBaseEntityDao<TConfigTable, String> dao;
	
	public TConfigTable queryTableById(String id) throws SQLException{
		return dao.getEntityById("table.queryTableById", id);
	}
	public List<TConfigTable> queryAllTable() throws SQLException{
		return dao.getEntities("table.queryAllTable");
	}
}
