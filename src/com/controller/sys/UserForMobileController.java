package com.controller.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.buffer.SessionBuffer;
import com.service.common.BufferService;
import com.service.sys.CompanySevice;
import com.service.sys.DepartmentService;
import com.service.sys.PersonService;
import com.service.sys.RoleService;
import com.service.sys.UnitService;
import com.service.sys.UserService;
import com.service.sys.UserServiceTest;
import com.util.ConstantParam;
import com.util.ResponseUtil;
import com.util.SysLog;
import com.util.UserHolder;
import com.vo.CompanyVo;
import com.vo.Person;
import com.vo.RoleResourceVO;
import com.vo.SessionVO;
import com.vo.TBRole;
import com.vo.UnitVO;
import com.vo.UserVO;

@Controller(value="userFMController")
@RequestMapping("/userFM.spr")
public class UserForMobileController {
	@Autowired
	@Qualifier("personService")
	private PersonService personService;
	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	@Autowired
	@Qualifier("unitService")
	private UnitService unitService;
	@Autowired
	@Qualifier("departmentService")
	private DepartmentService departService;
	@Autowired
	@Qualifier("companyService")
	private CompanySevice companyService;
	
	@Autowired
	@Qualifier("bufferService")
	private BufferService bufferService;
	
	@Autowired
	private UserServiceTest ust;
	
	private final static Logger logger = LoggerFactory.getLogger(UserForMobileController.class);
	
//	@RequestMapping(params="method=logon")
//	public String logon(HttpServletResponse response) throws SQLException, IOException{
//		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		String userName = req.getParameter("userName");
//		String password = req.getParameter("password");	
//		System.out.println("userName:"+userName+";password:"+password);
//		if(req.getParameter("oprFlag")==null && req.getSession().getAttribute(ConstantParam.USER_INFO)!=null){
//			SysLog.logRecord(null, null, SysLog.OPERATION_TYPE_LOGIN, ((UserVO)(req.getSession().getAttribute(ConstantParam.USER_INFO))).getUserCode()+" 登录系统");
//			return "logon/frame_new"; 
//		}
//		if(null == userName || null == password || "".equals(userName)|| "".equals(password)){
//			response.sendRedirect(req.getContextPath()+"/login.jsp");
//			return null;
//		}
//		
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("userCode", userName);
//		map.put("userPassword", password);
//		UserVO userVO = this.userService.queryUniqueUser(map);		
//		if(userVO!=null){
//			Map<String,Object> userMap = new HashMap<String,Object>();
//			userMap.put("userId", userVO.getUserId()); 
//			List<UnitVO> unitList = unitService.queryUnitByUser(userMap);
//			userVO.setUnitList(unitList);
//			userMap.put("personId", userVO.getPersonId());
//			List<Person> personList = personService.queryPerson(userMap);
//			if(personList!=null && personList.size()>0){
//				userVO.setPerson(personList.get(0));
//			}
//			List<TBRole> roleList = this.roleService.queryRoleListForVO(userMap);
//			if(roleList!=null && roleList.size()>0){
//				Map<String,Object> tmpMap = new HashMap<String, Object>();
//				for(int i=0;i<roleList.size();i++){
//					TBRole tmpRole = roleList.get(i);
//					tmpMap.put("roleId", tmpRole.getRoleId());
//					List<RoleResourceVO> tmpRoleRes = this.roleService.queryRoleResouceByRole(tmpMap);
//					if(tmpRoleRes!=null && tmpRoleRes.size()>0){
//						tmpRole.setRoleResouceList(tmpRoleRes);
//					}
//				}
//				userVO.setRoleList(roleList);
//			}
//			req.getSession().setAttribute(ConstantParam.USER_INFO, userVO);
//			UserHolder.setUserContext(userVO);
//			SysLog.logRecord(null, null, SysLog.OPERATION_TYPE_LOGIN, userVO.getUserCode()+" 登录系统");
////			System.out.println(ust.getUserById(10000000000136L));
//			return "logon/frame_new"; 
//		}else{
//			req.getSession().setAttribute("loginErrMsg", "用户名或者密码错误，请重新输入!");
//			response.sendRedirect(req.getContextPath()+"/login.jsp");
//			return null; 
//		}
//	}
	@RequestMapping(params="method=logout")
	public String logout(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		System.out.println("移除sessionId:"+req.getParameter("sessionId"));
		SessionBuffer.getInstance().removeSession(req.getParameter("sessionId"));
		req.getSession().invalidate();
//		SysLog.logRecord(null, null, SysLog.OPERATION_TYPE_lOGOUT, UserHolder.getUserContext().getUserCode()+" 退出系统");
		JSONObject rtnObj = new JSONObject();
		rtnObj.put("flag", "0");
		ResponseUtil.printWrite(resp, rtnObj, ResponseUtil.TRANSFER_JSONOBJECT);
//		resp.sendRedirect(req.getContextPath()+"/login.jsp");
		return null;
	}
	
