package com.controller.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.sys.LogService;
import com.util.ResponseUtil;

/**
 * 
 * @className: LogController 
 * @author: gu.xiaogang
 * @description: 日志控制器
 * @date: 2014年9月10日
 */
@Controller
@RequestMapping("log.spr")
public class LogController {
	
	@Autowired
	@Qualifier("logService")
	private LogService logService;
	
	/** 
	 * @Title: toLogManage 
	 * @Description: 跳转日志管理界面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toLogManage")
	public String toLogManage(){
		return "sys/logManage";
	}
	/** 
	 * @Title: toLogManageNew 
	 * @Description: 跳转日志管理新界面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toLogManageNew")
	public String toLogManageNew(){
		return "sys/logManageNew";
	}
	/** 
	 * @Title: queryLog 
	 * @Description: 查询日志
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryLog")
	public String queryLog(HttpServletRequest req,HttpServletResponse response) throws UnsupportedEncodingException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		String userInfo = new String (req.getParameter("queryInfo").getBytes("iso-8859-1"),"utf8");
		JSONObject obj = JSONObject.fromObject(userInfo);
		Map<String,Object> map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
		
		try {
			List<Map<String,Object>> list = this.logService.queryLog(map);
			pw = response.getWriter();
			pw.println(JSONArray.fromObject(list));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	
	
	/** 
	 * @Title: queryLogNew 
	 * @Description: 新的日志查询方法
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryLogNew")
	public String queryLogNew(HttpServletRequest req,HttpServletResponse response,String queryCondition) throws UnsupportedEncodingException, SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(queryCondition!=null && !"".equals(queryCondition)){
			try{
				JSONObject obj = JSONObject.fromObject(queryCondition);
				map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(req.getParameter("rows")!=null){
			map.put("rows", Integer.valueOf(req.getParameter("rows")));
		}
		if(req.getParameter("page")!=null){
			map.put("page", Integer.valueOf(req.getParameter("page")));
		}
		int cnt = this.logService.queryLogCnt(map);
		List<Map<String, Object>> list = this.logService.queryLogNew(map);
		JSONObject obj = new JSONObject();
		obj.put("total", cnt);
		obj.put("rows", list);
		ResponseUtil.printWrite(response, obj, ResponseUtil.TRANSFER_JSONOBJECT);
		return null;
	}	
	
	
	/** 
	 * @Title: queryLogCnt 
	 * @Description: 查询日志记录数
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryLogCnt")
	public String queryLogCnt(HttpServletRequest req,HttpServletResponse response) throws UnsupportedEncodingException, SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		String userInfo = new String (req.getParameter("queryInfo").getBytes("iso-8859-1"),"utf8");
		JSONObject obj = JSONObject.fromObject(userInfo);
		Map<String,Object> map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
		JSONObject rtnObj = new JSONObject();
		try {
			Integer cnt = this.logService.queryLogCnt(map);
			pw = response.getWriter();
			rtnObj.put("totalRecord", cnt);
			pw.println(rtnObj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	 * @Title: queryLogType 
	 * @Description: 查询日志类型
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryLogType")
	public String queryLogType(HttpServletResponse response) throws UnsupportedEncodingException, SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		try {
			List<Map<String,Object>> list = this.logService.queryLogType();
			pw = response.getWriter();
			pw.println(JSONArray.fromObject(list));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	 * @Title: queryUserTables 
	 * @Description: 查询用户表
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryUserTables")
	public String queryUserTables(HttpServletResponse response) throws UnsupportedEncodingException, SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		try {
			List<Map<String,Object>> list = this.logService.queryUserTables(null);
			pw = response.getWriter();
			pw.println(JSONArray.fromObject(list));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
