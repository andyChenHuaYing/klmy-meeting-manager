package com.controller.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

import com.service.AreaService;
import com.service.common.BufferService;
import com.service.sys.CompanySevice;
import com.service.sys.DepartmentService;
import com.service.sys.RoleService;
import com.service.sys.RolegrpService;
import com.service.sys.UnitService;
import com.service.sys.UserService;
import com.util.ConstantParam;
import com.util.ResponseUtil;
import com.util.SysLog;
import com.util.UserHolder;
import com.vo.CompanyVo;
import com.vo.TBRole;
import com.vo.TbBaseDepartment;
import com.vo.TbBaseRolegrp;
import com.vo.UserVO;

/**
 * 
 * @className: UserController 
 * @author: gu.xiaogang
 * @description: 用户控制器
 * @date: 2014年9月10日
 */
@Controller(value="userController")
@RequestMapping("/user.spr")
public class UserController {
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
	private DepartmentService departmentService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private RolegrpService rolegrpService;
	
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/** 
	 * @Title: logon 
	 * @Description: 登录
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings("rawtypes")
	@RequestMapping(params="method=logon")
	public String logon(HttpServletResponse response) throws SQLException, IOException{
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");	
		System.out.println("userName:"+userName+";password:"+password);
//		if(req.getParameter("oprFlag")==null && req.getSession().getAttribute(ConstantParam.USER_INFO)!=null){
//			SysLog.logRecord(null, null, SysLog.OPERATION_TYPE_LOGIN, ((UserVO)(req.getSession().getAttribute(ConstantParam.USER_INFO))).getUserCode()+" 登录系统");
////			return "logon/frame_new"; 
//			return "logon/frame";
//		}
		if(null == userName || null == password || "".equals(userName)|| "".equals(password)){
			response.sendRedirect(req.getContextPath()+"/login_new.jsp");
			return null;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userCode", userName);
		map.put("userPassword", password);
		UserVO userVO = this.userService.queryUniqueUser(map);		
		if(userVO!=null){
			Map<String,Object> userMap = new HashMap<String,Object>();
			userMap.put("userId", userVO.getUserId()); 
//			List<UnitVO> unitList = unitService.queryUnitByUser(userMap);
//			userVO.setUnitList(unitList);
			userMap.put("queryByRolegrp", true);
			List<TBRole> roleList = this.roleService.queryRoleListForVO(userMap);
			if(roleList!=null && roleList.size()>0){
				userVO.setRoleList(roleList);
			}
			Map roleArea = roleService.queryUserRoleAreaList(userVO.getUserId());
			userVO.setRoleAreaList(roleArea);
			req.getSession().setAttribute(ConstantParam.USER_INFO, userVO);
			UserHolder.setUserContext(userVO);
			SysLog.logRecord(null, null, SysLog.OPERATION_TYPE_LOGIN, userVO.getUserCode()+" 登录系统");
//			System.out.println(ust.getUserById(10000000000136L));
//			return "logon/frame_new"; 
			return "logon/frame";
		}else{
			req.getSession().setAttribute("loginErrMsg", "用户名或者密码错误，请重新输入!");
			response.sendRedirect(req.getContextPath()+"/login_new.jsp");
			return null; 
		}
	}
	/** 
	 * @Title: logout 
	 * @Description: 退出
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=logout")
	public String logout(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		req.getSession().invalidate();
		if(null != UserHolder.getUserContext()){
			SysLog.logRecord(null, null, SysLog.OPERATION_TYPE_lOGOUT, UserHolder.getUserContext().getUserCode()+" 退出系统");
		}
		resp.sendRedirect(req.getContextPath()+"/login_new.jsp");
		return null;
	}
	
	/** 
	 * @Title: toUserManage 
	 * @Description: 跳转到用户管理
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toUserManage")
	public String toUserManage(){
		return "sys/userManage";
	}
	/** 
	 * @Title: toUserManageNew 
	 * @Description: 跳转到用户管理新页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toUserManageNew")
	public String toUserManageNew(){
		return "sys/userManageNew";
	}
	/** 
	 * @Title: queryUserForMap 
	 * @Description: 查询用户信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings("unchecked")
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
	
	/** 
	 * @Title: queryUserCntForMap 
	 * @Description: 查询用户记录数
	 * @param 
	 * @return String
	 * @throws 
	*/
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
	
