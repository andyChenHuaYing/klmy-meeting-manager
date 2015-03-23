package com.controller.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.service.sys.CompanySevice;
import com.service.sys.DepartmentService;
import com.util.ResponseUtil;
import com.vo.CompanyVo;

/**
 * 
 * @className: SysConfigController 
 * @author: gu.xiaogang
 * @description: 系统设置控制器
 * @date: 2014年9月10日
 */
@Controller
@RequestMapping("/sysConfig.spr")
public class SysConfigController {
	
	@Autowired
	@Qualifier("departmentService")
	private DepartmentService departService;
	
	@Autowired
	@Qualifier("companyService")
	private CompanySevice companyService;
	
	
	private final static Logger logger = LoggerFactory.getLogger(SysConfigController.class);
	
	/** 
	 * @Title: queryStructureData 
	 * @Description: 查询部门树形菜单数据
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params=("method=queryDepartmentData"))
	public String queryStructureData(HttpServletRequest req,HttpServletResponse response) throws UnsupportedEncodingException, ParserConfigurationException{
		ResponseUtil.formatResp(response, ResponseUtil.CONTENTTYPE_XML, ResponseUtil.CHARENCODING_UTF8);
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
						ele.setAttribute("src", req.getContextPath()+"/sysConfig.spr?method=queryDepartmentData&parentDepartId="+((BigDecimal)list.get(i).get("DEPART_ID")).longValue());
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
	@RequestMapping(params=("method=queryCompanyData"))
	public String queryCompanyData(HttpServletRequest req,HttpServletResponse response) throws UnsupportedEncodingException{
		ResponseUtil.formatResp(response, ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
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
	
}
