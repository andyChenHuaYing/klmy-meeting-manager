package com.controller.buz.mz;

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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.DictionaryService;
import com.service.buz.mz.MzRyJbxxService;
import com.util.ResponseUtil;
import com.vo.TConfigDictionary;

/**
 * 
 * @className: MzRyJbxxController 
 * @author: gu.xiaogang
 * @description: 民政人员基本信息控制器
 * @date: 2014年9月10日
 */
@Controller(value="mzRyJbxxController")
@RequestMapping("/mzRyJbxx.spr")
public class MzRyJbxxController {
	@Autowired
	@Qualifier("mzRyJbxxService")
	private MzRyJbxxService mzRyJbxxService;
	@Autowired
	private DictionaryService dictionaryService; 
	private static Logger logger = LoggerFactory.getLogger(MzRyJbxxController.class); 
	
	/**
	 * @throws SQLException  
	 * @Title: toMzPersonQuery 
	 * @Description: 跳转到民政人员查询页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toMzPersonQuery")
	public String toMzPersonQuery(HttpServletRequest req) throws SQLException{
		String keywords = req.getParameter("keywords");
		if(keywords!=null && !"".equals(keywords)){
			String[] keywordArr = keywords.split(",");
			Map map = new HashMap<String, String[]>();
			map.put("keywords", keywordArr);
			Map<String,List<TConfigDictionary>> rtnMap = this.dictionaryService.queryDictionaryClassify(map);
			req.setAttribute("dictionaryList", JSONObject.fromObject(rtnMap).toString());
		}
		return "buz/mz/mzPersonQuery";
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
		int cnt = this.mzRyJbxxService.queryMzRyJbxxCnt(map);
		List<Map<String, Object>> list = this.mzRyJbxxService.queryMzRy(map);
		JSONObject obj = new JSONObject();
		obj.put("total", cnt);
		obj.put("rows", list);
		ResponseUtil.printWrite(response, obj, ResponseUtil.TRANSFER_JSONOBJECT);
		return null;
	}
	
	
	
}
