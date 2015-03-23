package com.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.base.IBaseEntityDao;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.vo.TConfigArea;
import com.vo.TbBaseDepartment;

@Service("areaService")
public class AreaService {
	@Autowired
	private IBaseEntityDao<TConfigArea, Long> dao;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<TConfigArea> queryAreaByMap(Map map) throws SQLException{
		return dao.getEntities("area.queryArea", map);
	}
	@Autowired
	@Qualifier("sqlMapClient")
	public SqlMapClient sqlMapClient;
	
	/**
	 * @throws SQLException  
	 * @Title: queryDeptTree 
	 * @Description: 返回地区树，查询已经是递归查询了，支持到10级
	 * @param 
	 * @return List<TbBaseDepartment>
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TConfigArea> queryAreaTreeVo() throws SQLException{
		Map map = new HashMap<String, Object>();
		map.put("recursion", true);
		List<TConfigArea> list = this.queryAreaByMap(map);		
		List<TConfigArea> rtnList = new ArrayList<TConfigArea>();
		for(int i=0;i<list.size();i++){
			TConfigArea tmpDeptVO = list.get(i);
			//就支持到4级
			if(tmpDeptVO.getAreaLevel().intValue()==1){
				rtnList.add(tmpDeptVO);
			}else if(tmpDeptVO.getAreaLevel().intValue()==2){
				TConfigArea tmpDeptVO1 = rtnList.get(rtnList.size()-1);
				if(tmpDeptVO1.getChildren()==null){
					List<TConfigArea> tmpList1 = new ArrayList<TConfigArea>();
					tmpList1.add(tmpDeptVO);
					tmpDeptVO1.setChildren(tmpList1);
				}else{
					tmpDeptVO1.getChildren().add(tmpDeptVO);
				}
			}else if(tmpDeptVO.getAreaLevel().intValue()==3){
				TConfigArea tmpDeptVO2 = rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1);
				if(tmpDeptVO2.getChildren()==null){
					List<TConfigArea> tmpList2 = new ArrayList<TConfigArea>();
					tmpList2.add(tmpDeptVO);
					tmpDeptVO2.setChildren(tmpList2);
				}else{
					tmpDeptVO2.getChildren().add(tmpDeptVO);
				}
			}else if(tmpDeptVO.getAreaLevel().intValue()==4){
				TConfigArea tmpDeptVO2 = rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1);
				if(tmpDeptVO2.getChildren()==null){
					List<TConfigArea> tmpList2 = new ArrayList<TConfigArea>();
					tmpList2.add(tmpDeptVO);
					tmpDeptVO2.setChildren(tmpList2);
				}else{
					tmpDeptVO2.getChildren().add(tmpDeptVO);
				}
			}else if(tmpDeptVO.getAreaLevel().intValue()==5){
				TConfigArea tmpDeptVO2 = rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1);
				if(tmpDeptVO2.getChildren()==null){
					List<TConfigArea> tmpList2 = new ArrayList<TConfigArea>();
					tmpList2.add(tmpDeptVO);
					tmpDeptVO2.setChildren(tmpList2);
				}else{
					tmpDeptVO2.getChildren().add(tmpDeptVO);
				}
			}else if(tmpDeptVO.getAreaLevel().intValue()==6){
				
				TConfigArea tmpDeptVO2 = rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1);
				if(tmpDeptVO2.getChildren()==null){
					List<TConfigArea> tmpList2 = new ArrayList<TConfigArea>();
					tmpList2.add(tmpDeptVO);
					tmpDeptVO2.setChildren(tmpList2);
				}else{
					tmpDeptVO2.getChildren().add(tmpDeptVO);
				}
			}else if(tmpDeptVO.getAreaLevel().intValue()==7){
				
				TConfigArea tmpDeptVO2 = rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1);
				if(tmpDeptVO2.getChildren()==null){
					List<TConfigArea> tmpList2 = new ArrayList<TConfigArea>();
					tmpList2.add(tmpDeptVO);
					tmpDeptVO2.setChildren(tmpList2);
				}else{
					tmpDeptVO2.getChildren().add(tmpDeptVO);
				}
			}else if(tmpDeptVO.getAreaLevel().intValue()==8){
				TConfigArea tmpDeptVO2 = rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1);
				if(tmpDeptVO2.getChildren()==null){
					List<TConfigArea> tmpList2 = new ArrayList<TConfigArea>();
					tmpList2.add(tmpDeptVO);
					tmpDeptVO2.setChildren(tmpList2);
				}else{
					tmpDeptVO2.getChildren().add(tmpDeptVO);
				}
			}else if(tmpDeptVO.getAreaLevel().intValue()==9){
				TConfigArea tmpDeptVO2 = rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1);
				if(tmpDeptVO2.getChildren()==null){
					List<TConfigArea> tmpList2 = new ArrayList<TConfigArea>();
					tmpList2.add(tmpDeptVO);
					tmpDeptVO2.setChildren(tmpList2);
				}else{
					tmpDeptVO2.getChildren().add(tmpDeptVO);
				}
			}else if(tmpDeptVO.getAreaLevel().intValue()==10){
				TConfigArea tmpDeptVO2 = rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().get(rtnList.get(rtnList.size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1).getChildren().size()-1);
				if(tmpDeptVO2.getChildren()==null){
					List<TConfigArea> tmpList2 = new ArrayList<TConfigArea>();
					tmpList2.add(tmpDeptVO);
					tmpDeptVO2.setChildren(tmpList2);
				}else{
					tmpDeptVO2.getChildren().add(tmpDeptVO);
				}
			}
		}
		return rtnList;
	}
	/** 
	 * @Title: queryAreaTree2 
	 * @Description: 地区树，满足 jquery easyui tree 格式
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	*/
	