	/** 
	 * @Title: toAddExistsUser 
	 * @Description: 跳转新增用户页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toAddExistsUser")
	public String toAddExistsUser(){
		return "sys/addExistsUser";
	}
	@RequestMapping(params="method=toAddSysUser")
	public String toAddSysUser(){
		return "sys/addUserNew";
	}
	/** 
	 * @Title: queryStructureData 
	 * @Description: 查询部门信息
	 * @param 
	 * @return String
	 * @throws 
	*/
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
	/** 
	 * @Title: queryCompanyData 
	 * @Description: 查询公司信息
	 * @param 
	 * @return String
	 * @throws 
	*/
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
	
	/** 
	 * @Title: toCommonTree 
	 * @Description: 跳转到公共树页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toCommonTree")
	public String toCommonTree(){
		return "common/CommonTree";
	}
	
	
	
	/** 
	 * @Title: queryUserInfo 
	 * @Description: 查询单个用户信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryUserInfo")
	public String queryUserInfo(HttpServletRequest req,HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		Long userId = Long.valueOf(req.getParameter("userId"));
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		try {
			UserVO userVO = this.userService.queryUniqueUser(map);
			List<Map<String,Object>> roleList = this.roleService.queryRole(map);
			List<Map<String,Object>> rolegrpList = this.rolegrpService.queryUserRolegrp(map);
			map.put("orderByRoleId", true);
			Map<String,List<Long>> rtnMap = this.roleService.queryUserRoleArea(map);
			Map<String,List<Long>> rtnRolegrpMap = this.rolegrpService.queryUserRolegrpArea(map);
			
			JSONObject obj = new JSONObject();
			obj.put("userVO", JSONObject.fromObject(userVO));
			obj.put("roleList", JSONArray.fromObject(roleList));
			obj.put("roleAreaMap", JSONObject.fromObject(rtnMap));
			obj.put("rolegrpList", JSONArray.fromObject(rolegrpList));
			obj.put("rolegrpAreaMap", JSONObject.fromObject(rtnRolegrpMap));
			pw = response.getWriter();
			pw.println(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("queryCompanyData:"+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	/** 
	 * @Title: toRegister 
	 * @Description: 跳转到用户注册页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toRegister")
	public String toRegister(){
		return "user/addUser";
	}
	
	/** 
	 * @Title: register 
	 * @Description: 用户注册
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(params="method=register")
	public String register(HttpServletRequest req,HttpServletResponse response) throws UnsupportedEncodingException, SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		String userInfo = new String (req.getParameter("userInfo").getBytes("iso-8859-1"),"utf8");
		String roleMenuStr = req.getParameter("roleMenuMap");
		String rolegrpMenuMapStr = req.getParameter("rolegrpMenuMap");
		JSONObject roleMenuObj = JSONObject.fromObject(roleMenuStr);
		JSONObject rolegrpMenuObj = JSONObject.fromObject(rolegrpMenuMapStr);
		JSONObject obj = JSONObject.fromObject(userInfo);
		Map<String,Object> map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
		Map<String,ArrayList<Long>> roleMenuMap = (Map<String, ArrayList<Long>>) JSONObject.toBean(roleMenuObj, HashMap.class);
		Map<String,ArrayList<Long>> rolegrpMenuMap =  (Map<String, ArrayList<Long>>) JSONObject.toBean(rolegrpMenuObj, HashMap.class);
		
		String roleList = req.getParameter("roleList");
		String rolegrpList = req.getParameter("rolegrpList");
		
		
		map.put("userId", UserHolder.getUserContext() == null ? 10000000000001L:UserHolder.getUserContext().getUserId());
		map.put("createUserId", UserHolder.getUserContext() == null ? 10000000000001L:UserHolder.getUserContext().getUserId());
		map.put("modifyUserId", UserHolder.getUserContext() == null ? 10000000000001L:UserHolder.getUserContext().getUserId());
		
		
		Long userId = 0L;
		JSONObject rtnObj = new JSONObject();
		try {
			userId = this.userService.addUser(map);
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
				}
				
				Iterator<Entry<String, ArrayList<Long>>> it = roleMenuMap.entrySet().iterator();
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				while(it.hasNext()){
					Entry<String,ArrayList<Long>> entry = it.next();
					List areaArr = entry.getValue();
					for(int i=0;i<areaArr.size();i++){
						Map tmpMap = new HashMap<String,Long>();
						tmpMap.put("userId", userId);
						tmpMap.put("roleId", entry.getKey());
						tmpMap.put("areaId", areaArr.get(i));
						list.add(tmpMap);
					}
				}
				this.roleService.saveUserRoleArea(list,userId);
				
				
				//处理用户与权限组的关联关系
				if(rolegrpList!=null && !"".equals(rolegrpList)){//传入了角色ID
					String[] rolegrpArr = rolegrpList.split(",");
					for(int i=0;i<rolegrpArr.length;i++){
						Map<String,Object> tmpMap = new HashMap<String,Object>();
						tmpMap.put("userId", userId);
						tmpMap.put("rolegrpId", rolegrpArr[i]);
						this.rolegrpService.addUserRolegrp(tmpMap);
					}
				}
				Iterator<Entry<String, ArrayList<Long>>> it1 = rolegrpMenuMap.entrySet().iterator();
				List<Map<String,Object>> list2 = new ArrayList<Map<String,Object>>();
				while(it1.hasNext()){
					Entry<String,ArrayList<Long>> entry = it1.next();
					List areaArr = entry.getValue();
					for(int i=0;i<areaArr.size();i++){
						Map tmpMap = new HashMap<String,Long>();
						tmpMap.put("userId", userId);
						tmpMap.put("rolegrpId", entry.getKey());
						tmpMap.put("areaId", areaArr.get(i));
						list2.add(tmpMap);
					}
				}
				
				this.rolegrpService.saveUserRolegrpArea(list2, userId);
			}
			
			// 更新缓存
			//this.bufferService.refreshBuffer();
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
	
	/** 
	 * @Title: deleteUser 
	 * @Description: 删除用户
	 * @param 
	 * @return String
	 * @throws 
	*/
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
	
