package com.controller.sys;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.AreaService;
import com.util.ResponseUtil;
import com.util.UserHolder;
import com.vo.TConfigArea;
import com.vo.TbBaseDepartment;

/**
 * 
 * @className: AreaController 
 * @author: gu.xiaogang
 * @description: 地区相关功能控制器
 * @date: 2014年9月10日
 */
@Controller(value="areaController")
@RequestMapping("/area.spr")
public class AreaController {
	@Autowired
	@Qualifier("areaService")
	private AreaService areaServcie;
	
	/** 
	 * @Title: toAreaManage 
	 * @Description: 跳转到地区管理页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toAreaManage")
	public String toAreaManage(){
		return "sys/areaManage";
	}
	
	/** 
	 * @Title: queryAreaTree 
	 * @Description: 查询地区树
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(params="method=queryAreaTree")
	public String queryAreaTree(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		Map map = new HashMap<String, String>();
		map.put("recursion", true);
		List<TConfigArea> list = this.areaServcie.queryAreaByMap(map);
		JSONArray rtnArr = new JSONArray();
		for(int i=0;i<list.size();i++){
			TConfigArea tmpArea = list.get(i);
			if(tmpArea.getAreaLevel().intValue()==1){//areaLevel == 1
				JSONObject obj = new JSONObject();
				obj.put("id", tmpArea.getAreaId());
				obj.put("name", tmpArea.getAreaName());
				obj.put("children", new JSONArray());
				rtnArr.add(obj);
			}else if(tmpArea.getAreaLevel().intValue()==2){
				JSONObject obj = new JSONObject();
				obj.put("id", tmpArea.getAreaId());
				obj.put("name", tmpArea.getAreaName());
				obj.put("children", new JSONArray());
				((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).add(obj);
			}else if(tmpArea.getAreaLevel().intValue()==3){
				JSONObject obj = new JSONObject();
				obj.put("id", tmpArea.getAreaId());
				obj.put("name", tmpArea.getAreaName());
				obj.put("children", new JSONArray());
				JSONArray tmpJsonArr = ((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children"));
				((JSONArray)((JSONObject)(tmpJsonArr.get(tmpJsonArr.size()-1))).get("children")).add(obj);
			}else if(tmpArea.getAreaLevel().intValue()==4){
				JSONObject obj = new JSONObject();
				obj.put("id", tmpArea.getAreaId());
				obj.put("name", tmpArea.getAreaName());
				obj.put("children", new JSONArray());
				JSONArray tmpJsonArr = ((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children");
				((JSONArray)((JSONObject)(tmpJsonArr.get(tmpJsonArr.size()-1))).get("children")).add(obj);
			}else if(tmpArea.getAreaLevel().intValue()==5){
				JSONObject obj = new JSONObject();
				obj.put("id", tmpArea.getAreaId());
				obj.put("name", tmpArea.getAreaName());
				obj.put("children", new JSONArray());
				JSONArray tmpJsonArr = ((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children");
				((JSONArray)((JSONObject)(tmpJsonArr.get(tmpJsonArr.size()-1))).get("children")).add(obj);
			}else if(tmpArea.getAreaLevel().intValue()==6){
				JSONObject obj = new JSONObject();
				obj.put("id", tmpArea.getAreaId());
				obj.put("name", tmpArea.getAreaName());
				obj.put("children", new JSONArray());
				JSONArray tmpJsonArr = ((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children");
				((JSONArray)((JSONObject)(tmpJsonArr.get(tmpJsonArr.size()-1))).get("children")).add(obj);
			}else if(tmpArea.getAreaLevel().intValue()==7){
				JSONObject obj = new JSONObject();
				obj.put("id", tmpArea.getAreaId());
				obj.put("name", tmpArea.getAreaName());
				obj.put("children", new JSONArray());
				JSONArray tmpJsonArr = ((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children");
				((JSONArray)((JSONObject)(tmpJsonArr.get(tmpJsonArr.size()-1))).get("children")).add(obj);
			}else if(tmpArea.getAreaLevel().intValue()==8){
				JSONObject obj = new JSONObject();
				obj.put("id", tmpArea.getAreaId());
				obj.put("name", tmpArea.getAreaName());
				obj.put("children", new JSONArray());
				JSONArray tmpJsonArr = ((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children");
				((JSONArray)((JSONObject)(tmpJsonArr.get(tmpJsonArr.size()-1))).get("children")).add(obj);
			}else if(tmpArea.getAreaLevel().intValue()==9){
				JSONObject obj = new JSONObject();
				obj.put("id", tmpArea.getAreaId());
				obj.put("name", tmpArea.getAreaName());
				obj.put("children", new JSONArray());
				JSONArray tmpJsonArr = ((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children");
				((JSONArray)((JSONObject)(tmpJsonArr.get(tmpJsonArr.size()-1))).get("children")).add(obj);
			}else if(tmpArea.getAreaLevel().intValue()==10){
				JSONObject obj = new JSONObject();
				obj.put("id", tmpArea.getAreaId());
				obj.put("name", tmpArea.getAreaName());
				obj.put("children", new JSONArray());
				JSONArray tmpJsonArr = ((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").getJSONObject(((JSONObject)((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").get(((JSONObject)((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).get(((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).size()-1)).getJSONArray("children").size()-1)).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children").size()-1).getJSONArray("children");
				((JSONArray)((JSONObject)(tmpJsonArr.get(tmpJsonArr.size()-1))).get("children")).add(obj);
			}
		}
		ResponseUtil.printWrite(response, rtnArr, ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	
	/** 
	 * @Title: queryAreaTreeForVo 
	 * @Description: 返回地区树，地区管理界面用到
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryAreaTreeForVo")
	public String queryAreaTreeForVo(HttpServletResponse response) throws SQLException{ 
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);	
		ResponseUtil.printWrite(response, this.areaServcie.queryAreaTreeVo(), ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	/** 
	 * @Title: queryAreaTree2 
	 * @Description: 查询地区树，满足jquery easy ui 
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryAreaTree2")
	public String queryAreaTree2(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		List<TConfigArea> list = this.areaServcie.queryAreaTreeVo();
		ResponseUtil.printWrite(response, this.areaServcie.queryAreaTree2(list, null), ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	
	/** 
	 * @Title: areaId 
	 * @Description: 删除地区信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(params="method=deleteArea")
	public String deleteArea(String areaId,HttpServletResponse response){
		if(areaId==null && !"".equals(areaId)){
			ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
		}else{
			ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
			Map map = new HashMap<String,Object>();
			map.put("areaId", areaId);
			map.put("recursion", true);
			int cnt = this.areaServcie.deleteArea(map);
			ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
		}
		return null;
	}
	
	/** 
	 * @Title: addArea 
	 * @Description: 新增地区
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(params="method=addArea")
	public String addDept(String areaInfo,HttpServletResponse response){
		if(areaInfo==null && !"".equals(areaInfo)){
			ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
		}else{
			ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
			JSONObject obj = JSONObject.fromObject(areaInfo);
			Map map = (Map<String,Object>)JSONObject.toBean(obj, HashMap.class);
			map.put("userId", UserHolder.getUserContext().getUserId());
			int cnt = this.areaServcie.addArea(map);
			ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
		}
		return null;
	}
	/** 
	 * @Title: updateArea 
	 * @Description: 修改地区信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(params="method=updateArea")
	public String updateArea(String areaInfo,HttpServletResponse response){
		if(areaInfo==null && !"".equals(areaInfo)){
			ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
		}else{
			ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
			JSONObject obj = JSONObject.fromObject(areaInfo);
			Map map = (Map<String,Object>)JSONObject.toBean(obj, HashMap.class);
			map.put("modifyUserId", UserHolder.getUserContext().getUserId());
			int cnt = this.areaServcie.updateArea(map);
			ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
		}
		return null;
	}
	/** 
	 * @Title: toAddArea 
	 * @Description: 跳转到新增地区页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toAddArea")
	public String toAddDept(){
		return "sys/addArea";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(params="method=queryCondition")
	public String queryCondition(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		Map map = new HashMap<String, String>();
		map.put("recursion", true);
		List<TConfigArea> areaList = this.areaServcie.queryAreaByMap(map);
		JSONObject obj = new JSONObject();
		obj.put("areaList", areaList);
		ResponseUtil.printWrite(response, obj, ResponseUtil.TRANSFER_NONE);
		return null;
	}
	
}
