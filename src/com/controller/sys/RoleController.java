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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.common.BufferService;
import com.service.sys.MenuService;
import com.service.sys.RoleService;
import com.service.sys.UnitService;
import com.util.ResponseUtil;
import com.util.UserHolder;

/**
 * 
 * @className: RoleController 
 * @author: gu.xiaogang
 * @description: 权限控制器
 * @date: 2014年9月10日
 */
@Controller
@RequestMapping("/role.spr")
public class RoleController {
	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;
	
	@Autowired
	@Qualifier("menuService")
	private MenuService menuService;
	
	@Autowired
	@Qualifier("bufferService")
	private BufferService bufferService;
	
	private final static Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	/** 
	 * @Title: toRManageNew 
	 * @Description: 跳转到权限管理页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toRManageNew")
	public String toRManageNew(){
		return "sys/roleManageNew";
	}
	
	/** 
	 * @Title: toRManage 
	 * @Description: 跳转到权限管理页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toRManage")
	public String toRManage(){
		return "sys/roleManage";
	}
	
	/** 
	 * @Title: queryRoleCntForMap 
	 * @Description: 查询权限记录数
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryRoleCntForMap")
	public String queryRoleCntForMap(HttpServletRequest req,HttpServletResponse response) throws UnsupportedEncodingException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		String userInfo = new String (req.getParameter("queryInfo").getBytes("iso-8859-1"),"utf8");
		JSONObject obj = JSONObject.fromObject(userInfo);
		Map<String,Object> map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
		JSONObject rtnObj = new JSONObject();
		try {
			int rtn = this.roleService.queryRoleCnt(map);
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
	 * @Title: queryRoleForMap 
	 * @Description: 查询权限信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryRoleForMap")
	public String queryRoleForMap(HttpServletRequest req,HttpServletResponse response) throws UnsupportedEncodingException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		String userInfo = new String (req.getParameter("queryInfo").getBytes("iso-8859-1"),"utf8");
		JSONObject obj = JSONObject.fromObject(userInfo);
		Map<String,Object> map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
		try {
			List<Map<String,Object>> list = this.roleService.queryRole(map);
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
	 * @Title: queryRoleForMapNew 
	 * @Description: 查询权限信息（新）
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryRoleForMapNew")
	public String queryRoleForMapNew(HttpServletRequest req,HttpServletResponse response,String queryCondition) throws UnsupportedEncodingException{
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
		int cnt = this.roleService.queryRoleCnt(map);
		List<Map<String, Object>> list = this.roleService.queryRoleNew(map);
		JSONObject obj = new JSONObject();
		obj.put("total", cnt);
		obj.put("rows", list);
		ResponseUtil.printWrite(response, obj, ResponseUtil.TRANSFER_JSONOBJECT);
		return null;
	}
	
	
	/** 
	 * @Title: deleteRole 
	 * @Description: 权限删除
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=deleteRole")
	public String deleteRole(HttpServletRequest req,HttpServletResponse response){
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		String[] roleIdList = req.getParameter("roleIdList").split(",");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("roleIdList", roleIdList);
		int cnt = 0;
		PrintWriter pw = null;
		try{
			pw = response.getWriter();
			cnt = this.roleService.deleteRoleForMap(map);
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
	 * @Title: toAddRole 
	 * @Description: 跳转到权限新增页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toAddRole")
	public String toAddRole(){
		return "sys/addRole";
	}
	
	/** 
	 * @Title: queryTreeMenuInfo 
	 * @Description: 查询菜单树形展示信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryTreeMenuInfo")
	public String queryTreeMenuInfo(HttpServletResponse response,String topMenu){
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			JSONArray rtnArr = new JSONArray();
			Map<String,Object> map = new HashMap<String,Object>();
//			map.put("nullParentMenuId",1);
			map.put("recursion", 1);//递归查找
			/*
			 * 目前只支持两层
			 * 形如   1
			 *        11  1
			 *        12  1
			 *     2   
			 *        21  2
			 *        22  2
			 */
			
			if(topMenu!=null && !"".equals(topMenu)){
				map.put("topMenu", topMenu);
			}
			List<Map<String,Object>> list = this.menuService.findMenu(map);
			