	@RequestMapping(params="method=toUserManage")
	public String toUserManage(){
		return "sys/userManage";
	}
	@RequestMapping(params="method=toUserManageNew")
	public String toUserManageNew(){
		return "sys/userManageNew";
	}
	@RequestMapping(params="method=queryUserForMap")
	public String queryUserForMap(HttpServletRequest req,HttpServletResponse response) throws UnsupportedEncodingException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		String userInfo = new String (req.getParameter("queryInfo").getBytes("iso-8859-1"),"utf8");
		JSONObject obj = JSONObject.fromObject(userInfo);
		Map<String,Object> map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
		try {
			List<Map<String,Object>> list = this.userService.queryUserForMap(map);
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=queryUserCntForMap")
	public String queryUserCntForMap(HttpServletRequest req,HttpServletResponse response) throws UnsupportedEncodingException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		String userInfo = new String (req.getParameter("queryInfo").getBytes("iso-8859-1"),"utf8");
		JSONObject obj = JSONObject.fromObject(userInfo);
		Map<String,Object> map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
		JSONObject rtnObj = new JSONObject();
		try {
			int rtn = this.userService.queryUserCntForMap(map);
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
	
	@RequestMapping(params="method=toAddExistsUser")
	public String toAddExistsUser(){
		return "sys/addExistsUser";
	}
	@RequestMapping(params="method=queryDepartmentData")
	public String queryStructureData(HttpServletRequest req,HttpServletResponse response) throws UnsupportedEncodingException, ParserConfigurationException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_XML, ResponseUtil.CHARENCODING_UTF8);		
		PrintWriter pw = null;
		Map<String,Object> map = new HashMap<String, Object>();
		if(req.getParameter("level0")!=null){
			map.put("level0", 1);
		}
		if(req.getParameter("coId")!=null){
			map.put("coId", Long.valueOf(req.getParameter("coId")));
		}
		if(req.getParameter("parentDepartId")!=null){
			map.put("parentDepartId", Long.valueOf(req.getParameter("parentDepartId")));
		}
		
		
		DocumentBuilder builder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		try {
			List<Map<String,Object>> list = this.departService.queryDepartment(map);
			Document doc = builder.newDocument();
			Element tmpEle = doc.createElement("tree");
			doc.appendChild(tmpEle);
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size();i++){
					Element ele = doc.createElement("tree");
					ele.setAttribute("text", (String)list.get(i).get("NAME"));
					ele.setAttribute("action", "javascript:departClick("+((BigDecimal)list.get(i).get("DEPART_ID")).longValue()+",'"+(String)list.get(i).get("NAME")+"');");
					if(((BigDecimal)list.get(i).get("CNT")).intValue()>0){
						ele.setAttribute("src", req.getContextPath()+"/sysConfig/queryDepartmentData.action?parentDepartId="+((BigDecimal)list.get(i).get("DEPART_ID")).longValue());
					}
					tmpEle.appendChild(ele);
				}
			}
			pw = response.getWriter();
			pw.println(ResponseUtil.docToStr(doc));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(params="method=queryCompanyData")
	public String queryCompanyData(HttpServletRequest req,HttpServletResponse response) throws UnsupportedEncodingException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		Map<String,Object> map = ResponseUtil.transferAttrToMap(req, "queryCondition");
		try {
			List<CompanyVo> list =  this.companyService.queryCompany(map);
			pw = response.getWriter();
			pw.println(JSONArray.fromObject(list));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("queryCompanyData:"+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(params="method=toCommonTree")
	public String toCommonTree(){
		return "common/CommonTree";
	}
	
	
	
	@RequestMapping(params="method=queryUserInfo")
	public String queryUserInfo(HttpServletRequest req,HttpServletResponse response){
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		Long userId = Long.valueOf(req.getParameter("userId"));
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		try {
			UserVO userVO = this.userService.queryUniqueUser(map);
			List<Person> personList = this.personService.queryPerson(map);
			List<Map<String,Object>> roleList = this.roleService.queryRole(map);
			JSONObject obj = new JSONObject();
			obj.put("userVO", JSONObject.fromObject(userVO));
			if(personList!=null && personList.size()>0){
				obj.put("personVO", JSONObject.fromObject(personList.get(0)));
			}
			obj.put("roleList", JSONArray.fromObject(roleList));
			pw = response.getWriter();
			pw.println(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("queryCompanyData:"+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(params="method=toRegister")
	public String toRegister(){
		return "user/addUser";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=register")
	public String register(HttpServletRequest req,HttpServletResponse response) throws UnsupportedEncodingException, SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		String userInfo = new String (req.getParameter("userInfo").getBytes("iso-8859-1"),"utf8");
		JSONObject obj = JSONObject.fromObject(userInfo);
		Map<String,Object> map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
		String roleList = req.getParameter("roleList");
		map.put("userId", UserHolder.getUserContext() == null ? 10000000000001L:UserHolder.getUserContext().getUserId());
		map.put("createUserId", UserHolder.getUserContext() == null ? 10000000000001L:UserHolder.getUserContext().getUserId());
		map.put("modifyUserId", UserHolder.getUserContext() == null ? 10000000000001L:UserHolder.getUserContext().getUserId());
		
		
		Long userId = 0L;
		JSONObject rtnObj = new JSONObject();
		try {
			if(map.get("personId") != null && ((Long)map.get("personId")).longValue() != 0L){
				userId = this.userService.addUserAndUpdatePerson(map);
			}else{
				userId = this.userService.addUserAndPerson(map);
			}
			if(userId>0){
				if(roleList!=null && !"".equals(roleList)){//传入了角色ID
					String[] roleArr = roleList.split(",");
					for(int i=0;i<roleArr.length;i++){
						Map<String,Object> tmpMap = new HashMap<String,Object>();
						tmpMap.put("userId", userId);
						tmpMap.put("roleId", roleArr[i]);
						tmpMap.put("createUserId", UserHolder.getUserContext() == null ? 10000000000001L:UserHolder.getUserContext().getUserId());
						this.roleService.addUserRole(tmpMap);
					}
				}else{//默认患者权限
					Map<String,Object> tmpMap = new HashMap<String,Object>();
					tmpMap.put("userId", userId);
					tmpMap.put("createUserId", UserHolder.getUserContext() == null ? 10000000000001L:UserHolder.getUserContext().getUserId());
					this.roleService.addPatientRole(tmpMap);
				}
			}
			
			// 更新缓存
			this.bufferService.refreshBuffer();
			pw = response.getWriter();
			rtnObj.put("userId", userId);
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
	
	@RequestMapping(params="method=deleteUser")
	public String deleteUser(HttpServletRequest req,HttpServletResponse response){
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		String[] userIdList = req.getParameter("userIdList").split(",");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userIdList", userIdList);
		int cnt = 0;
		PrintWriter pw = null;
		try{
			pw = response.getWriter();
			cnt = this.userService.deleteUser(map);
			pw.println(cnt);
		}catch(Exception e){
			logger.error("deleteUser:"+e.getMessage());
		}finally{			
			if(pw!=null){
				pw.close();
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=update")
	public String update(HttpServletRequest req,HttpServletResponse response) throws UnsupportedEncodingException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		String userInfo = new String (req.getParameter("userInfo").getBytes("iso-8859-1"),"utf8");
		JSONObject obj = JSONObject.fromObject(userInfo);
		Map<String,Object> map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
		String roleList = req.getParameter("roleList");
		map.put("modifyUserId", UserHolder.getUserContext() == null ? 10000000000001L:UserHolder.getUserContext().getUserId());
		JSONObject rtnObj = new JSONObject();
		try {
			int cnt = this.userService.updateUserForMap(map);
			this.personService.updatePerson(map);
			if(map.get("userId")!=null){
				this.roleService.deleteUserRole(map);
				if(roleList!=null && !"".equals(roleList)){//传入了角色ID
					String[] roleArr = roleList.split(",");
					for(int i=0;i<roleArr.length;i++){
						Map<String,Object> tmpMap = new HashMap<String,Object>();
						tmpMap.put("userId", map.get("userId"));
						tmpMap.put("roleId", roleArr[i]);
						tmpMap.put("createUserId", UserHolder.getUserContext() == null ? 10000000000001L:UserHolder.getUserContext().getUserId());
						this.roleService.addUserRole(tmpMap);
					}
				}
			}
			pw = response.getWriter();
			rtnObj.put("cnt", cnt);
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
	
	@RequestMapping(params="method=toAddUser")
	public String toAddUser(){
		return "user/addUser";
	}
	
	@RequestMapping(params="method=toAddUserNew")
	public String toAddUserNew(){
		return "user/addUserNew";
	}
	
	@RequestMapping(params="method=toMenu")
	public String toMenu(){
		return "logon/menu";
	}
	
	@RequestMapping(params="method=toQuickbar")
	public String toQuickbar(){
		return "logon/quickbar";
	}
	@RequestMapping(params="method=toMainContent")
	public String toMainContent(){
		return "sys/mainContent";
	}
	
	/*
	 * flag:0    表示成功
	 * flag:1    表示失败 用户名或者密码为空
	 * flag:2    表示失败 用户名或者密码错误
	 * userInfo:{};
	 *  
	 */
	
	@RequestMapping(params="method=logon")
	public String logon(HttpServletResponse response) throws SQLException, IOException{
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");	
		System.out.println("userName:"+userName+";password:"+password);
		
		JSONObject rtnObj = new JSONObject();
		
		
		
		if(null == userName || null == password || "".equals(userName)|| "".equals(password)){
			rtnObj.put("flag", 1);
			rtnObj.put("msg", "用户名或者密码为空!");
//			response.sendRedirect(req.getContextPath()+"/login.jsp");
			ResponseUtil.printWrite(response, rtnObj, ResponseUtil.TRANSFER_JSONOBJECT);
			return null; 
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userCode", userName);
		map.put("userPassword", password);
		UserVO userVO = this.userService.queryUniqueUser(map);		
		if(userVO!=null){
			rtnObj.put("flag", 0);
			rtnObj.put("userInfo", userVO);
			rtnObj.put("sessionId", req.getSession().getId());
//			Map<String,Object> userMap = new HashMap<String,Object>();
//			userMap.put("userId", userVO.getUserId()); 
//			List<UnitVO> unitList = unitService.queryUnitByUser(userMap);
//			userVO.setUnitList(unitList);
//			userMap.put("personId", userVO.getPersonId());
//			List<Person> personList = personService.queryPerson(userMap);
//			if(personList!=null && personList.size()>0){
//				userVO.setPerson(personList.get(0));
//			}
//			List<TBRole> roleList = this.roleService.queryRoleListForVO(userMap);
//			if(roleList!=null && roleList.size()>0){
//				Map<String,Object> tmpMap = new HashMap<String, Object>();
//				for(int i=0;i<roleList.size();i++){
//					TBRole tmpRole = roleList.get(i);
//					tmpMap.put("roleId", tmpRole.getRoleId());
//					List<RoleResourceVO> tmpRoleRes = this.roleService.queryRoleResouceByRole(tmpMap);
//					if(tmpRoleRes!=null && tmpRoleRes.size()>0){
//						tmpRole.setRoleResouceList(tmpRoleRes);
//					}
//				}
//				userVO.setRoleList(roleList);
//			}
//			req.getSession().setAttribute(ConstantParam.USER_INFO, userVO);
			String sessionId = req.getSession().getId();
			System.out.println("sessionId存入缓存："+sessionId);
			
			SessionBuffer.getInstance().setSession(new SessionVO(sessionId,new Date()), sessionId);
			UserHolder.setUserContext(userVO);
			SysLog.logRecord(null, null, SysLog.OPERATION_TYPE_LOGIN, userVO.getUserCode()+" 登录系统");
//			System.out.println(ust.getUserById(10000000000136L));
//			return "logon/frame_new"; 
		}else{
//			req.getSession().setAttribute("loginErrMsg", "用户名或者密码错误，请重新输入!");
//			response.sendRedirect(req.getContextPath()+"/login.jsp");
			rtnObj.put("flag", 2);
			rtnObj.put("msg", "用户名或者密码错误!");
		}
		
		ResponseUtil.printWrite(response, rtnObj, ResponseUtil.TRANSFER_JSONOBJECT);
		return null; 
		
	}
	@RequestMapping(params="method=sessionOut")
	public String sessionOut(HttpServletResponse response){
		JSONObject rtnObj = new JSONObject();
		rtnObj.put("flag", "4");
		rtnObj.put("msg", "session不存在或者过期!");
		ResponseUtil.printWrite(response, rtnObj, ResponseUtil.TRANSFER_JSONOBJECT);
		return null;
	}
		
}	
