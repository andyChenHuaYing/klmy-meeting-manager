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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.sys.PersonService;
import com.util.ResponseUtil;
import com.vo.Person;

/**
 * 
 * @className: PersonController 
 * @author: gu.xiaogang
 * @description: 人员控制器
 * @date: 2014年9月10日
 */
@Controller
@RequestMapping("/person.spr")
public class PersonController {
	private final static Logger logger = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	@Qualifier("personService")
	private PersonService personService;
	
	/** 
	 * @Title: queryPerson 
	 * @Description: 查询人员信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryPerson")
	public String queryPerson(HttpServletRequest req,HttpServletResponse response){
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		String idCard = req.getParameter("idCard");
		String medicareCard = req.getParameter("medicareCard");
		String personName = req.getParameter("personName");
		Map<String,Object> map = new HashMap<String,Object>();
		if(idCard!=null && !"".equals(idCard)){
			map.put("idCard", req.getParameter("idCard"));
		}
		if(medicareCard!=null && !"".equals(medicareCard)){
			map.put("medicareCard", req.getParameter("medicareCard"));	
		}
		
		if(personName!=null && !"".equals(personName)){
			map.put("personName", personName);
		}
		try {
			List<Person> list = this.personService.queryPerson(map);
			pw = response.getWriter();
			JSONObject obj = new JSONObject();
			obj.put("personInfo", JSONArray.fromObject(list));
			pw.println(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("queryPerson:"+e.getMessage());
			e.printStackTrace();
		} finally {
			if(null!=pw){
				pw.close();
			}
		}
		return null;
	}
	
	/** 
	 * @Title: toPersonManage 
	 * @Description: 跳转到人员管理界面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toPersonManage")
	public String toPersonManage(){
		return "sys/personManage";
	}
	
	/** 
	 * @Title: queryPersonCntForMap 
	 * @Description: 查询人员记录数
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=queryPersonCntForMap")
	public String queryPersonCntForMap(HttpServletRequest req,HttpServletResponse response) throws UnsupportedEncodingException, SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		String userInfo = new String (req.getParameter("queryInfo").getBytes("iso-8859-1"),"utf8");
		JSONObject obj = JSONObject.fromObject(userInfo);
		Map<String,Object> map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
		JSONObject rtnObj = new JSONObject();
		try {
			int rtn = this.personService.queryPersonCntForMap(map);
			rtnObj.put("totalRecord", rtn);
			pw = response.getWriter();
			pw.println(rtnObj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if(null != pw){
				pw.close();
			}
		}
		return null;
	}
	
	/** 
	 * @Title: queryPersonForMap 
	 * @Description: 查询人员信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryPersonForMap")
	public String queryPersonForMap(HttpServletRequest req,HttpServletResponse response) throws UnsupportedEncodingException, SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		String userInfo = new String (req.getParameter("queryInfo").getBytes("iso-8859-1"),"utf8");
		JSONObject obj = JSONObject.fromObject(userInfo);
		Map<String,Object> map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
		try {
			List<Map<String,Object>> list = this.personService.queryPersonForMap(map);
			pw = response.getWriter();
			pw.println(JSONArray.fromObject(list));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if(null != pw){
				pw.close();
			}
		}
		return null;
	}
	
}