			for(int i=0;i<list.size();i++){
				if("parent".equals((String)list.get(i).get("URI"))){
					JSONObject obj = new JSONObject();
					obj.put("id", list.get(i).get("MENU_ID"));
					obj.put("name", list.get(i).get("NAME"));
					obj.put("children", new JSONArray());
					rtnArr.add(obj);
				}else{
					JSONObject obj = new JSONObject();
					obj.put("id", list.get(i).get("MENU_ID"));
					obj.put("name", list.get(i).get("NAME"));
					((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).add(obj);
				}				
			}
			
//			if(list!=null&& list.size()>0){
//				for(int i=0;i<list.size();i++){
//					JSONObject obj = new JSONObject();
//					obj.put("id", list.get(i).get("MENU_ID"));
//					obj.put("name", list.get(i).get("NAME"));
//					map.remove("nullParentMenuId");
//					map.put("parentMenuId", list.get(i).get("MENU_ID"));
//					List<Map<String,Object>> tmpList1 = this.menuService.findMenu(map);
//					if(tmpList1 != null && tmpList1.size()>0){
//						JSONArray objArr1 = new JSONArray();
//						for(int j=0;j<tmpList1.size();j++){
//							JSONObject obj1 = new JSONObject();
//							obj1.put("id", tmpList1.get(j).get("MENU_ID"));
//							obj1.put("name", tmpList1.get(j).get("NAME"));
//							map.put("parentMenuId", tmpList1.get(j).get("MENU_ID"));
//							List<Map<String,Object>> tmpList2 = this.menuService.findMenu(map);
//							if(tmpList2!=null && tmpList2.size()>0){
//								JSONArray objArr2 = new JSONArray();
//								for(int k=0;k<tmpList2.size();k++){
//									JSONObject obj2 = new JSONObject();
//									obj2.put("id", tmpList2.get(k).get("MENU_ID"));
//									obj2.put("name", tmpList2.get(k).get("NAME"));
//									objArr2.add(obj2);
//								}
//								obj1.put("children", objArr2);
//							}
//							objArr1.add(obj1);
//						}
//						obj.put("children", objArr1);
//					}
//					rtnArr.add(obj);
//				}				
//			}
			pw.println(rtnArr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(params="method=queryMenuTree")
	public String queryMenuTree(HttpServletResponse response,String topMenu){
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			JSONArray rtnArr = new JSONArray();
			Map<String,Object> map = new HashMap<String,Object>();
//			map.put("nullParentMenuId",1);
			map.put("recursion", 1);//递归查找
			/*
			 * 目前只支持两层
			 * 形如   1
			 *        11  1
			 *        12  1
			 *     2   
			 *        21  2
			 *        22  2
			 */
			
			if(topMenu!=null && !"".equals(topMenu)){
				map.put("topMenu", topMenu);
			}
			List<Map<String,Object>> list = this.menuService.findMenu(map);
			
			for(int i=0;i<list.size();i++){
				if("parent".equals((String)list.get(i).get("URI"))){
					JSONObject obj = new JSONObject();
					obj.put("id", list.get(i).get("MENU_ID"));
					obj.put("text", list.get(i).get("NAME"));
					obj.put("children", new JSONArray());
					obj.put("state", "closed");
					rtnArr.add(obj);
				}else{
					JSONObject obj = new JSONObject();
					obj.put("id", list.get(i).get("MENU_ID"));
					obj.put("text", list.get(i).get("NAME"));
					((JSONArray)((JSONObject)rtnArr.get(rtnArr.size()-1)).get("children")).add(obj);
				}				
			}
			pw.println(rtnArr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	 * @Title: saveRoleInfo 
	 * @Description: 权限保存
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=saveRoleInfo")
	public String saveRoleInfo(HttpServletRequest req,HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		String in_roleId = req.getParameter("in_roleId");
		String roleName = req.getParameter("roleName");
		String[] menuStrArr = new String[]{};
		if(req.getParameter("menuListStr")!=null && !"".equals(req.getParameter("menuListStr"))){
			menuStrArr = req.getParameter("menuListStr").split(",");
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("roleName", roleName);
		map.put("createUserId", UserHolder.getUserContext().getUserId());
		Long roleId = 0L;
		if(in_roleId!=null && !"".equals(in_roleId)){//修改,删除关联关系、
			
			map.put("roleId", in_roleId);
			int cnt = this.roleService.updateRoleInfo(map,menuStrArr);
			if(cnt>0){
				roleId = Long.valueOf(in_roleId);
			}else{
				roleId = (long)cnt;
			}
		}else{
			roleId = this.roleService.addRoleInfo(map, menuStrArr);
		}
		
		try {
			//修改role,更新缓存
			/*
			 * 不刷新缓存了
			 */
			//bufferService.refreshBuffer();
			pw = response.getWriter();
			pw.println(roleId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	 * @Title: getRoleInfo 
	 * @Description: 查询权限信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=getRoleInfo")
	public String getRoleInfo(HttpServletRequest req,HttpServletResponse response){
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		Long roleId = Long.valueOf(req.getParameter("roleId"));
		PrintWriter pw = null;
		JSONObject obj = new JSONObject();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("roleId", roleId);
			List<Map<String,Object>> list = this.roleService.queryRole(map);
			if(list!=null && list.size()>0){
				obj.put("roleInfo", list.get(0));
			}
			List<Map<String,Object>> menuList = this.menuService.findMenu(map);
			obj.put("menuList", JSONArray.fromObject(menuList));
			
//			List<Map<String, Object>> unitList = this.unitService.queryUnitForMap(map);
//			obj.put("unitList", JSONArray.fromObject(unitList));
			pw = response.getWriter();
			pw.println(obj);
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
	 * @Title: queryAllRole 
	 * @Description: 查询所有权限信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryAllRole")
	public String queryAllRole(HttpServletResponse response){
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			List<Map<String,Object>> list = this.roleService.queryRole(null);
			pw.println(JSONArray.fromObject(list));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("queryAllRoleAction:"+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	 * @Title: hasAuthority 
	 * @Description: 权限判断方法
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=hasAuthority")
	public String hasAuthority(HttpServletResponse response,Long roleId){
		ResponseUtil.printWrite(response, this.roleService.hasAuthority(roleId), ResponseUtil.TRANSFER_NONE);
		return null;
	}
}
