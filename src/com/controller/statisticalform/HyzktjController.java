package com.controller.statisticalform;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.statisticalform.HyzktjService;
import com.util.ResponseUtil;


/**
 * 
 * @className: HyzktjController 
 * @author: gu.xiaogang
 * @description: 婚姻状况控制器
 * @date: 2014年9月11日
 */
@Controller(value="hyzktjController")
@RequestMapping("hyzktj.spr")
public class HyzktjController {
	
	@Autowired
	private HyzktjService hyzktjService;
	
	/** 
	 * @Title: toAreaMaritalStatus 
	 * @Description: 跳转到地区婚姻状况
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toAreaMaritalStatus")
	public String toAreaMaritalStatus(){
		return "statisticalform/areaMaritalStatus";
	}
	
	/** 
	 * @Title: toYearMaritalStatus 
	 * @Description: 跳转到每年婚姻状况页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toYearMaritalStatus")
	public String toYearMaritalStatus(){
		return "statisticalform/yearMaritalStatus";
	}
	
	/** 
	 * @Title: queryAreaMaritalStatus 
	 * @Description: 查询地区婚姻状况
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryAreaMaritalStatus")
	public String queryAreaMaritalStatus(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		ResponseUtil.printWrite(response, this.hyzktjService.queryAreaMaritalStatus(), ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	
	/** 
	 * @Title: queryYearMaritalStatus 
	 * @Description: 查询年份婚姻状况
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryYearMaritalStatus")
	public String queryYearMaritalStatus(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		ResponseUtil.printWrite(response, this.hyzktjService.queryYearMaritalStatus(), ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	
	
}
