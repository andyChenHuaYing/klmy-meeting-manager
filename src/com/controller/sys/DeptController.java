package com.controller.sys;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.AreaService;
import com.service.sys.DepartmentService;
import com.util.ResponseUtil;
import com.util.UserHolder;
import com.vo.TConfigArea;
import com.vo.TbBaseDepartment;

/**
 * 
 * @className: DeptController 
 * @author: gu.xiaogang
 * @description: 组织架构控制器类
 * @date: 2014年9月17日
 */
@Controller(value="deptController")
@RequestMapping("dept.spr")
public class DeptController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private AreaService areaService;
	/** 
	 * @Title: toDeptManage 
	 * @Description: 跳转到部门管理页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toDeptManage")
	public String toDeptManage(){
		return "sys/deptManage";
	}
	/** 
	 * @Title: queryDeptTree 
	 * @Description: 查询部门树
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryDeptTree")
	public String queryDeptTree(HttpServletResponse response){
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		List<TbBaseDepartment> list = this.departmentService.queryDeptTree();
		ResponseUtil.printWrite(response, list, ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	
	/** 
	 * @Title: queryAllDept 
	 * @Description: 查询所有部门信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryAllDept")
	public String queryAllDept(HttpServletResponse response){
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		List<TbBaseDepartment> list = this.departmentService.queryAllDept();
		ResponseUtil.printWrite(response, list, ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	
	/** 
	 * @Title: deleteDept 
	 * @Description: 删除部门信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(params="method=deleteDept")
	public String deleteDept(String deptId,HttpServletResponse response){
		if(deptId==null && !"".equals(deptId)){
			ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
		}else{
			ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
			Map map = new HashMap<String,Object>();
			map.put("deptId", deptId);
			map.put("recursion", true);
			int cnt = this.departmentService.deleteDept(map);
			ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
		}
		return null;
	}
	/** 
	 * @Title: addDept 
	 * @Description: 新增部门
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(params="method=addDept")
	public String addDept(String deptInfo,HttpServletResponse response){
		if(deptInfo==null && !"".equals(deptInfo)){
			ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
		}else{
			ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
			JSONObject obj = JSONObject.fromObject(deptInfo);
			Map map = (Map<String,Object>)JSONObject.toBean(obj, HashMap.class);
			map.put("userId", UserHolder.getUserContext().getUserId());
			int cnt = this.departmentService.addDept(map);
			ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
		}
		return null;
	}
	/** 
	 * @Title: updateDept 
	 * @Description: 修改部门信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(params="method=updateDept")
	public String updateDept(String deptInfo,HttpServletResponse response){
		if(deptInfo==null && !"".equals(deptInfo)){
			ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
		}else{
			ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
			JSONObject obj = JSONObject.fromObject(deptInfo);
			Map map = (Map<String,Object>)JSONObject.toBean(obj, HashMap.class);
			map.put("modifyUserId", UserHolder.getUserContext().getUserId());
			int cnt = this.departmentService.updateDept(map);
			ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
		}
		return null;
	}
	/** 
	 * @Title: toAddDept 
	 * @Description: 跳转到新增部门页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toAddDept")
	public String toAddDept(){
		return "sys/addDept";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(params="method=queryCondition")
	public String queryCondition(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		Map map = new HashMap<String, String>();
		map.put("recursion", true);
		List<TbBaseDepartment> deptList = this.departmentService.queryAllDept();
		List<TConfigArea> areaList = this.areaService.queryAreaByMap(map);
		JSONObject obj = new JSONObject();
		obj.put("deptList", deptList);
		obj.put("areaList", areaList);
		ResponseUtil.printWrite(response, obj, ResponseUtil.TRANSFER_NONE);
		return null;
	}
	
}