	/** 
	 * @Title: update 
	 * @Description: 修改用户
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(params="method=update")
	public String update(HttpServletRequest req,HttpServletResponse response) throws UnsupportedEncodingException, SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		String userInfo = new String (req.getParameter("userInfo").getBytes("iso-8859-1"),"utf8");
		String roleMenuStr = req.getParameter("roleMenuMap");
		String rolegrpMenuMapStr = req.getParameter("rolegrpMenuMap");
		JSONObject obj = JSONObject.fromObject(userInfo);
		JSONObject roleMenuObj = JSONObject.fromObject(roleMenuStr);
		JSONObject rolegrpMenuObj = JSONObject.fromObject(rolegrpMenuMapStr);
		Map<String,Object> map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
		Map<String,ArrayList<Long>> roleMenuMap = (Map<String, ArrayList<Long>>) JSONObject.toBean(roleMenuObj, HashMap.class);
		Map<String,ArrayList<Long>> rolegrpMenuMap =  (Map<String, ArrayList<Long>>) JSONObject.toBean(rolegrpMenuObj, HashMap.class);
		String roleList = req.getParameter("roleList");
		String rolegrpList = req.getParameter("rolegrpList");
		map.put("modifyUserId", UserHolder.getUserContext() == null ? 10000000000001L:UserHolder.getUserContext().getUserId());
		JSONObject rtnObj = new JSONObject();
		try {
			int cnt = this.userService.updateUserForMap(map);
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
				//删除用户权限区域关联关系
				//保存用户权限区域关联关系
				Iterator<Entry<String, ArrayList<Long>>> it = roleMenuMap.entrySet().iterator();
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				while(it.hasNext()){
					Entry<String,ArrayList<Long>> entry = it.next();
					List areaArr = entry.getValue();
					for(int i=0;i<areaArr.size();i++){
						Map tmpMap = new HashMap<String,Long>();
						tmpMap.put("userId", map.get("userId"));
						tmpMap.put("roleId", entry.getKey());
						tmpMap.put("areaId", areaArr.get(i));
						list.add(tmpMap);
					}
				}
				this.roleService.saveUserRoleArea(list,Long.valueOf((String)(map.get("userId"))));
				this.rolegrpService.deleteUserRolegrp(map);
				if(rolegrpList!=null && !"".equals(rolegrpList)){//传入了角色ID
					String[] rolegrpArr = rolegrpList.split(",");
					for(int i=0;i<rolegrpArr.length;i++){
						Map<String,Object> tmpMap = new HashMap<String,Object>();
						tmpMap.put("userId", map.get("userId"));
						tmpMap.put("rolegrpId", rolegrpArr[i]);
						this.rolegrpService.addUserRolegrp(tmpMap);
					}
				}
				Iterator<Entry<String, ArrayList<Long>>> it1 = rolegrpMenuMap.entrySet().iterator();
				List<Map<String,Object>> list2 = new ArrayList<Map<String,Object>>();
				while(it1.hasNext()){
					Entry<String,ArrayList<Long>> entry = it1.next();
					List areaArr = entry.getValue();
					for(int i=0;i<areaArr.size();i++){
						Map tmpMap = new HashMap<String,Long>();
						tmpMap.put("userId", map.get("userId"));
						tmpMap.put("rolegrpId", entry.getKey());
						tmpMap.put("areaId", areaArr.get(i));
						list2.add(tmpMap);
					}
				}
				this.rolegrpService.saveUserRolegrpArea(list2, Long.valueOf((String)(map.get("userId"))));
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
	
	
	/** 
	 * @Title: queryUserForMapNew 
	 * @Description: 查询用户信息（新）
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=queryUserForMapNew")
	public String queryUserForMapNew(HttpServletRequest req,HttpServletResponse response,String queryCondition) throws UnsupportedEncodingException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(queryCondition!=null && !"".equals(queryCondition)){
			try{
				JSONObject obj = JSONObject.fromObject(queryCondition);
				map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
			}catch(Exception e){
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		if(req.getParameter("rows")!=null){
			map.put("rows", Integer.valueOf(req.getParameter("rows")));
		}
		if(req.getParameter("page")!=null){
			map.put("page", Integer.valueOf(req.getParameter("page")));
		}
		int cnt = this.userService.queryUserCntForMap(map);
		List<Map<String, Object>> list = this.userService.queryUserForMapNew(map);
		JSONObject obj = new JSONObject();
		obj.put("total", cnt);
		obj.put("rows", list);
		ResponseUtil.printWrite(response, obj, ResponseUtil.TRANSFER_JSONOBJECT);
		return null;
	}
	
	
	/** 
	 * @Title: toAddUser 
	 * @Description: 跳转到用户新增页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toAddUser")
	public String toAddUser(){
		return "user/addUser";
	}
	
	/** 
	 * @Title: toAddUserNew 
	 * @Description: 跳转到用户新增页面（新）
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toAddUserNew")
	public String toAddUserNew(){
		return "user/addUserNew";
	}
	
	/** 
	 * @Title: toMenu 
	 * @Description: 跳转到主框架菜单页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toMenu")
	public String toMenu(){
		return "logon/menu";
	}
	
	/** 
	 * @Title: toQuickbar 
	 * @Description: 跳转到主框架页头页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toQuickbar")
	public String toQuickbar(){
		return "logon/quickbar";
	}
	/** 
	 * @Title: toMainContent 
	 * @Description: 跳转到主框架内容部分
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toMainContent")
	public String toMainContent(){
		return "sys/mainContent";
	}
	/** 
	 * @Title: getUserInitInfo 
	 * @Description: 获取用户所属部门信息、地区信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(params="method=getUserInitInfo")
	public String getUserInitInfo(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		Map map = new HashMap<String, String>();
		map.put("recursion", true);
		List<TbBaseDepartment> deptList = this.departmentService.queryAllDept();
		List<Map<String,Object>> roleList = this.roleService.queryRole(null);
		List<TbBaseRolegrp> rolegrpList = this.rolegrpService.queryRolegrp(null);
		JSONObject obj = new JSONObject();
		obj.put("deptList", deptList);
		obj.put("roleList", roleList);
		obj.put("rolegrpList", rolegrpList);
		ResponseUtil.printWrite(response, obj, ResponseUtil.TRANSFER_NONE);
		return null;
	}
	
	/** 
	 * @Title: toModifyPwd 
	 * @Description: 跳转到修改密码页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toModifyPwd")
	public String toModifyPwd(){
		return "sys/modifyPwd";
	}
	
	/** 
	 * @Title: modifyPwd 
	 * @Description: 修改密码
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(params="method=modifyPwd")
	public String modifyPwd(HttpServletRequest req,HttpServletResponse response,String password){
		Map map = new HashMap<String,Object>();
		map.put("userId", UserHolder.getUserContext().getUserId().toString());
		UserVO user = this.userService.queryUniqueUser(map);
		if(user.getUserPassword().equals(password)){
			ResponseUtil.printWrite(response, 2, ResponseUtil.TRANSFER_NONE);
			return null;
		}
		map.put("userPassword", password);
		ResponseUtil.printWrite(response, this.userService.updateUserForMap(map), ResponseUtil.TRANSFER_NONE);
		return null;
	}
	
}	
