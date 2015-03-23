package com.controller.sys;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.sys.RolegrpService;
import com.util.ResponseUtil;
import com.util.UserHolder;
import com.vo.TbBaseRolegrp;

@Controller(value="rolegrpController")
@RequestMapping("/rolegrp.spr")
public class RolegrpController {
	@Autowired
	private RolegrpService rolegrpService;
	
	private static Logger logger = LoggerFactory.getLogger(RolegrpController.class);
	
	/** 
	 * @Title: toRolegrpManage 
	 * @Description: 跳转到权限组管理界面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toRolegrpManage")
	public String toRolegrpManage(){
		return "sys/rolegrpManage";
	}
	/** 
	 * @Title: toAddRolegrp 
	 * @Description: 跳转到新增修改权限组页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toAddRolegrp")
	public String toAddRolegrp(){
		return "sys/addRolegrp";
	}
	
	/** 
	 * @Title: queryRolegrp 
	 * @Description: 查询权限组信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=queryRolegrp")
	public String queryRolegrp(HttpServletRequest req,HttpServletResponse response,String queryCondition) throws UnsupportedEncodingException, SQLException{
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
		int cnt = this.rolegrpService.queryRolegrpCnt(map);
		List<TbBaseRolegrp> list = this.rolegrpService.queryRolegrp(map);
		JSONObject obj = new JSONObject();
		obj.put("total", cnt);
		obj.put("rows", list);
		ResponseUtil.printWrite(response, obj, ResponseUtil.TRANSFER_JSONOBJECT);
		return null;
	}
	
	/** 
	 * @Title: queryRoleList 
	 * @Description: 权限权限组相关的待选权限点和已选权限点
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryRoleList")
	public String queryRoleList(Long rolegrpId,HttpServletResponse response){
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		ResponseUtil.printWrite(response, this.rolegrpService.queryRoleList(rolegrpId), ResponseUtil.TRANSFER_JSONOBJECT);
		return null;
	}
	
	/** 
	 * @Title: addRole 
	 * @Description: 新增权限组
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(params="method=addRolegrp")
	public String addRolegrp(String rolegrpInfo,HttpServletResponse response) throws SQLException{
		if(rolegrpInfo==null && !"".equals(rolegrpInfo)){
			ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
		}else{
			ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
			JSONObject obj = JSONObject.fromObject(rolegrpInfo);
			Map map = (Map<String,Object>)JSONObject.toBean(obj, HashMap.class);
			int cnt = this.rolegrpService.addRolegrpInfo(map);
			ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
		}
		return null;
	}
	/** 
	 * @Title: updateRolegrp 
	 * @Description: 修改权限组
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(params="method=updateRolegrp")
	public String updateRolegrp(String rolegrpInfo,Long rolegrpId,HttpServletResponse response) throws SQLException{
		if(rolegrpInfo==null && !"".equals(rolegrpInfo)){
			ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
		}else{
			ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
			JSONObject obj = JSONObject.fromObject(rolegrpInfo);
			Map map = (Map<String,Object>)JSONObject.toBean(obj, HashMap.class);
			int cnt = this.rolegrpService.updateRolegrpInfo(map, rolegrpId);
			ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
		}
		return null;
	}
	
	/** 
	 * @Title: deleteRolegrp 
	 * @Description: 删除权限组及其关联权限点
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=deleteRolegrp")
	public String deleteRolegrp(Long rolegrpId,HttpServletResponse response) throws SQLException{
		if(rolegrpId==null){
			ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
		}else{
			ResponseUtil.printWrite(response, this.rolegrpService.deleteRolegrpInfo(rolegrpId), ResponseUtil.TRANSFER_NONE);
		}
		return null;
	}
	
}