	/*
	 * json格式
	 * [{    
    "id":1,    
    "text":"Folder1",    
    "iconCls":"icon-save",    
    "children":[{    
        "text":"File1",    
        "checked":true   
    },{    
        "text":"Books",    
        "state":"open",    
        "attributes":{    
            "url":"/demo/book/abc",    
            "price":100    
        },    
        "children":[{    
            "text":"PhotoShop",    
            "checked":true   
        },{    
            "id": 8,    
            "text":"Sub Bookds",    
            "state":"closed"   
        }]    
    }]    
},{    
    "text":"Languages",    
    "state":"closed",    
    "children":[{    
        "text":"Java"   
    },{    
        "text":"C#"   
    }]    
}]  

	 * 
	 */
	public List<Map<String,Object>> queryAreaTree2(List<TConfigArea> areaList,List<Map<String,Object>> list){
		if(list==null){
			list = new ArrayList<Map<String,Object>>();
		}		
		if(areaList!=null && areaList.size()>0){
			for(int i=0;i<areaList.size();i++){
				Map<String,Object> tMap = new HashMap<String, Object>();
				list.add(tMap);
				tMap.put("id", areaList.get(i).getAreaId());
				tMap.put("text", areaList.get(i).getAreaName());
				if(areaList.get(i).getChildren()!=null&&areaList.get(i).getChildren().size()>0){
					tMap.put("state", "closed");
					List<Map<String,Object>> tmpList = new ArrayList<Map<String,Object>>();
					tMap.put("children", tmpList);
					queryAreaTree2(areaList.get(i).getChildren(), tmpList);
				}
			}
		}
		return list;
	}
	
	
	@SuppressWarnings({ "finally", "rawtypes" })
	public int updateArea(Map map){
		int cnt = -1;
		try {
			int count = (Integer)this.sqlMapClient.queryForObject("area.queryAreaNameNumber", map);
			if(count > 0){ 
				return -1; 
			}else{
				cnt = this.sqlMapClient.update("area.updateArea",map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return cnt;
		}
	}
	
	@SuppressWarnings({ "finally", "rawtypes" })
	public int updateAreaLevel(Map map){
		int cnt = 0;
		try {
			cnt = this.sqlMapClient.update("area.updateAreaLevel",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return cnt;
		}
	}
	@SuppressWarnings({ "finally", "rawtypes" })
	public int deleteArea(Map map){
		int cnt = 0;
		try {
			cnt = this.sqlMapClient.delete("area.deleteArea", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return cnt;
		}
	}
	
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	public int addArea(Map map){
		int cnt = -1;
		try {
			map.put("areaId", null);
			int count = (Integer)this.sqlMapClient.queryForObject("area.queryAreaNameNumber", map);
			if(count > 0){ 
				return -1; 
			}else{
				cnt = this.sqlMapClient.update("area.addArea",map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return cnt;
		}
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int updateDeptService(Map map) throws SQLException{
		if(!map.containsKey("parentAreaId")){
			map.put("nullParentAreaId", true);
		}
		int cnt = 0;
		cnt = this.updateArea(map);
		//更新部门级别
		Map tmpMap = new HashMap<String,Object>();
		tmpMap.put("parentAreaId",map.get("AreaId"));
		tmpMap.put("modifyUserId", map.get("modifyUserId"));
		this.updateAreaLevelService(tmpMap);
		return cnt;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateAreaLevelService(Map map) throws SQLException{
		this.updateAreaLevel(map);
		List<TConfigArea> list = this.queryAreaByMap(map);
		for(int i=0;i<list.size();i++){
			Map tmpMap = new HashMap<String, Object>();
			tmpMap.put("parentAreaId",list.get(i).getParentAreaId());
			tmpMap.put("modifyUserId", map.get("modifyUserId"));
			updateAreaLevelService(tmpMap);
		}
		
	}
}
