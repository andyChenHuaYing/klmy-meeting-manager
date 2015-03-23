package com.controller.table;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonObject;
import com.service.datagrid.TableConfigService;
import com.util.ResponseUtil;

/**
 * 
 * @className: TableConfigController 
 * @author: gu.xiaogang
 * @description: 公共的表格页面
 * @date: 2014年9月10日
 */
@Controller(value="tableConfigController")
@RequestMapping("/tableConfig.spr")
public class TableConfigController {
	@Autowired
	@Qualifier("tableConfigService")
	private TableConfigService tableConfigService;
	
	/** 
	 * @Title: queryTableConfigInfo 
	 * @Description: 查询表格配置信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings("rawtypes")
	@RequestMapping(params="method=queryTableConfigInfo")
	public String queryTableConfigInfo(HttpServletRequest req,String tableId, HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_HTML, ResponseUtil.CHARENCODING_UTF8);
		String userCondition = req.getParameter("userCondition");
		Map map = this.tableConfigService.queryTableConfigInfo(tableId,userCondition);
		ResponseUtil.printWrite(response, map, ResponseUtil.TRANSFER_JSONOBJECT);
		return null;
	}
	
	/** 
	 * @Title: toColumnSetting 
	 * @Description: 跳转到列头设置页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toColumnSetting")
	public String toColumnSetting(){
		return "common/DatagridColumnSetting";
	}
	
	/** 
	 * @Title: toUserTabColumnRel 
	 * @Description: 跳转到配置用户查询展示列页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toUserTabColumnRel")
	public String toUserTabColumnRel(){
		return "sys/userTabColumnRel";
	}
	
	/** 
	 * @Title: queryAllTable 
	 * @Description: 查询所有表格信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryAllTable")
	public String queryAllTable(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_HTML, ResponseUtil.CHARENCODING_UTF8);
		ResponseUtil.printWrite(response, this.tableConfigService.queryAllTable(), ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	
	/**
	 * @throws SQLException  
	 * @Title: setUserTabColumnRel 
	 * @Description: TODO
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=setUserTabColumnRel")
	public String setUserTabColumnRel(HttpServletRequest req,HttpServletResponse response,Long userId) throws SQLException{
		if(userId==null || userId.longValue()==0L){
			ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
			return null;
		}
		String tabColumnMapStr = req.getParameter("tabColumnMap");
		JSONObject tabColumnMapObj = JSONObject.fromObject(tabColumnMapStr);
		Map<String,ArrayList<Long>> tabColumnMap = (Map<String, ArrayList<Long>>) JSONObject.toBean(tabColumnMapObj, HashMap.class);
		int cntCnt = this.tableConfigService.setUserTabColumnRel(userId, tabColumnMap);
		ResponseUtil.printWrite(response, cntCnt, ResponseUtil.TRANSFER_NONE);
		return null;
	}
	/** 
	 * @Title: queryUserTabHeaderMap 
	 * @Description: 查询某个用户下表和表头信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryUserTabHeaderMap")
	public String queryUserTabHeaderMap(Long userId,HttpServletResponse response) throws SQLException{
		Map<String,List<String>> rtnMap = this.tableConfigService.queryUserTabHeaderMap(userId);
		if(rtnMap.size()>0){
			ResponseUtil.printWrite(response, rtnMap , ResponseUtil.TRANSFER_JSONOBJECT);
		}else{
			ResponseUtil.printWrite(response, new JSONObject() , ResponseUtil.TRANSFER_JSONOBJECT);
		}
		return null;
	}
	
	/**
	 * @throws SQLException  
	 * @Title: copyQuerySetting 
	 * @Description: 复制另一个用户的查询设置给当前用户 userId 为当前要赋值的用户，copyUserId 为模板用户
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=copyQuerySetting")
	public String copyQuerySetting(Long userId,Long copyUserId,HttpServletResponse response) throws SQLException{
		if(userId==null || copyUserId ==null){
			ResponseUtil.printWrite(response, 0 , ResponseUtil.TRANSFER_NONE);
		}
		ResponseUtil.printWrite(response, this.tableConfigService.copyQuerySetting(userId, copyUserId) , ResponseUtil.TRANSFER_NONE);
		return null;
	}
	
}
