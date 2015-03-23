package com.controller.buz;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.buz.RyJbxxService;
import com.util.ResponseUtil;
import com.vo.buz.RyJbxx;

/**
 * 
 * @className: RyJbxxController 
 * @author: gu.xiaogang
 * @description: 人员基本信息控制器
 * @date: 2014年9月10日
 */
@Controller(value="ryJbxxController")
@RequestMapping("/ryJbxx.spr")
public class RyJbxxController {
	@Autowired
	@Qualifier("ryJbxxService")
	private RyJbxxService ryJbxxService;
	
	private static Logger logger = LoggerFactory.getLogger(RyJbxxController.class); 
	
	
	@RequestMapping(params="method=toGaPersonQuery")
	public String toGaPersonQuery(){
		return "buz/ga/gaPersonQuery";
	}
	
	
	/** 
	 * @Title: queryRyJbxx 
	 * @Description: 查询人员基本信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=queryRyJbxx")
	public String queryRyJbxx(HttpServletRequest req,HttpServletResponse response,String queryCondition) throws SQLException{
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
		int cnt = this.ryJbxxService.queryRyJbxxCnt(map);
		List<RyJbxx> list = this.ryJbxxService.queryRy(map);
		JSONObject obj = new JSONObject();
		obj.put("total", cnt);
		obj.put("rows", list);
		ResponseUtil.printWrite(response, obj, ResponseUtil.TRANSFER_JSONOBJECT);
		return null;
	}
	
	
	
}
